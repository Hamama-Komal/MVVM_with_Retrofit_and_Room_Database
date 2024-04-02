package com.example.mvvmretrofitroom

import android.app.Application
import androidx.constraintlayout.widget.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mvvmretrofitroom.api.QuoteServices
import com.example.mvvmretrofitroom.api.RetrofitHelper
import com.example.mvvmretrofitroom.database.QuoteDatabase
import com.example.mvvmretrofitroom.repository.QuoteRepository
import com.example.mvvmretrofitroom.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {

    // Initialize Repository here, So all model classes access this easily
    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()

        initialize()

        setWorker()
    }

    private fun setWorker() {

        val constraints = androidx.work.Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quoteServices = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteServices, database, applicationContext)
    }

}