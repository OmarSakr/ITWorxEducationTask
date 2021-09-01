package com.codevalley.itworxeducationtask.main.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codevalley.itworxeducationtask.main.home.repository.HomeDataSource
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import kotlinx.coroutines.flow.Flow

class HomeViewModel : ViewModel() {
    private var _topHeadlines: Flow<PagingData<Article>>? = null


    fun getTopHeadlines(
        country: String,
        category: String,
        language: String
    ): Flow<PagingData<Article>> {
        _topHeadlines = Pager(PagingConfig(pageSize = 10)) {
            HomeDataSource(country, category, language)
        }.flow
            .cachedIn(viewModelScope)
        return _topHeadlines as Flow<PagingData<Article>>
    }
}