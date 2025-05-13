package com.example.assigment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Questions : AppCompatActivity() {

    private val questions = arrayOf(
        "Lionel Messi has won more Ballon d'Or awards than Cristiano Ronaldo",
        "The FIFA World Cup is held every 6 years",
        "Brazil has won the most FIFA World Cups",
        "The Premier League is based in Germany",
        "Maradona scored his iconic goal in the 2010 FIFA WORLD CUP"
    )
    private val answers = booleanArrayOf(true, false, true, false, false)

    private var currentIndex = 0
    private var score = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        // Apply edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views inside onCreate
        val questionsView = findViewById<TextView>(R.id.questionsView)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val nextButton = findViewById<Button>(R.id.Nextbutton)

        // Set the first question
        questionsView.text = questions[currentIndex]

        fun checkAnswer(userAnswer: Boolean) {
            val correct = answers[currentIndex] == userAnswer
            if (correct) {
                feedbackText.text = "Correct!"
                score++
            } else {
                feedbackText.text = "Incorrect"
            }

            Log.d(
                "QuizLog",
                "Question ${currentIndex + 1}: ${questions[currentIndex]} - Your Answer: $userAnswer - Correct: ${answers[currentIndex]}"
            )
        }

        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                questionsView.text = questions[currentIndex]
                feedbackText.text = ""
            } else {
                val intent = Intent(this, Score::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                finish()
            }
        }
    }
}

