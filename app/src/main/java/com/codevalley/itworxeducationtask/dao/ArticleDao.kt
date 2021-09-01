package com.codevalley.itworxeducationtask.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codevalley.itworxeducationtask.models.articlesModel.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM favourite_table ORDER BY publishedAt DESC")
    fun getFavourites(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(article: Article)

    @Query("DELETE FROM favourite_table")
    suspend fun deleteAll()

}