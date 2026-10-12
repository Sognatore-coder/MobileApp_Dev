package com.example.topicsgrid

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
data class Topic(
    @StringRes val stringResourceId: Int,
    val numberOfCourses: Int,
    @DrawableRes val imageResourceId: Int
)
