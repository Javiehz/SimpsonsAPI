package edu.javieh.simpsonsapi.application.features.domain

class GetAllSimpsonsUseCase(private val simpsonsRepository: SimpsonsRepository) {
     operator fun invoke():List<Simpson>{
        return simpsonsRepository.getAll()
    }
}