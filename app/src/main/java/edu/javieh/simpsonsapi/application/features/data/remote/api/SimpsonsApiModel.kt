package edu.javieh.simpsonsapi.application.features.data.remote.api

import com.google.gson.annotations.SerializedName

data class SimpsonsApiModel(
    val count: Int,
    val next: String?,
    val prev: String?,
    val pages: Int,
    val results: List<CharacterApiModel>
)

data class CharacterApiModel(
    val id: Int,
    val age: Int?,
    val birthdate: String?,
    val gender: String,
    val name: String,
    val occupation: String,
    @SerializedName("portrait_path") val imageUrl: String,
    val phrases: List<String>
)