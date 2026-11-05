package com.example.unscramble.data

// Тестовый хелпер: по перемешанному слову возвращает исходное (сравнение по отсортированным буквам)
private val wordsBySortedLetters: Map<String, String> =
    allWords.associateBy { it.toCharArray().sorted().joinToString("") }

fun getUnscrambledWord(scrambledWord: String): String =
    wordsBySortedLetters[scrambledWord.toCharArray().sorted().joinToString("")] ?: ""
