package com.felix.gotraining.ui.training

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trainingModule = module {
    viewModel {
        TrainingViewModel(
            trainingRepository = get(),
            weightRepository = get()
        )
    }
}