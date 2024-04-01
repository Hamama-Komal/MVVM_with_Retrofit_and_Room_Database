package com.example.mvvmretrofitroom.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofitroom.api.QuoteServices
import com.example.mvvmretrofitroom.models.QuoteList

class QuoteRepository(private val quoteServices: QuoteServices) {

    // mutable liveData
    private val quoteLiveData = MutableLiveData<QuoteList>()

    // liveData
    val quotes : LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(){
        val result =  quoteServices.getQuote()
        if(result.body() != null){
            quoteLiveData.postValue(result.body())
        }
    }
}