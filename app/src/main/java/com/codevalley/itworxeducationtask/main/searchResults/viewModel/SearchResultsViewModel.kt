package com.codevalley.itworxeducationtask.main.searchResults.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.codevalley.itworxeducationtask.main.searchResults.repository.SearchResultsDataSource
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import kotlinx.coroutines.flow.Flow

class SearchResultsViewModel : ViewModel() {
    private var _searchResults: Flow<PagingData<Article>>? = null


    fun getSearchResults(
        country: String,
        category: String,
        language: String,
        searchWord: String
    ): Flow<PagingData<Article>> {
        _searchResults = Pager(PagingConfig(pageSize = 10)) {
            SearchResultsDataSource(country, category, language, searchWord)
        }.flow
            .cachedIn(viewModelScope)
        return _searchResults as Flow<PagingData<Article>>
    }
}