package leanlens;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class NaiveBayesClassifier {
    private Map<String, PartyData> partyDataMap;

    public NaiveBayesClassifier(Map<String, PartyData> partyDataMap) {
        this.partyDataMap = partyDataMap;
    }

    public String predict(List<String> responses) {
        Map<String, Double> partyScores = new HashMap<>();
        double totalScore = 0.0;

        // Calculate likelihoods of responses given each party
        for (Map.Entry<String, PartyData> entry : partyDataMap.entrySet()) {
            String party = entry.getKey();
            PartyData partyData = entry.getValue();
            double score = 1.0;
            for (String response : responses) {
                score *= partyData.getResponseScore(response);
            }
            partyScores.put(party, score);
            totalScore += score;
        }

        // Find party with the highest score
        double maxScore = Double.MIN_VALUE;
        String predictedParty = "";
        for (Map.Entry<String, Double> entry : partyScores.entrySet()) {
            if (entry.getValue() > maxScore) {
                maxScore = entry.getValue();
                predictedParty = entry.getKey();
            }
        }

        // Calculate confidence
        double confidence = (totalScore > 0) ? (maxScore / totalScore) * 100.0 : 0.0;
        confidence = Math.round(confidence * 10.0) / 10.0; // round to 1 decimal

        return predictedParty + " (" + confidence + "% confidence)";
    }
}

