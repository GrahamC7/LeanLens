# LeanLens

**LeanLens** is a Java-based political affiliation prediction app that uses survey responses and a simple Naive Bayes-style algorithm to classify user responses as Democratic, Republican, Independent, or Other.

---

## 🔍 Features

- Interactive CLI-based survey experience
- Real-time political affiliation prediction
- Naive Bayes-style classifier
- Customizable survey questions and responses
- Trains using previous user responses (stored in plain text)
- Modular, readable code structure

---

## 🧠 How It Works

1. The user takes a short political opinion survey.
2. Responses are compared to historical trends per party.
3. LeanLens uses a probability model to predict the likely political leaning.
4. The user's responses are saved to further train the system.

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven (optional, if you want to build via `pom.xml`)

### Run the App

```bash
# If using IntelliJ or any IDE, simply run Main.java
# OR run from command line:
javac -d out src/main/java/leanlens/*.java
java -cp out leanlens.Main
```

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
└── (survey training data files)
```

---

## 🛠️ Future Improvements

- Add scoring confidence or heatmap-style feedback
- Support saving/exporting data in JSON or CSV
- GUI interface (JavaFX or web-based)
- Model serialization and smarter learning over time

---

## 👨‍💻 Author

Graham Cockerham  
[github.com/GrahamC7](https://github.com/GrahamC7)

---

## 📜 License

This project is open source and free to use under the MIT License.