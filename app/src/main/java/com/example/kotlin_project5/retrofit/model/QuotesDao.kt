package com.example.kotlin_project5.retrofit.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface QuotesDao {
    @Query("SELECT * FROM quotes")
    fun getAllQuotes(): LiveData<List<Quotes>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuote(quote: Quotes)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuotes(quotes: List<Quotes>)
    @Update
    fun updateQuote(quote: Quotes)
    @Delete
    fun deleteQuote(quote: Quotes)
}