package com.felix.gotraining.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Training(
    val id: Long = 0,
    val name: String,
    val exercises: List<Exercise> = listOf()
) : Parcelable