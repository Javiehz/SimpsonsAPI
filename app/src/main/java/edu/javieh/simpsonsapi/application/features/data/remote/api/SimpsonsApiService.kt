package edu.javieh.simpsonsapi.application.features.data.remote.api
import retrofit2.Response
import retrofit2.http.GET

interface SimpsonsApiService {

    @GET("characters")
    suspend fun getAll(): Response<SimpsonsApiModel>
}