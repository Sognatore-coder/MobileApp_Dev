package com.example.artpeformerview.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ArtPerformerUiState(
    val currentStep: Int = 1,
    @DrawableRes val imageResourceId: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val artistResourceId: Int
)