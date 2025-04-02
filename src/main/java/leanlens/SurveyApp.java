package leanlens;

import java.util.Scanner;

public class SurveyApp {
    private static final String[] QUESTIONS = {
            "Should there be stricter gun control laws?",
            "How open should our borders be to immigrants?",
            "Should the government invest more in public education, or should there be more school choice options?",
            "What is your opinion on climate change?",
            "Do you believe everyone deserves access to affordable healthcare, or should it be primarily a private market?",
            "Which political party do you affiliate with?"
    };

    private static final String[][] ANSWER_CHOICES = {
            { "A. Strongly agree", "B. Agree", "C. Neutral", "D. Disagree", "E. Strongly disagree" },
            { "A. Completely open borders", "B. Controlled immigration", "C. Closed borders" },
            { "A. More investment in public education", "B. More school choice options", "C. Balanced approach" },
            { "A. Climate change is a serious threat that requires immediate action",
                    "B. Climate change is a natural phenomenon", "C. Climate change is exaggerated",
                    "D. Climate change is a hoax" },
            { "A. Access to affordable healthcare for all", "B. Healthcare as a private market", "C. Other" },
            { "A. Democratic Party", "B. Republican Party", "C. Independent", "D. Other" }
    };

    public String[] conductSurvey() {
        Scanner scanner = new Scanner(System.in);
        String[] responses = new String[QUESTIONS.length];

        for (int i = 0; i < QUESTIONS.length; i++) {
            System.out.println("\nQuestion " + (i + 1) + ": " + QUESTIONS[i]);
            System.out.println("Options:");
            for (String choice : ANSWER_CHOICES[i]) {
                System.out.println(choice);
            }
            System.out.print("Your answer: ");
            responses[i] = scanner.nextLine().toUpperCase();
        }

        return responses;
    }
}