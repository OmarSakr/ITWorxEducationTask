package com.codevalley.itworxeducationtask.main.favourite.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codevalley.itworxeducationtask.main.favourite.repository.FavouriteRepository
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: FavouriteRepository) : ViewModel() {

    val allWords: LiveData<List<Article>> = repository.allFavourites.asLiveData()


    fun addToFavorite(article: Article) = viewModelScope.launch {
        repository.addToFavorite(article)
    }


}