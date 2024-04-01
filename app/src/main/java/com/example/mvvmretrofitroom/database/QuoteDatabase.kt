package com.example.mvvmretrofitroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmretrofitroom.models.QuoteListItem

@Database(entities = [QuoteListItem::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao() : QuoteDao

    companion object{
        private var INSTANCE : QuoteDatabase? = null

        fun getDatabase(context: Context) : QuoteDatabase{

            if (INSTANCE == null){

                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, QuoteDatabase::class.java, "quoteDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}