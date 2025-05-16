# LeanLens

**LeanLens** is a modern Java-based political affiliation prediction CLI app.  
It uses a short opinion survey and a Naive Bayes-style algorithm to predict a user's political leaning, providing a confidence percentage along with a colorized summary of responses.

---

## 🔍 Features

- 🔢 Number-based survey with refined political questions
- 🎯 Real-time prediction with confidence percentage
- 📊 Naive Bayes-style classification
- 🧠 Remembers responses to improve future accuracy
- 🎨 ANSI-colored CLI for a polished UX
- 🧱 Modular, well-structured Java classes
- 🛡️ Integrated logging for robust error handling

---

## 🧠 How It Works

1. The user completes a series of survey questions (Strongly Agree → Strongly Disagree).
2. At the end, they select their own political affiliation.
3. Their responses are saved and used to train the model.
4. The app predicts the user's likely political leaning based on prior training data.
5. The result includes a confidence score and a summary of their answers.

---

## 🚀 Getting Started

### React Frontend

### Prerequisites
- Node.js (v16 or higher)
- npm

### Installation & Run
1. Navigate to the frontend directory:
- cd leanlens-ui

2. Install dependencies:
- npm install

3. Start the development server:
- npm run dev
- App will run at http://localhost:5173.

## Java CLI App
### Prerequisites
- Java 21 or higher
- Maven

## Compile and Run
1. From the project root, compile the CLI with Maven:
- mvn compile

2. Run the application:
- mvn exec:java -Dexec.mainClass="leanlens.Main"

3. If exec-maven-plugin is not yet configured, you can run the compiled class manually:
- java -cp target/classes leanlens.Main

Follow the prompts in the terminal to complete the survey and receive your political affiliation prediction.

## 📁 Project Structure

```
src/
└── main/
    └── java/
        └── leanlens/
            ├── Main.java
            ├── SurveyApp.java
            ├── SurveyDataManager.java
            ├── PartyData.java
            └── NaiveBayesClassifier.java
data/
└── (training data files saved per political party)
```

---

## 🛠️ Future Improvements

- 📊 Visual confidence bar (e.g. ███████░░ 70%)
- 🧪 Add unit tests for core components
- 🌐 Web-based version (React/Flask)
- 📂 Save/load models in JSON format
- 🔁 Survey retry or session history

---

## 👨‍💻 Author

Graham Cockerham  
[github.com/GrahamC7](https://github.com/GrahamC7)

---

## 📜 License

This project is open source and free to use under the MIT License.ct is open source and free to use under the MIT License.