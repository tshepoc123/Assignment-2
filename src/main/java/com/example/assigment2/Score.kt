package com.example.assigment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Score : AppCompatActivity() {
    @SuppressLint("MissingInflatedId" , "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val score = intent.getIntExtra("score", 0)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val btnExit = findViewById<Button>(R.id.btnExit)

        txtScore.text = "Your Score: $score / 5"

        feedbackText.text = if (score >= 3) {
            "Great job!"
        } else {
            "Keep practising!"
        }

        // This will restart the quiz (or take to a review screen if you have one)
        reviewButton.setOnClickListener {
            val intent = Intent(this, Review::class.java) // or ReviewActivity if you have one
            startActivity(intent)
            finish()
        }

        // This will exit the app
        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}


