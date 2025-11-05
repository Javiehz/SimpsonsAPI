package edu.javieh.simpsonsapi.application.features.domain

class GetByIdSimpsonUseCase(private val simpsonsRepository: SimpsonsRepository) {
    suspend operator fun invoke(id: Int):Result<Simpson>{
        return simpsonsRepository.getById(id)
    }
}