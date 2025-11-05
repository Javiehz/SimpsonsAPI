package edu.javieh.simpsonsapi.application.features.domain

interface SimpsonsRepository {
     suspend fun getAll():Result<List<Simpson>>
     suspend fun getById(id: Int):Result<Simpson>
}