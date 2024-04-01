package com.example.mvvmretrofitroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofitroom.models.QuoteList
import com.example.mvvmretrofitroom.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes()
        }
    }

    val quotes : LiveData<QuoteList>
        get() = quoteRepository.quotes
}