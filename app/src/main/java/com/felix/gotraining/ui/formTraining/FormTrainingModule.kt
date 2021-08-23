package com.felix.gotraining.ui.formTraining

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val formTrainingModule = module {
    viewModel {
        FormTrainingViewModel(
            groupExerciseRepository = get(),
            trainingRepository = get()
        )
    }
}