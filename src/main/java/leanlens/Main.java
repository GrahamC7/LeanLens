package leanlens;

import java.util.Map;

public class Main {
  public static final String RESET = "\u001B[0m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String BOLD = "\u001B[1m";

  public static void main(String[] args) {
    // Step 1: Conduct survey
    SurveyApp surveyApp = new SurveyApp();
    String[] userResponses = surveyApp.conductSurvey();

    // Step 2: Show user a summary of their answers
    System.out.println(BOLD + BLUE + "\nThanks for completing the survey! Here's a quick summary of your responses:\n" + RESET);

    String[] questions = SurveyApp.getQuestions();
    for (int i = 0; i < questions.length - 1; i++) { // skip final question (party choice)
      String question = questions[i];
      String answer = SurveyApp.getAnswerText(userResponses[i]);
      System.out.println(BOLD + (i + 1) + ". " + questions[i] + RESET);
      System.out.println("   → Answer: " + YELLOW + SurveyApp.getAnswerText(userResponses[i]) + RESET + "\n");

    }

    // Step 3: Load training data
    SurveyDataManager dataManager = new SurveyDataManager();
    Map<String, PartyData> partyDataMap = dataManager.loadTrainingData();

    // Step 4: Update training data
    for (int i = 0; i < userResponses.length - 1; i++) {
      if (!userResponses[i].isEmpty()) {
        for (PartyData party : partyDataMap.values()) {
          party.updateResponseScore(userResponses[i]);
        }
      }
    }

    // Step 5: Save responses
    dataManager.saveResponses(userResponses);

    // Step 6: Predict
    NaiveBayesClassifier classifier = new NaiveBayesClassifier(partyDataMap);
    String predictedParty = classifier.predict(userResponses);

    System.out.println(BOLD + "\nPredicting political leaning..." + RESET);
    System.out.println(GREEN + "\nPredicted political party: " + predictedParty + RESET);
  }
}

