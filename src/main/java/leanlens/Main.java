package leanlens;

import java.util.Map;

public class Main {

  public static void main(String[] args) {
    // Step 1: Conduct survey
    SurveyApp surveyApp = new SurveyApp();
    String[] userResponses = surveyApp.conductSurvey();

    // Step 2: Show user a summary of their answers
    System.out.println(ConsoleColor.BOLD.code() + ConsoleColor.BLUE.code() +
            "\nThanks for completing the survey! Here's a quick summary of your responses:\n" +
            ConsoleColor.RESET.code());

    String[] questions = SurveyApp.getQuestions();
    for (int i = 0; i < questions.length - 1; i++) { // skip final question (party choice)
      String question = questions[i];
      String answer = SurveyApp.getAnswerText(userResponses[i]);

      System.out.println(ConsoleColor.BOLD.code() + (i + 1) + ". " + question + ConsoleColor.RESET.code());
      System.out.println("   → Answer: " + ConsoleColor.YELLOW.code() + answer + ConsoleColor.RESET.code() + "\n");
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

    System.out.println(ConsoleColor.BOLD.code() + "\nPredicting political leaning..." + ConsoleColor.RESET.code());
    System.out.println(ConsoleColor.GREEN.code() + "\nPredicted political party: " +
            predictedParty + ConsoleColor.RESET.code());
  }
}

