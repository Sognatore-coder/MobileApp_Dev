package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    private val TAG = "ActivityLifecycleTAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() вызван — Слой инициализации UI")
        setContent {
            MaterialTheme {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Отслеживание Жизненного Цикла в Logcat")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() вызван — Экран становится видимым")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() вызван — Приложение в фокусе, готово к вводу")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() вызван — Фокус частично потерян")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() вызван — Приложение скрыто")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() вызван — Возврат к существующему экземпляру")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() вызван — Активность уничтожается")
    }
}