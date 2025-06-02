package com.example.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object Retrofit {
    private const val BASE_URL = "https://www.theguardian.com"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(XML.asConverterFactory("application/xml; charset=UTF8".toMediaType()))
            .client(OkHttpClient.Builder().build())
            .build()
    }
    val rssFeed: RssFeed by lazy {
        retrofit.create(RssFeed::class.java)
    }
}