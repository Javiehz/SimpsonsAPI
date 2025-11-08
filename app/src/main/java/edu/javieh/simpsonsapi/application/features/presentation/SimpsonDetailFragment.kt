package edu.javieh.simpsonsapi.application.features.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import edu.javieh.simpsonsapi.application.core.api.ApiClient
import edu.javieh.simpsonsapi.application.features.data.remote.SimpsonsDataRepository
import edu.javieh.simpsonsapi.application.features.data.remote.api.SimpsonsApiRemoteDataSource
import edu.javieh.simpsonsapi.application.features.domain.GetByIdSimpsonUseCase
import edu.javieh.simpsonsapi.databinding.FragmentSimpsonDetailBinding
import coil3.load

class SimpsonDetailFragment : Fragment() {

    private var _binding: FragmentSimpsonDetailBinding? = null
    private val binding get() = _binding!!

    private val dataRepository = SimpsonsDataRepository(
        SimpsonsApiRemoteDataSource(ApiClient())
    )
    private val detailViewModel = SimpsonViewModel(
        GetByIdSimpsonUseCase(dataRepository)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val simpsonId = SimpsonDetailFragmentArgs.fromBundle(requireArguments()).simpsonId

        setupObserver()
        detailViewModel.loadById(simpsonId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpsonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupObserver() {
        detailViewModel.uiState.observe(this) { uiState ->
            if (uiState.isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }

            uiState.error?.let { error ->
                Log.e("@dev", "Error cargando personaje: $error")
            }

            uiState.simpson?.let { simpson ->
                binding.tvName.text = simpson.name
                binding.tvOccupation.text = simpson.occupation
                binding.tvPhrases.text = simpson.phrases.joinToString("\n")
                binding.ivPhoto.load(simpson.imageUrl)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}