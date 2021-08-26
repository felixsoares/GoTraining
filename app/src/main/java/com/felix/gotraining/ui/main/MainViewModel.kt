package com.felix.gotraining.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.gotraining.data.local.ToggleRepository
import com.felix.gotraining.data.repository.GroupExerciseRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: GroupExerciseRepository,
    private val toggleRepository: ToggleRepository
) : ViewModel() {

    fun saveGroupsExerciseIfNecessary(json: String) = viewModelScope.launch {
        if (!toggleRepository.isDatabaseAlreadyCreated()) {
            repository.saveGroupsExercise(json)
            toggleRepository.markAsDatabaseCreated()
        }
    }

}