package leanlens;

import java.util.HashMap;
import java.util.Map;

public class PartyData {
    private Map<String, Double> responseScores = new HashMap<>();

    public PartyData() {
        // Initialize scores for 1 through 5 with baseline values
        responseScores.put("1", 0.5);  // Strongly Agree
        responseScores.put("2", 0.3);  // Agree
        responseScores.put("3", 0.0);  // Neutral
        responseScores.put("4", -0.3); // Disagree
        responseScores.put("5", -0.5); // Strongly Disagree
    }

    public void updateResponseScore(String response) {
        responseScores.put(response, responseScores.getOrDefault(response, 0.0) + 1.0);
    }

    public double getResponseScore(String response) {
        return responseScores.getOrDefault(response, 0.0);
    }
}
