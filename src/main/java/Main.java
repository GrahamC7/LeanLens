import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.*;

public class Main {
  private static Map<String, PartyData> partyDataMap = new HashMap<>();
  private static final String[] PARTY_AFFILIATIONS = { "Democratic Party", "Republican Party", "Independent", "Other" };
  private static final String DATA_DIRECTORY = "data/";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Initialize data storage for each political party
    initializePartyDataStorage();

    // Read existing data from files
    readDataFromFiles();

    // Survey questions
    String[] questions = {
        "Should there be stricter gun control laws?",
        "How open should our borders be to immigrants?",
        "Should the government invest more in public education, or should there be more school choice options?",
        "What is your opinion on climate change?",
        "Do you believe everyone deserves access to affordable healthcare, or should it be primarily a private market?",
        "Which political party do you affiliate with?"
    };

    // Answer choices for the questions
    String[][] answerChoices = {
        { "A. Strongly agree", "B. Agree", "C. Neutral", "D. Disagree", "E. Strongly disagree" },
        { "A. Completely open borders", "B. Controlled immigration", "C. Closed borders" },
        { "A. More investment in public education", "B. More school choice options", "C. Balanced approach" },
        { "A. Climate change is a serious threat that requires immediate action",
            "B. Climate change is a natural phenomenon", "C. Climate change is exaggerated",
            "D. Climate change is a hoax" },
        { "A. Access to affordable healthcare for all", "B. Healthcare as a private market", "C. Other" },
        { "A. Democratic Party", "B. Republican Party", "C. Independent", "D. Other" } // Added answer choices for party affiliation
    };

    // User responses to the questions
    String[] userResponses = new String[questions.length];

    // Showing user the questions and gathering their responses
    for (int i = 0; i < questions.length; i++) {
      System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
      System.out.println("Options:");
      for (String choice : answerChoices[i]) {
        System.out.println(choice);
      }
      System.out.print("Your answer: ");
      userResponses[i] = scanner.nextLine().toUpperCase();

      // Update party data based on user response
      if (i < questions.length - 1 && !userResponses[i].isEmpty()) { // Adjusted condition
        updatePartyData(userResponses[i]);
      }
    }

    // Save user responses to data files
    saveUserResponsesToFile(userResponses);

    // Train the model using existing data
    NaiveBayesClassifier classifier = trainModel();

    // Predict political affiliation based on user responses
    String predictedParty = classifier.predict(userResponses);
    System.out.println("\nPredicted political party: " + predictedParty);
  }

  // Method to initialize data storage for each political party
  private static void initializePartyDataStorage() {
    for (String party : PARTY_AFFILIATIONS) {
      partyDataMap.put(party, new PartyData());
    }
  }

  // Method to update party data based on user responses
  private static void updatePartyData(String response) {
    for (PartyData partyData : partyDataMap.values()) {
      partyData.updateResponseScore(response);
    }
  }

  // Method to save user responses to data files
  private static void saveUserResponsesToFile(String[] responses) {
    try {
      for (int i = 0; i < responses.length - 1 && i < PARTY_AFFILIATIONS.length - 1; i++) {
        String party = PARTY_AFFILIATIONS[i];
        String fileName = DATA_DIRECTORY + party.replace(" ", "_") + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
          writer.write(responses[i] + "\n");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Train the model using existing data
  private static NaiveBayesClassifier trainModel() {
    NaiveBayesClassifier classifier = new NaiveBayesClassifier(partyDataMap);
    return classifier;
  }

  // Method to read existing data from files
  private static void readDataFromFiles() {
    // Ensure that the data directory exists
    File dataDirectory = new File(DATA_DIRECTORY);
    if (!dataDirectory.exists()) {
      // Attempt to create the directory
      if (dataDirectory.mkdirs()) {
        System.out.println("Data directory created: " + DATA_DIRECTORY);
      } else {
        System.err.println("Failed to create data directory: " + DATA_DIRECTORY);
        return;
      }
    }

    // Continue with reading data from files
    for (String party : PARTY_AFFILIATIONS) {
      String fileName = DATA_DIRECTORY + party.replace(" ", "_") + ".txt"; // Replace spaces with underscores
      File file = new File(fileName);
      try {
        if (!file.exists()) {
          // Create the file if it doesn't exist
          file.createNewFile();
          System.out.println("File created: " + fileName);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
          String line;
          while ((line = reader.readLine()) != null) {
            updatePartyData(line.trim());
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // Class to represent party data and perform classification
  static class NaiveBayesClassifier {
    private Map<String, PartyData> partyDataMap;

    public NaiveBayesClassifier(Map<String, PartyData> partyDataMap) {
      this.partyDataMap = partyDataMap;
    }

    public String predict(String[] responses) {
      Map<String, Double> partyScores = new HashMap<>();

      // Calculate likelihoods of responses given each party
      for (Map.Entry<String, PartyData> entry : partyDataMap.entrySet()) {
        String party = entry.getKey();
        PartyData partyData = entry.getValue();
        double score = 1.0;
        for (String response : responses) {
          score *= partyData.getResponseScore(response);
        }
        partyScores.put(party, score);
      }

      // Find party with the highest likelihood
      double maxScore = Double.MIN_VALUE;
      String predictedParty = "";
      for (Map.Entry<String, Double> entry : partyScores.entrySet()) {
        String party = entry.getKey();
        double score = entry.getValue();
        if (score > maxScore) {
          maxScore = score;
          predictedParty = party;
        }
      }

      return predictedParty;
    }
  }

  // Class to show data associated with each political party
  static class PartyData {
    private Map<String, Double> responseScores = new HashMap<>();

    public PartyData() {
      // Initialize response scores to zero for each choice
      responseScores.put("A. STRONGLY AGREE", 0.5);
      responseScores.put("B. AGREE", 0.3);
      responseScores.put("C. NEUTRAL", 0.0);
      responseScores.put("D. DISAGREE", -0.3);
      responseScores.put("E. STRONGLY DISAGREE", -0.5);
    }

    public void updateResponseScore(String response) {
      responseScores.put(response, responseScores.getOrDefault(response, 0.0) + 1.0);
    }

    public double getResponseScore(String response) {
      return responseScores.getOrDefault(response, 0.0);
    }
  }
}