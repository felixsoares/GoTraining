package com.felix.gotraining.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.gotraining.R
import com.felix.gotraining.data.constants.Constants
import com.felix.gotraining.data.model.ResultState
import com.felix.gotraining.data.model.Training
import com.felix.gotraining.data.widget.TrainingAdapter
import com.felix.gotraining.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val adapter = TrainingAdapter(this::onSelectItem)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservers()
    }

    private fun onSelectItem(training: Training) {
        findNavController().navigate(
            R.id.action_homeFragment_to_trainingFragment,
            bundleOf(
                Constants.PARAM_TRAINING_ID to training.id,
                Constants.PARAM_TRAINING_NAME to training.name
            )
        )
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            when (viewState) {
                is ResultState.Loading -> setLoadingView()
                is ResultState.Error -> setEmptyView()
                is ResultState.Success -> setContentView(viewState.data)
            }
        })
    }

    private fun setContentView(trainings: List<Training>) {
        binding.btnAddTraining.isVisible = true
        binding.recyclerView.isVisible = true
        binding.txtEmpty.isVisible = false
        binding.progressCircular.isVisible = false
        adapter.updateTrainings(trainings)
    }

    private fun setEmptyView() {
        binding.btnAddTraining.isVisible = true
        binding.recyclerView.isVisible = false
        binding.txtEmpty.isVisible = true
        binding.progressCircular.isVisible = false
    }

    private fun setLoadingView() {
        binding.btnAddTraining.isVisible = false
        binding.recyclerView.isVisible = false
        binding.progressCircular.isVisible = true
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTrainings()
    }

    private fun setupView() {
        binding.btnAddTraining.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_formTrainingFragment)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
        binding.toolbar.title = "GoTraining"
    }

}