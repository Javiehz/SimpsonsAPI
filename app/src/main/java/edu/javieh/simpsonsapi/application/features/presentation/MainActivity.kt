package edu.javieh.simpsonsapi.application.features.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import edu.javieh.simpsonsapi.R
import edu.javieh.simpsonsapi.application.core.api.ApiClient
import edu.javieh.simpsonsapi.application.features.data.remote.SimpsonsDataRepository
import edu.javieh.simpsonsapi.application.features.data.remote.api.SimpsonsApiRemoteDataSource
import edu.javieh.simpsonsapi.application.features.domain.GetAllSimpsonsUseCase
import edu.javieh.simpsonsapi.application.features.domain.GetByIdSimpsonUseCase
import edu.javieh.simpsonsapi.application.features.domain.SimpsonsRepository

class MainActivity : AppCompatActivity() {

    private val repository = SimpsonsDataRepository(SimpsonsApiRemoteDataSource(ApiClient()))

    //private val viewModel = SimpsonsViewModel(GetAllSimpsonsUseCase(repository))

    private val viewModel = SimpsonViewModel(GetByIdSimpsonUseCase(repository))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObserver()
        viewModel.loadById(3)

    }
    fun setupObserver(){
        val observer = Observer<SimpsonViewModel.UiState>{ uiState ->
            if(uiState.isLoading){
                //SPINNER
            } else {
                //OCULTAMOS SPINNER
            }

            if(uiState.error != null){
                //ERROR
            }

            uiState.simpson

        }
    viewModel.uiState.observe(this, observer)
    }

}