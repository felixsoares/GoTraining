package com.felix.gotraining.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weight(
    val id: Long,
    val value: Int
) : Parcelable