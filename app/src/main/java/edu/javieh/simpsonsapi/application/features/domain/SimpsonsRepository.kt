package edu.javieh.simpsonsapi.application.features.domain

interface SimpsonsRepository {
     suspend fun getAll():Result<List<Simpson>>
}