package com.felix.gotraining.ui.formTraining

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.gotraining.data.model.Exercise
import com.felix.gotraining.data.model.GroupExercise
import com.felix.gotraining.data.model.ResultState
import com.felix.gotraining.data.model.Training
import com.felix.gotraining.data.repository.GroupExerciseRepository
import com.felix.gotraining.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class FormTrainingViewModel(
    private val groupExerciseRepository: GroupExerciseRepository,
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _exercisesState = MutableLiveData<ResultState<List<GroupExercise>>>()
    val exercisesState: LiveData<ResultState<List<GroupExercise>>> = _exercisesState

    private val _formState = MutableLiveData<ResultState<Boolean>>()
    val formState: LiveData<ResultState<Boolean>> = _formState

    private val selectedExercises = mutableListOf<Exercise>()

    fun loadExercisesByGroup() {
        viewModelScope.launch {
            _exercisesState.postValue(ResultState.Loading)
            val groups = groupExerciseRepository.getGroupsExercise()
            _exercisesState.postValue(ResultState.Success(groups))
        }
    }

    fun selectExercise(exercise: Exercise) {
        if (selectedExercises.contains(exercise)) {
            selectedExercises.remove(exercise)
        } else {
            selectedExercises.add(exercise)
        }
    }

    fun saveTraining(name: String) {
        viewModelScope.launch {
            _formState.postValue(ResultState.Loading)
            if (name.isNotEmpty() && selectedExercises.isNotEmpty()) {
                trainingRepository.saveTraining(
                    Training(
                        name = name,
                        exercises = selectedExercises
                    )
                )
                _formState.postValue(ResultState.Success(true))
            } else {
                _formState.postValue(ResultState.Error)
            }
        }
    }
}