package com.example.repository

import com.example.api.RssFeed
import com.example.domain.model.NewsDomainItem
import com.example.domain.repository.RepositoryImpl
import com.example.mappers.toDomain

class Repository(private val rssFeed: RssFeed) : RepositoryImpl {
    override suspend fun getNews(): List<NewsDomainItem> {
        val response = rssFeed.getRss()
        return response.channel.items.map { it.toDomain() }
    }
}