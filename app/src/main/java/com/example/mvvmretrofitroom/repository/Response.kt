package com.example.mvvmretrofitroom.repository

import com.example.mvvmretrofitroom.models.QuoteList

// 1) Simple Response Class
/*sealed class Response{

    class Loading : Response()
    class Success(val quoteList: QuoteList) : Response()
    class Error(val errorMessage: String) : Response()
}*/

// 2) Improve Version of Simple Response Class
/*
sealed class Response2(private val data: QuoteList? = null, val message: String? = null){

    class Loading : Response2()
    class Success(quoteList: QuoteList) : Response2(data = quoteList)
    class Error(errorMessage: String) : Response2(message = errorMessage)

}*/

// 3) Generic Implementation
sealed class Response<T>(val data: T? = null, val message: String? = null){

    class Loading<T> : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(errorMessage: String) : Response<T>(message = errorMessage)

}