import { useState } from "react";

const QUESTIONS = [
    "Healthcare should be universal and government-funded.",
    "Taxes should be increased on the wealthy.",
    "The government should regulate environmental practices of corporations.",
    "Gun ownership is a fundamental right that should not be infringed.",
    "Immigration should be more restricted than it currently is.",
];

function App() {
    const [currentIndex, setCurrentIndex] = useState(0);
    const [answers, setAnswers] = useState([]);

    const handleAnswer = (value) => {
        const updated = [...answers, value];
        setAnswers(updated);

        setAnswers(updated);
        setCurrentIndex(currentIndex + 1);

    };

    return (
        <div className="min-h-screen bg-gradient-to-br from-sky-500 to-indigo-700 flex items-center justify-center p-4">
            <div className="bg-white rounded-2xl shadow-lg p-8 max-w-xl w-full text-gray-800">
                <h1 className="text-2xl font-bold mb-4 text-center">LeanLens Survey</h1>

                {currentIndex < QUESTIONS.length ? (
                    <>
                        <p className="text-sm text-center mb-6">
                            How strongly do you agree with the following statement:
                        </p>
                        <h2 className="text-lg font-semibold mb-6 text-center">
                            {QUESTIONS[currentIndex]}
                        </h2>

                        <div className="grid grid-cols-1 sm:grid-cols-5 gap-3">
                            {[1, 2, 3, 4, 5].map((val) => (
                                <button
                                    key={val}
                                    onClick={() => handleAnswer(val)}
                                    className="bg-sky-100 hover:bg-sky-300 transition-colors text-sm sm:text-base text-sky-900 font-medium py-2 rounded-lg shadow text-center"
                                >
                                    {val}
                                </button>
                            ))}
                        </div>
                    </>
                ) : (
                    <div className="text-center">
                        <h2 className="text-xl font-semibold text-green-600 mb-2">
                            Survey Complete!
                        </h2>
                        <p className="text-gray-700">
                            Thanks for your responses. Processing your alignment...
                        </p>
                    </div>
                )}
            </div>
        </div>
    );
}

export default App;

