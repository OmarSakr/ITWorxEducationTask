package com.codevalley.itworxeducationtask.main.favourite.repository

import androidx.annotation.WorkerThread
import com.codevalley.itworxeducationtask.dao.ArticleDao
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import kotlinx.coroutines.flow.Flow

class FavouriteRepository(private val articleDao: ArticleDao) {
    val allFavourites: Flow<List<Article>> = articleDao.getFavourites()

    @WorkerThread
    suspend fun addToFavorite(article: Article) {
        articleDao.addToFavorite(article)
    }

}