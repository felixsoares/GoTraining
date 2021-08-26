package com.felix.gotraining.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.felix.gotraining.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val json =
            resources.openRawResource(R.raw.gotraining)
                .bufferedReader()
                .use { it.readText() }

        viewModel.saveGroupsExerciseIfNecessary(json)
    }
}