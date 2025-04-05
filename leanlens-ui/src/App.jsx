import { useState } from "react";
import { motion, AnimatePresence } from "framer-motion";

const QUESTIONS = [
    {
        text: "Healthcare should be universal and government-funded.",
        category: "Liberal",
    },
    {
        text: "Taxes should be increased on the wealthy.",
        category: "Liberal",
    },
    {
        text: "The government should regulate environmental practices of corporations.",
        category: "Moderate",
    },
    {
        text: "Gun ownership is a fundamental right that should not be infringed.",
        category: "Conservative",
    },
    {
        text: "Immigration should be more restricted than it currently is.",
        category: "Conservative",
    },
];

const ANSWER_LABELS = [
    "Strongly Agree",
    "Agree",
    "Neutral",
    "Disagree",
    "Strongly Disagree",
];

function App() {
    const [currentIndex, setCurrentIndex] = useState(0);
    const [answers, setAnswers] = useState([]);

    const handleAnswer = (value) => {
        const updated = [...answers, value];
        setAnswers(updated);
        setCurrentIndex(currentIndex + 1);
    };

    const getPrediction = () => {
        const avg = answers.reduce((sum, val) => sum + val, 0) / answers.length;
        if (avg <= 2) return "Liberal";
        if (avg <= 3.5) return "Moderate";
        return "Conservative";
    };

    const getPredictionStyle = () => {
        const prediction = getPrediction();
        switch (prediction) {
            case "Liberal":
                return {
                    label: "You lean Liberal 🌊",
                    color: "text-blue-600",
                };
            case "Moderate":
                return {
                    label: "You are Politically Moderate ⚖️",
                    color: "text-yellow-600",
                };
            case "Conservative":
                return {
                    label: "You lean Conservative 🦅",
                    color: "text-red-600",
                };
            default:
                return {
                    label: "Undetermined 🤷‍♂️",
                    color: "text-gray-600",
                };
        }
    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-sky-500 to-indigo-700 flex items-center justify-center p-4">
            <div className="bg-white rounded-2xl shadow-lg p-8 max-w-xl w-full text-gray-800">
                <h1 className="text-2xl font-bold mb-4 text-center">LeanLens Survey</h1>

                <div className="w-full h-2 bg-gray-200 rounded-full overflow-hidden mb-6">
                    <div
                        className="h-full bg-indigo-500 transition-all duration-300"
                        style={{ width: `${(currentIndex / QUESTIONS.length) * 100}%` }}
                    />
                </div>

                {currentIndex < QUESTIONS.length ? (
                    <>
                        <AnimatePresence mode="wait">
                            <motion.div
                                key={currentIndex}
                                initial={{ opacity: 0, y: 20 }}
                                animate={{ opacity: 1, y: 0 }}
                                exit={{ opacity: 0, y: -20 }}
                                transition={{ duration: 0.3 }}
                            >
                                <p className="text-sm text-center mb-6">
                                    How strongly do you agree with the following statement:
                                </p>
                                <h2 className="text-lg font-semibold mb-6 text-center">
                                    {QUESTIONS[currentIndex].text}
                                </h2>

                                <div className="grid grid-cols-1 sm:grid-cols-5 gap-3">
                                    {[1, 2, 3, 4, 5].map((val) => (
                                        <motion.button
                                            key={val}
                                            whileHover={{ scale: 1.05 }}
                                            whileTap={{ scale: 0.95 }}
                                            onClick={() => handleAnswer(val)}
                                            className="bg-sky-100 hover:bg-sky-300 transition-colors text-sm sm:text-base text-sky-900 font-medium py-2 rounded-lg shadow text-center"
                                        >
                                            {val}
                                        </motion.button>
                                    ))}
                                </div>
                            </motion.div>
                        </AnimatePresence>
                    </>
                ) : (
                    <motion.div
                        initial={{ opacity: 0 }}
                        animate={{ opacity: 1 }}
                        transition={{ duration: 0.5 }}
                    >
                        <h2 className="text-xl font-semibold text-green-600 mb-4 text-center">
                            Survey Complete!
                        </h2>
                        <p className="text-gray-700 text-center mb-6">
                            Here's a summary of your responses:
                        </p>

                        <ul className="space-y-4">
                            {QUESTIONS.map((question, index) => (
                                <li key={index} className="bg-gray-100 p-4 rounded-xl shadow-sm">
                                    <p className="font-semibold text-gray-800 mb-1">{question.text}</p>
                                    <p className="text-sm text-gray-600">
                                        You answered: <span className="font-medium text-indigo-700">{ANSWER_LABELS[answers[index] - 1]} (#{answers[index]})</span>
                                    </p>
                                    <p className="text-xs text-gray-500 italic mt-1">
                                        Category: {question.category}
                                    </p>
                                </li>
                            ))}
                        </ul>

                        <div className="mt-8 text-center">
                            <h3 className="text-lg font-bold text-indigo-700 mb-2">Predicted Alignment:</h3>
                            <p className={`text-xl font-semibold ${getPredictionStyle().color}`}>
                                {getPredictionStyle().label}
                            </p>
                        </div>

                        <div className="mt-8 text-center">
                            <button
                                onClick={() => {
                                    setAnswers([]);
                                    setCurrentIndex(0);
                                }}
                                className="bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-2 px-6 rounded-full transition-colors"
                            >
                                Retake Survey
                            </button>
                        </div>
                    </motion.div>
                )}
            </div>
        </div>
    );
}

export default App;