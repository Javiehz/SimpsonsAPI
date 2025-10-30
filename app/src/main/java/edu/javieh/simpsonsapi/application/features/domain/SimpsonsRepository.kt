package edu.javieh.simpsonsapi.application.features.domain

interface SimpsonsRepository {
     fun getAll():List<Simpson>
}