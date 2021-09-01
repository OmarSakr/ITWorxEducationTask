package com.codevalley.itworxeducationtask.network

import com.codevalley.itworxeducationtask.models.articlesModel.ArticlesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface AppServices {

    @GET(AppUrls.topHeadlines)
    suspend fun topHeadlines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Response<ArticlesModel>

    @GET(AppUrls.topHeadlines)
    suspend fun search(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: String,
        @Query("sortBy") sortBy: String,
        @Query("language") language: String,
        @Query("q") q: String,
        @Query("page") page: String
    ): Response<ArticlesModel>

}