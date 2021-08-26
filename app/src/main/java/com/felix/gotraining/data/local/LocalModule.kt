package com.felix.gotraining.data.local

import android.content.Context
import android.content.SharedPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    factory<SharedPreferences> {
        androidContext()
            .getSharedPreferences("GOTRAINING_APP_PREFS", Context.MODE_PRIVATE)
    }

    single<ToggleRepository> {
        ToggleRepositoryImpl(sharedPreferences = get())
    }
}