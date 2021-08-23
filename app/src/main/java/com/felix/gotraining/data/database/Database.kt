package com.felix.gotraining.data.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                "go_training_database"
            )
            .build()
    }

    single { get<AppDatabase>().getExerciseDao() }
    single { get<AppDatabase>().getGroupDao() }
    single { get<AppDatabase>().getTrainingDao() }
    single { get<AppDatabase>().getSelectedExerciseDao() }
    single { get<AppDatabase>().getWeightDao() }
}