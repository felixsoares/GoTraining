package com.felix.gotraining.ui.formTraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.gotraining.data.model.GroupExercise
import com.felix.gotraining.data.model.ResultState
import com.felix.gotraining.data.widget.GroupExerciseAdapter
import com.felix.gotraining.databinding.FragmentFormTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormTrainingFragment : Fragment() {

    private lateinit var binding: FragmentFormTrainingBinding
    private val viewModel: FormTrainingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormTrainingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
        viewModel.loadExercisesByGroup()
    }

    private fun setupView() {
        binding.btnSave.setOnClickListener {
            viewModel.saveTraining(
                binding.editName.text.toString()
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.toolbar.title = "Criar treino"
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupObservers() {
        viewModel.exercisesState.observe(this, Observer { resultState ->
            when (resultState) {
                is ResultState.Success -> setExercisesView(resultState.data)
                is ResultState.Loading -> setLoadingView()
                else -> Unit
            }
        })

        viewModel.formState.observe(this, Observer { resultState ->
            when (resultState) {
                is ResultState.Success -> findNavController().popBackStack()
                is ResultState.Error -> showErrorMessage()
                is ResultState.Loading -> setFormLoadingView()
            }
        })
    }

    private fun setFormLoadingView() {
        binding.textFieldName.isEnabled = false
        binding.progressCircular.isVisible = true
        binding.btnSave.isEnabled = false
    }

    private fun setExercisesView(groupExercises: List<GroupExercise>) {
        binding.progressCircular.isVisible = false
        binding.btnSave.isEnabled = true
        binding.recyclerView.adapter =
            GroupExerciseAdapter(groupExercises, viewModel::selectExercise)
    }

    private fun showErrorMessage() {
        binding.textFieldName.isEnabled = true
        binding.progressCircular.isVisible = false
        binding.btnSave.isEnabled = true

        val alertDialog = AlertDialog
            .Builder(requireContext())
            .setTitle("Oops")
            .setMessage("Parece que você esqueceu de preencher o formulário corretamente. Digite o nome desse treino e escolha os exercícios que deseja fazer")
            .setPositiveButton("Ok") { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        val dialog = alertDialog.create()
        dialog.show()
    }

    private fun setLoadingView() {
        binding.progressCircular.isVisible = true
        binding.btnSave.isEnabled = false
    }

}