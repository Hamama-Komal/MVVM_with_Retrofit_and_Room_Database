package com.example.mvvmretrofitroom.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmretrofitroom.models.QuoteListItem

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuote(quotes: List<QuoteListItem>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<QuoteListItem>
}