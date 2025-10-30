package edu.javieh.simpsonsapi.application.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiClient {

    val BASE_URL = "https://thesimpsonsapi.com/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(typeClass:Class<T>):T{
        return retrofit.create(typeClass)
    }   
}