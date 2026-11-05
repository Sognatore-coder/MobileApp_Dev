package com.example.unscramble.ui.test

import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.allWords
import com.example.unscramble.data.getUnscrambledWord
import com.example.unscramble.ui.GameViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    // ---------- путь успеха ----------
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    // ---------- путь ошибки ----------
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWord = "and"

        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()

        val currentGameUiState = viewModel.uiState.value
        // Счёт не изменился, флаг ошибки выставлен
        assertEquals(0, currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
        // Номер слова не изменился, игра не окончена, ввод сброшен
        assertEquals(1, currentGameUiState.currentWordCount)
        assertFalse(currentGameUiState.isGameOver)
        assertEquals("", viewModel.userGuess)
    }

    // ---------- граничный случай: старт игры ----------
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambledWord)

        // Слово загружено, перемешано и есть в списке слов
        assertTrue(gameUiState.currentScrambledWord.isNotEmpty())
        assertNotEquals(unScrambledWord, gameUiState.currentScrambledWord)
        assertTrue(allWords.contains(unScrambledWord))
        // Начальные значения
        assertEquals(1, gameUiState.currentWordCount)
        assertEquals(0, gameUiState.score)
        assertFalse(gameUiState.isGuessedWordWrong)
        assertFalse(gameUiState.isGameOver)
    }

    // ---------- граничный случай: максимум слов ----------
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            // После каждого верного ответа счёт растёт, ошибки нет
            assertEquals(expectedScore, currentGameUiState.score)
            assertFalse(currentGameUiState.isGuessedWordWrong)
        }

        // Итог: счёт максимальный, счётчик слов на максимуме, игра окончена
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertEquals(MAX_NO_OF_WORDS * SCORE_INCREASE, currentGameUiState.score)
        assertTrue(currentGameUiState.isGameOver)
    }

    // ---------- пропуск слова ----------
    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        // Сначала набираем очки, чтобы проверка "счёт не изменился" была содержательной
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount

        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value

        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
        assertFalse(currentGameUiState.isGuessedWordWrong)
        assertEquals("", viewModel.userGuess)
    }

    // ---------- сброс игры (дополнительно, закрывает resetGame по состоянию) ----------
    @Test
    fun gameViewModel_ResetGame_StateRestored() {
        viewModel.skipWord()
        viewModel.resetGame()

        val state = viewModel.uiState.value
        assertEquals(1, state.currentWordCount)
        assertEquals(0, state.score)
        assertFalse(state.isGameOver)
    }

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }
}
