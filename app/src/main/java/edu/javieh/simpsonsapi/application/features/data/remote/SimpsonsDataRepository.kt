package edu.javieh.simpsonsapi.application.features.data.remote

import edu.javieh.simpsonsapi.application.features.data.remote.api.SimpsonsApiRemoteDataSource
import edu.javieh.simpsonsapi.application.features.domain.Simpson
import edu.javieh.simpsonsapi.application.features.domain.SimpsonsRepository

class SimpsonsDataRepository(private val simpsonsApiRemoteDataSource: SimpsonsApiRemoteDataSource): SimpsonsRepository {
    override suspend fun getAll(): Result<List<Simpson>> {
        return simpsonsApiRemoteDataSource.getAllSimpsons()
    }

    override suspend fun getById(id: Int): Result<Simpson> {
        return simpsonsApiRemoteDataSource.getByIdSimpson(id)
    }
}