package com.codevalley.itworxeducationtask.models.articlesModel

data class ArticlesModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
    val message: String
)