package leanlens;


import java.util.HashMap;
import java.util.Map;

public class PartyData {
    private Map<String, Double> responseScores = new HashMap<>();

    public PartyData() {
        // initialize response scores to zero for each choice
        responseScores.put("A. STRONGLY AGREE", 0.5);
        responseScores.put("B. AGREE", 0.3);
        responseScores.put("C. NEUTRAL", 0.0);
        responseScores.put("D. DISAGREE", -0.3);
        responseScores.put("E. STRONGLY DISAGREE", -0.5);
    }

    public void updateResponseScore(String responses) {
        responseScores.put(responses, responseScores.getOrDefault(responses, 0.0) + 1.0);
    }

    public double getResponseScores(String responses) {
        return responseScores.getOrDefault(responses, 0.0);
    }
}
