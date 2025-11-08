package edu.javieh.simpsonsapi.application.features.data.remote.api

import edu.javieh.simpsonsapi.application.core.api.ApiClient
import edu.javieh.simpsonsapi.application.features.domain.ErrorApp
import edu.javieh.simpsonsapi.application.features.domain.Simpson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimpsonsApiRemoteDataSource(private val apiClient: ApiClient) {
    val apiService = apiClient.createService(SimpsonsApiService::class.java)

    suspend fun getAllSimpsons(): Result<List<Simpson>> {
        return withContext(Dispatchers.IO) {
            try {
                val apiResults = apiService.getAll()

                if (apiResults.isSuccessful && apiResults.body() != null) {

                    val simpsonsApiModel: SimpsonsApiModel = apiResults.body()!!
                    val listSimpsonsApiModel: List<CharacterApiModel> = simpsonsApiModel.results

                    val simpsonModel = listSimpsonsApiModel.map { simpsonApiModel ->
                        simpsonApiModel.toModel()
                    }

                    Result.success(simpsonModel)

                } else {
                    Result.failure(ErrorApp.ServerError)
                }

            } catch (e: Exception) {
                Result.failure(ErrorApp.InternetError)
            }
        }
    }

    suspend fun getByIdSimpson(id: Int): Result<Simpson> {
        return withContext(Dispatchers.IO) {
            val apiResults = apiService.getById(id)
            if (apiResults.isSuccessful && apiResults.errorBody() == null) {
                val simpsonApiModel: CharacterApiModel = apiResults.body()!!
                val simpson = simpsonApiModel.toModel()
                Result.success(simpson)
            } else {
                Result.failure(ErrorApp.ServerError)
            }
        }
    }
}