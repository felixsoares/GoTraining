package com.felix.gotraining.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.gotraining.data.model.ResultState
import com.felix.gotraining.data.model.Training
import com.felix.gotraining.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TrainingRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ResultState<List<Training>>>()
    val viewState: LiveData<ResultState<List<Training>>> = _viewState

    fun getTrainings() {
        viewModelScope.launch {
            _viewState.postValue(ResultState.Loading)
            val trainings = repository.getAllTrainings()
            if (trainings.isEmpty()) {
                _viewState.postValue(ResultState.Error)
            } else {
                _viewState.postValue(ResultState.Success(trainings))
            }
        }
    }
}