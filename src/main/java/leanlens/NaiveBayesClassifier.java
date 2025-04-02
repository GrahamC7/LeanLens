package leanlens;


import java.util.HashMap;
import java.util.Map;

public class NaiveBayesClassifier {
    private Map<String, PartyData> partyDataMap;

    public NaiveBayesClassifier(Map<String, PartyData> partyDataMap) {
        this.partyDataMap = partyDataMap;
    }

    public String predict(String[] responses) {
        Map<String, Double> partyScores = new HashMap<>();

        // calculate likelihoods of responses given each party
        for (Map.Entry<String, PartyData> entry : partyDataMap.entrySet()) {
            String party = entry.getKey();
            PartyData partyData = entry.getValue();
            double score = 1.0;
            for (String response : responses) {
                score *= partyData.getResponseScores(response);
            }
            partyScores.put(party, score);
        }

        // find party with the highest likelihood
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
