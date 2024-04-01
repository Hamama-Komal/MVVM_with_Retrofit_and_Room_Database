package com.example.mvvmretrofitroom.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "quote")
data class QuoteListItem(
    @PrimaryKey(autoGenerate = true)
    val quoteId: Int,
    val author: String,
    val id: String,
    val quote: String
)