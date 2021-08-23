package com.felix.gotraining.data.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<GroupExerciseRepository> {
        GroupExerciseRepositoryImpl(
            groupDao = get(),
            exerciseDao = get()
        )
    }
    single<TrainingRepository> {
        TrainingRepositoryImpl(
            trainingDao = get(),
            selectedInterfaceDao = get()
        )
    }
    single<WeightRepository> {
        WeightRepositoryImpl(
            weightDao = get()
        )
    }
}