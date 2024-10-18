package com.example.kotlin_project5.retrofit.model

import androidx.lifecycle.LiveData

class QuotesRepository(private val quotesDao: QuotesDao) {
    val allQuotes: LiveData<List<Quotes>> = quotesDao.getAllQuotes()

    suspend fun insert(quotes: List<Quotes>) {
        quotesDao.insertQuotes(quotes)
    }
}
