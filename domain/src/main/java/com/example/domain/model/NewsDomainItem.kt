package com.example.domain.model

data class NewsDomainItem(
    val title: String,
    val description: String,
    val link: String,
    val pubDate: String,
    val guid: String,
    val imageUrl: String?,
    val dcCreator: String?,
    val tags: List<String> = emptyList(),
)