package com.felix.gotraining.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GroupExercise(
    val name: String,
    val exercises: List<Exercise>
) : Parcelable