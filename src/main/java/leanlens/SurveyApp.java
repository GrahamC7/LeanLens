package leanlens;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class SurveyApp {

    private static final List<String> QUESTIONS = List.of(
            "The government should implement stricter gun control laws.",
            "The U.S. should provide a path to citizenship for undocumented immigrants.",
            "Taxes on the wealthy should be increased to fund social programs.",
            "The government should ensure universal access to healthcare.",
            "Climate change should be prioritized even at the cost of short-term economic growth.",
            "Same-sex couples should have the same adoption rights as heterosexual couples.",
            "Police funding should be redirected toward community and social services.",
            "Government spending should focus more on public welfare than reducing the national debt.",
            "Which political party do you most closely identify with?"
    );

    private static final List<String> SCALE = List.of(
            "1. Strongly Agree",
            "2. Agree",
            "3. Neutral",
            "4. Disagree",
            "5. Strongly Disagree"
    );

    public List<String> conductSurvey() {
        Scanner scanner = new Scanner(System.in);
        List<String> responses = new ArrayList<>();

        for (int i = 0; i < QUESTIONS.size(); i++) {
            System.out.printf("\nQuestion %d: %s\n", i + 1, QUESTIONS.get(i));

            if (i < QUESTIONS.size() - 1) {
                // Use standard 1-5 agreement scale
                for (String option : SCALE) {
                    System.out.println(option);
                }
            } else {
                // Last question: Party affiliation
                System.out.println("1. Democratic Party");
                System.out.println("2. Republican Party");
                System.out.println("3. Independent");
                System.out.println("4. Other");
            }

            int choice = 0;
            int maxChoice = (i < QUESTIONS.size() - 1) ? 5 : 4;

            while (choice < 1 || choice > maxChoice) {
                System.out.printf("Enter your choice (1-%d): ", maxChoice);
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    if (choice < 1 || choice > maxChoice) {
                        System.out.printf("Invalid input. Please enter a number between 1 and %d.\n", maxChoice);
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("Invalid input. Please enter a number between 1 and %d.\n", maxChoice);
                }
            }

            responses.add(String.valueOf(choice));
        }

        return responses;
    }

    public static String getAnswerText(String responseNumber) {
        switch (responseNumber) {
            case "1": return "Strongly Agree";
            case "2": return "Agree";
            case "3": return "Neutral";
            case "4": return "Disagree";
            case "5": return "Strongly Disagree";
            default: return "Unknown";
        }
    }

    public static List<String> getQuestions() {
        return QUESTIONS;
    }
}

