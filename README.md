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

### Prerequisites

- Java 21 or higher
- Maven (optional)
- Node.js (v16 or higher)
- npm (v7 or higher)

### Run the App

Installation

Clone the repository:

git clone https://github.com/GrahamC7/LeanLens.git
cd LeanLens

Install dependencies for the frontend:

cd leanlens-ui
npm install

Start the development server:
npm run dev
This will launch the app at http://localhost:5173.

---

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