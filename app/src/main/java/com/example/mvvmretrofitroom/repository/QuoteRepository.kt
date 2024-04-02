package com.example.mvvmretrofitroom.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofitroom.api.QuoteServices
import com.example.mvvmretrofitroom.database.QuoteDatabase
import com.example.mvvmretrofitroom.models.QuoteList
import com.example.mvvmretrofitroom.utils.NetworkUtils

class QuoteRepository(
    private val quoteServices: QuoteServices,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    // mutable liveData
    private val quoteLiveData = MutableLiveData<QuoteList>()

    // liveData
    val quotes : LiveData<QuoteList>
        get() = quoteLiveData

    suspend fun getQuotes(){

        if(NetworkUtils.isNetworkAvailable(applicationContext)){
            // Network call result
            val result =  quoteServices.getQuote()
            if(result.body() != null){
                // To store the result in database
                quoteDatabase.quoteDao().insertQuote(result.body()!!)

                quoteLiveData.postValue(result.body())
            }
        }
        else{

            val quote = quoteDatabase.quoteDao().getQuotes()
            quoteLiveData.postValue(quote as QuoteList?)
        }

    }

    // worker function
    suspend fun getQuotesBackground(){

       // val randomNumber = (Math.random()*10).toInt()
        val result = quoteServices.getQuote()
        if(result.body() != null) {
            // To store the result in database
            quoteDatabase.quoteDao().insertQuote(result.body()!!)
        }

    }
}