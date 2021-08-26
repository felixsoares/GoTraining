package com.felix.gotraining.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

interface ToggleRepository {
    fun isDatabaseAlreadyCreated(): Boolean
    fun markAsDatabaseCreated()
}

class ToggleRepositoryImpl(
    private val sharedPreferences: SharedPreferences
) : ToggleRepository {

    companion object {
        private const val DATABASE_PREFS_NAME = "DATABASE_PREFS_NAME"
        private const val DATABASE_PREFS_DEFAULT_VALUE = false
    }

    override fun isDatabaseAlreadyCreated(): Boolean =
        sharedPreferences.getBoolean(DATABASE_PREFS_NAME, DATABASE_PREFS_DEFAULT_VALUE)

    override fun markAsDatabaseCreated() {
        sharedPreferences.edit(commit = true) {
            putBoolean(DATABASE_PREFS_NAME, true)
        }
    }


}