package com.codevalley.itworxeducationtask.main.searchResults.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import com.codevalley.itworxeducationtask.utils.Constants
import com.codevalley.itworxeducationtask.utils.RetrofitClient

class SearchResultsDataSource(
    private val country: String,
    private val category: String,
    private val language: String,
    private val searchWord: String
) : PagingSource<Int, Article>() {
    private val apiService = RetrofitClient.apiInterface

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.search(
                country, category, Constants.apiKey,
                "10", "publishedAt", language,searchWord, currentLoadingPageKey.toString()
            )
            val responseData = mutableListOf<Article>()
            val data = response.body()?.articles ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}