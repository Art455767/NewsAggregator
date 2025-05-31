package com.example.newsaggregator.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import coil.compose.rememberImagePainter
import com.example.domain.model.NewsDomainItem
import com.example.newsaggregator.viewModel.NewsViewModel

@Composable
fun NewsList(viewModel: NewsViewModel) {
    val news by viewModel.news.collectAsState()
    LazyColumn {
        items(news) { item ->
            NewsItem(item)
        }
    }
}

@Composable
fun NewsItem(item: NewsDomainItem) {
    Column {
        Text(item.title)
        Text(item.description)
        Image(painter = rememberImagePainter(item.imageUrl), contentDescription = null)
    }
}