package com.example.assigment2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Review : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val questions = intent.getStringArrayExtra("questions") ?: arrayOf()
        val userAnswers = intent.getStringArrayExtra("userAnswers") ?: arrayOf()
        val correctAnswers = intent.getStringArrayExtra("correctAnswers") ?: arrayOf()

        val reviewContainer = findViewById<LinearLayout>(R.id.reviewContainer)
        val reviewAgainButton = findViewById<Button>(R.id.btnReviewAgain)
        val exitAppButton = findViewById<Button>(R.id.btnExitApp)

        // Show review items
        for (i in questions.indices) {
            val reviewText = TextView(this).apply {
                text = """
                    Q${i + 1}: ${questions[i]}
                    Your Answer: ${userAnswers.getOrNull(i) ?: "No Answer"}
                    Correct Answer: ${correctAnswers.getOrNull(i) ?: "No Answer"}
                """.trimIndent()
                textSize = 16f
                setTextColor(Color.WHITE) // This makes the answers white
                setPadding(16, 24, 16, 24)
            }
            reviewContainer.addView(reviewText)
        }

        reviewAgainButton.setOnClickListener {
            val intent = Intent(this, Questions::class.java)
            startActivity(intent)
            finish()
        }

        exitAppButton.setOnClickListener {
            finishAffinity()
        }
    }
}