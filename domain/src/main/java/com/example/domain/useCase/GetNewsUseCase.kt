package com.example.domain.useCase

import com.example.domain.repository.RepositoryImpl
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: RepositoryImpl) {
    suspend operator fun invoke() = repository.getNews()
}