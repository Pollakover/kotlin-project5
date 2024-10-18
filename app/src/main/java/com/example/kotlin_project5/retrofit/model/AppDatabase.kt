package com.example.kotlin_project5.retrofit.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Quotes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuotesDao
}