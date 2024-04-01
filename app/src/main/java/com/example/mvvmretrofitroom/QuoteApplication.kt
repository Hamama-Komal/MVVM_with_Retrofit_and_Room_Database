package com.example.mvvmretrofitroom

import android.app.Application
import com.example.mvvmretrofitroom.api.QuoteServices
import com.example.mvvmretrofitroom.api.RetrofitHelper
import com.example.mvvmretrofitroom.database.QuoteDatabase
import com.example.mvvmretrofitroom.repository.QuoteRepository

class QuoteApplication : Application() {

    // Initialize Repository here, So all model classes access this easily
    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()

        initialize()
    }

    private fun initialize() {
        val quoteServices = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteServices, database, applicationContext)
    }

}