package com.felix.gotraining.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val id: Long,
    val name: String,
    val number: String,
    var selected: Boolean = false,
    var range: Int = 0,
    var repeat: Int = 0,
    var weights: MutableList<Weight>? = null
) : Parcelable
