package leanlens;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SurveyDataManager {
    private static final Logger LOGGER = Logger.getLogger(SurveyDataManager.class.getName());
    private static final String DATA_DIRECTORY = "data/";
    private static final String[] PARTY_AFFILIATIONS = {
            "Democratic Party", "Republican Party", "Independent", "Other"
    };

    public Map<String, PartyData> loadTrainingData() {
        Map<String, PartyData> partyDataMap = new HashMap<>();

        for (String party : PARTY_AFFILIATIONS) {
            partyDataMap.put(party, new PartyData());
        }

        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists() && !dataDirectory.mkdirs()) {
            LOGGER.warning("Failed to create data directory: " + DATA_DIRECTORY);
            return partyDataMap;
        }

        for (String party : PARTY_AFFILIATIONS) {
            String fileName = DATA_DIRECTORY + party.replace(" ", "_") + ".txt";
            File file = new File(fileName);

            if (!file.exists()) {
                LOGGER.info("No existing data file for: " + party);
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    partyDataMap.get(party).updateResponseScore(line.trim());
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading file: " + fileName, e);
            }
        }

        return partyDataMap;
    }

    public void saveResponses(String[] responses) {
        String userParty = responses[responses.length - 1];
        String[] parties = { "Democratic Party", "Republican Party", "Independent", "Other" };
        int partyIndex;

        try {
            partyIndex = Integer.parseInt(userParty) - 1;
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid numeric input for party: " + userParty);
            return;
        }

        if (partyIndex < 0 || partyIndex >= parties.length) {
            LOGGER.warning("Party index out of bounds: " + partyIndex);
            return;
        }

        String party = parties[partyIndex];
        String fileName = DATA_DIRECTORY + party.replace(" ", "_") + ".txt";
        LOGGER.info("Saving responses to: " + fileName);

        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (int i = 0; i < responses.length - 1; i++) {
                writer.write(responses[i] + "\n");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save responses to file: " + fileName, e);
        }
    }
}

