package edu.javieh.simpsonsapi.application.features.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpsonsApiService {

    @GET("characters")
    suspend fun getAll(): Response<SimpsonsApiModel>

    @GET("characters/{simpsonsId}")
    suspend fun getById(@Path("simpsonsId") id: Int): Response<SimpsonApiModel>
}