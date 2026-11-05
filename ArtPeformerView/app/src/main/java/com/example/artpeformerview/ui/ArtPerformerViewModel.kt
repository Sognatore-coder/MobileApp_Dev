package com.example.artpeformerview.ui

import com.example.artpeformerview.R;
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtPerformerViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(createUiStateForStep(1))
    val uiState: StateFlow<ArtPerformerUiState> = _uiState.asStateFlow()

    fun nextArtwork() {
        _uiState.update { currentState ->
            val nextStep = if (currentState.currentStep < 3) currentState.currentStep + 1 else 1
            createUiStateForStep(nextStep)
        }
    }

    fun previousArtwork() {
        _uiState.update { currentState ->
            val prevStep = if (currentState.currentStep > 1) currentState.currentStep - 1 else 3
            createUiStateForStep(prevStep)
        }
    }

    private fun createUiStateForStep(step: Int): ArtPerformerUiState {
        val image = when (step) {
            1 -> R.drawable.xxx
            2 -> R.drawable.phara
            else -> R.drawable.trip
        }
        val title = when (step) {
            1 -> R.string.art1_release
            2 -> R.string.art2_release
            else -> R.string.art3_release
        }
        val artist = when (step) {
            1 -> R.string.art1_artist
            2 -> R.string.art2_artist
            else -> R.string.art3_artist
        }
        return ArtPerformerUiState(currentStep = step, imageResourceId = image, titleResourceId = title, artistResourceId = artist)
    }
}