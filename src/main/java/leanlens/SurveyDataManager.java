package leanlens;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SurveyDataManager {
    private static final String DATA_DIRECTORY = "data/";
    private static final String[] PARTY_AFFILIATIONS = {
            "Democratic Party", "Republican Party", "Independent", "Other"
    };

    public Map<String, PartyData> loadTrainingData() {
        Map<String, PartyData> partyDataMap = new HashMap<>();

        // Initialize empty PartyData objects
        for (String party : PARTY_AFFILIATIONS) {
            partyDataMap.put(party, new PartyData());
        }

        // Make sure data directory exists
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists() && !dataDirectory.mkdirs()) {
            System.err.println("Failed to create data directory: " + DATA_DIRECTORY);
            return partyDataMap;
        }

        // Read each party's data file
        for (String party : PARTY_AFFILIATIONS) {
            String fileName = DATA_DIRECTORY + party.replace(" ", "_") + ".txt";
            File file = new File(fileName);

            try {
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        System.out.println("File created: " + fileName);
                    }
                }

                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        partyDataMap.get(party).updateResponseScore(line.trim());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return partyDataMap;
    }

    public void saveResponses(String[] responses) {
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
}
