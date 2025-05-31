package leanlens;

import java.util.Map;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    // Step 1: Conduct survey
    SurveyApp surveyApp = new SurveyApp();
    List<String> userResponses = surveyApp.conductSurvey();

    // Step 2: Show user a summary of their answers
    System.out.printf("%s%s\nThanks for completing the survey! Here's a quick summary of your responses:\n%s\n",
            ConsoleColor.BOLD, ConsoleColor.BLUE, ConsoleColor.RESET);

    List<String> questions = SurveyApp.getQuestions();

    IntStream.range(0, questions.size() - 1).forEach(i -> {
      String question = questions.get(i);
      String answer = SurveyApp.getAnswerText(userResponses.get(i));
      System.out.printf("%s%d. %s%s\n", ConsoleColor.BOLD, i + 1, question, ConsoleColor.RESET);
      System.out.printf("   → Answer: %s%s%s\n\n", ConsoleColor.YELLOW, answer, ConsoleColor.RESET);
    });

    // Step 3: Load training data
    SurveyDataManager dataManager = new SurveyDataManager();
    Map<String, PartyData> partyDataMap = dataManager.loadTrainingData();

    // Step 4: Update training data
    IntStream.range(0, userResponses.size() - 1).forEach(i -> {
      String response = userResponses.get(i);
      if (!response.isEmpty()) {
        partyDataMap.values().forEach(party -> party.updateResponseScore(response));
      }
    });

    // Step 5: Save responses
    dataManager.saveResponses(userResponses);

    // Step 6: Predict
    NaiveBayesClassifier classifier = new NaiveBayesClassifier(partyDataMap);
    String predictedParty = classifier.predict(userResponses);

    System.out.printf("%s\nPredicting political leaning...%s\n", ConsoleColor.BOLD, ConsoleColor.RESET);
    System.out.printf("%s\nPredicted political party: %s%s\n", ConsoleColor.GREEN, predictedParty, ConsoleColor.RESET);
  }
}


