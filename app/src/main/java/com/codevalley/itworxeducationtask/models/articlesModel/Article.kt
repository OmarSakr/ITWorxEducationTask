package com.codevalley.itworxeducationtask.models.articlesModel

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class Article(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @Embedded
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)