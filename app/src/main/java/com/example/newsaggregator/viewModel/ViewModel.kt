package com.example.newsaggregator.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.NewsDomainItem
import com.example.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {
    private val _news = MutableStateFlow<List<NewsDomainItem>>(emptyList())
    val news: StateFlow<List<NewsDomainItem>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            _news.value = getNewsUseCase()

        }
    }
}


