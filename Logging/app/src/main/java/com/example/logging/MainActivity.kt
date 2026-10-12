package com.example.logging

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        val editText = findViewById<EditText>(R.id.editText)
        val buttonLog = findViewById<Button>(R.id.button_log)
        val buttonTimber = findViewById<Button>(R.id.button_timber)

        buttonLog.setOnClickListener {
            val textToLog = editText.text.toString()
            Log.v("From EditText", textToLog)
        }

        buttonTimber.setOnClickListener {
            val textToLog = editText.text.toString()
            Timber.v(textToLog)
        }
    }
}