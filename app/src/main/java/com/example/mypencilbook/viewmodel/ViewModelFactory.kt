package com.example.mypencilbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mypencilbook.repository.BookRepository

class ViewModelFactory(private val bookRepository: BookRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(bookRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}