package edu.javieh.simpsonsapi.application.features.presentation

import androidx.fragment.app.Fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.javieh.simpsonsapi.application.core.api.ApiClient
import edu.javieh.simpsonsapi.application.features.data.remote.SimpsonsDataRepository
import edu.javieh.simpsonsapi.application.features.data.remote.api.SimpsonsApiRemoteDataSource
import edu.javieh.simpsonsapi.application.features.domain.GetAllSimpsonsUseCase
import edu.javieh.simpsonsapi.databinding.FragmentSimpsonsListBinding

class SimpsonsListFragment : Fragment() {

    private var _binding: FragmentSimpsonsListBinding? = null
    private val binding get() = _binding!!

    private val simpsonsAdapter = SimpsonsAdapter { simpsonId ->
        val action =
            SimpsonsListFragmentDirections.actionSimpsonsListFragmentToSimpsonsDetailFragment(
                    simpsonId
                )

        findNavController().navigate(action)
    }

    private val dataRepository = SimpsonsDataRepository(
        SimpsonsApiRemoteDataSource(ApiClient())
    )
    private val listViewModel = SimpsonsViewModel(
        GetAllSimpsonsUseCase(dataRepository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        listViewModel.loadAllSimpsons()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpsonsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvSimpsonsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = simpsonsAdapter
        }
    }

    private fun setupObserver() {
        listViewModel.uiState.observe(this) { uiState ->
            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

            uiState.error?.let { error ->
                Log.e("@dev", "Error: $error")
            }

            uiState.simpsons?.let { simpsons ->
                simpsonsAdapter.submitList(simpsons)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}