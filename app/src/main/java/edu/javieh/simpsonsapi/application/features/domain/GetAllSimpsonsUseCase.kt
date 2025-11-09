package edu.javieh.simpsonsapi.application.features.domain

class GetAllSimpsonsUseCase(private val simpsonsRepository: SimpsonsRepository) {
    suspend operator fun invoke(): Result<List<Simpson>> {
        return simpsonsRepository.getAll()
    }
}