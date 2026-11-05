package com.example.unscramble.data

const val MAX_NO_OF_WORDS = 10
const val SCORE_INCREASE = 20

// Слова без анаграмм между собой (важно для тестового хелпера getUnscrambledWord)
val allWords: Set<String> = setOf(
    "animal", "autumn", "basket", "candle", "dragon",
    "forest", "garden", "island", "jacket", "kitten",
    "lemon", "market", "orange", "planet", "rocket",
    "silver", "tunnel", "window"
)
