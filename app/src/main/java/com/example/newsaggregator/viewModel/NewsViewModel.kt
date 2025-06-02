package com.example.newsaggregator.viewModel

import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.domain.model.NewsDomainItem
import com.example.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {
    private val _news = MutableStateFlow<List<NewsDomainItem>>(emptyList())
    val news: StateFlow<List<NewsDomainItem>> = _news

    @OptIn(UnstableApi::class)
    fun fetchNews() {
        Log.d("NewsViewModel", "fetchNews: Fetching news")
        viewModelScope.launch {
            try {
                _news.value = getNewsUseCase()
                Log.d("NewsViewModel", "fetchNews: News fetched successfully")
            } catch (e: Exception) {
                Log.e("NewsViewModel", "fetchNews: Error fetching news", e)
            }
        }
    }
}


