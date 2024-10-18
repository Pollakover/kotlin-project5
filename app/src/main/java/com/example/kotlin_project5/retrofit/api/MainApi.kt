package com.example.kotlin_project5.retrofit.api

import com.example.kotlin_project5.retrofit.model.Quotes
import com.example.kotlin_project5.retrofit.model.QuotesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("quotes/{id}")
    suspend fun getQuoteById(@Path("id") id: Int): Quotes

}