package com.felix.gotraining.ui.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainUiModule = module {
    viewModel { MainViewModel(repository = get()) }
}