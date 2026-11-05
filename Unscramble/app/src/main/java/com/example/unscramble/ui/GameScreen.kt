package com.example.unscramble.ui

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscramble.R

@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            wordCount = gameUiState.currentWordCount,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            userGuess = gameViewModel.userGuess,
            onUserGuessChanged = { gameViewModel.updateUserGuess(it) },
            onKeyboardDone = { gameViewModel.checkUserGuess() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { gameViewModel.checkUserGuess() }
            ) { Text(stringResource(R.string.submit)) }

            OutlinedButton(
                onClick = { gameViewModel.skipWord() },
                modifier = Modifier.fillMaxWidth()
            ) { Text(stringResource(R.string.skip)) }
        }
        GameStatus(score = gameUiState.score, modifier = Modifier.padding(20.dp))

        if (gameUiState.isGameOver) {
            FinalScoreDialog(
                score = gameUiState.score,
                onPlayAgain = { gameViewModel.resetGame() }
            )
        }
    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            text = stringResource(R.string.score, score),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    wordCount: Int,
    isGuessWrong: Boolean,
    userGuess: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = stringResource(R.string.word_count, wordCount),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = currentScrambledWord,
                fontSize = 45.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(R.string.instructions),
                style = MaterialTheme.typography.titleMedium
            )
            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = onUserGuessChanged,
                label = {
                    if (isGuessWrong) Text(stringResource(R.string.wrong_guess))
                    else Text(stringResource(R.string.enter_your_word))
                },
                isError = isGuessWrong,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { onKeyboardDone() })
            )
        }
    }
}

@Composable
private fun FinalScoreDialog(score: Int, onPlayAgain: () -> Unit, modifier: Modifier = Modifier) {
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = { },
        title = { Text(stringResource(R.string.congratulations)) },
        text = { Text(stringResource(R.string.you_scored, score)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = { activity.finish() }) { Text(stringResource(R.string.exit)) }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) { Text(stringResource(R.string.play_again)) }
        }
    )
}
