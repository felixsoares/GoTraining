package com.felix.gotraining.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.gotraining.data.repository.GroupExerciseRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: GroupExerciseRepository
) : ViewModel() {

    fun saveGroupsExerciseIfNecessary(json: String) = viewModelScope.launch {
        if (false) {
            repository.saveGroupsExercise(json)
        }
    }

}