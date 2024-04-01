package com.example.mvvmretrofitroom

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofitroom.api.QuoteServices
import com.example.mvvmretrofitroom.api.RetrofitHelper
import com.example.mvvmretrofitroom.repository.QuoteRepository
import com.example.mvvmretrofitroom.viewmodel.MainViewModel
import com.example.mvvmretrofitroom.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val quoteServices = RetrofitHelper.getInstance().create(QuoteServices::class.java)
        val repository = QuoteRepository(quoteServices)

        mainViewModel =ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, Observer {

            Log.d("QUOTE_RESULT", it.toString())
        })

    }
}