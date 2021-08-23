package com.felix.gotraining

import android.app.Application
import com.felix.gotraining.data.database.databaseModule
import com.felix.gotraining.data.repository.repositoryModule
import com.felix.gotraining.ui.formTraining.formTrainingModule
import com.felix.gotraining.ui.home.homeModule
import com.felix.gotraining.ui.main.mainUiModule
import com.felix.gotraining.ui.training.trainingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GoApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    mainUiModule,
                    homeModule,
                    formTrainingModule,
                    trainingModule
                )
            )
        }
    }
}