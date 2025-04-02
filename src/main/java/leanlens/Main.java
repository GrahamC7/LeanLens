package leanlens;

import java.util.Map;

public class Main {
  public static void main(String[] args) {
    // Step 1: Conduct survey
    SurveyApp surveyApp = new SurveyApp();
    String[] userResponses = surveyApp.conductSurvey();

    // Step 2: Load training data
    SurveyDataManager dataManager = new SurveyDataManager();
    Map<String, PartyData> partyDataMap = dataManager.loadTrainingData();

    // Step 3: Update party data with user responses (excluding final question)
    for (int i = 0; i < userResponses.length - 1; i++) {
      if (!userResponses[i].isEmpty()) {
        for (PartyData party : partyDataMap.values()) {
          party.updateResponseScore(userResponses[i]);
        }
      }
    }

    // Step 4: Save user responses to file
    dataManager.saveResponses(userResponses);

    // Step 5: Train and run classifier
    NaiveBayesClassifier classifier = new NaiveBayesClassifier(partyDataMap);
    String predictedParty = classifier.predict(userResponses);

    // Step 6: Output prediction
    System.out.println("\nPredicted political party: " + predictedParty);
  }
}
