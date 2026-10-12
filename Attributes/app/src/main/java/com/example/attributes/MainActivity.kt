package com.example.attributes

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val targetEditText = findViewById<EditText>(R.id.edit_text_target)

        findViewById<Button>(R.id.btn_black_text).setOnClickListener {
            targetEditText.setTextColor(Color.BLACK)
        }
        findViewById<Button>(R.id.btn_red_text).setOnClickListener {
            targetEditText.setTextColor(Color.RED)
        }

        findViewById<Button>(R.id.btn_size_8sp).setOnClickListener {
            targetEditText.setTextSize(8f)
        }
        findViewById<Button>(R.id.btn_size_24sp).setOnClickListener {
            targetEditText.setTextSize(24f)
        }

        findViewById<Button>(R.id.btn_white_bg).setOnClickListener {
            targetEditText.setBackgroundColor(Color.WHITE)
        }
        findViewById<Button>(R.id.btn_yellow_bg).setOnClickListener {
            targetEditText.setBackgroundColor(Color.YELLOW)
        }
    }
}