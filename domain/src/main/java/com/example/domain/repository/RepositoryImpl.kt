package com.example.domain.repository

import com.example.domain.model.NewsDomainItem

interface RepositoryImpl {
    suspend fun getNews(): List<NewsDomainItem>
}