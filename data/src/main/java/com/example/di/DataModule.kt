package com.example.di

import com.example.api.Retrofit
import com.example.api.RssFeed
import com.example.repository.Repository
import com.example.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

object DataModule {
    @Provides
    fun provideRssFeed(): RssFeed {
        return Retrofit.rssFeed
    }
    @Provides
    fun provideRepository(rssFeed: RssFeed): RepositoryImpl {
        return Repository(rssFeed)
    }
}