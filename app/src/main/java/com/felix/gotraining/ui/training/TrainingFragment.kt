package com.felix.gotraining.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.gotraining.data.constants.Constants
import com.felix.gotraining.data.widget.ViewDialog
import com.felix.gotraining.data.widget.WeightAdapter
import com.felix.gotraining.databinding.FragmentTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrainingFragment : Fragment() {

    private lateinit var binding: FragmentTrainingBinding
    private val viewModel: TrainingViewModel by viewModel()
    private lateinit var weightAdapter: WeightAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
        val id = arguments?.getLong(Constants.PARAM_TRAINING_ID, 0) ?: 0
        viewModel.loadTraining(id)
    }

    private fun setupObservers() {
        viewModel.exercise.observe(this, { exercise ->
            binding.txtExerciseName.text = exercise.name
            binding.txtRepeatValue.text = "${exercise.repeat}"
            binding.txtSerieValue.text = "${exercise.range}"

            weightAdapter = WeightAdapter(exercise.weights ?: mutableListOf())
            binding.recyclerView.adapter = weightAdapter
        })

        viewModel.weights.observe(this, { weights ->
            weightAdapter.updateWeights(weights)
        })
    }

    private fun setupView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val name = arguments?.getString(Constants.PARAM_TRAINING_NAME, "") ?: ""
        binding.toolbar.title = name


        binding.btnPrev.setOnClickListener { viewModel.loadPrevExercise() }
        binding.btnProx.setOnClickListener { viewModel.loadNextExercise() }
        binding.btnAddWeight.setOnClickListener {
            ViewDialog.showWeightDialog(requireContext()) { weight ->
                viewModel.setWeight(weight)
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}