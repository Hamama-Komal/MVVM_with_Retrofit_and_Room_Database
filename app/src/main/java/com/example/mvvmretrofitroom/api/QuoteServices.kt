package com.example.mvvmretrofitroom.api

import com.example.mvvmretrofitroom.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET

interface QuoteServices {

    @GET("v1/quote/50")
    suspend fun getQuote(): Response<QuoteList>
}