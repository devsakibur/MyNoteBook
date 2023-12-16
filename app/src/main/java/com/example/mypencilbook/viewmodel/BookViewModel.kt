package com.example.mypencilbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypencilbook.model.BookModel
import com.example.mypencilbook.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel(val bookRepository: BookRepository):ViewModel() {

    val allBooks : LiveData<List<BookModel>> = bookRepository.allBooks

    fun insert(book:BookModel) = viewModelScope.launch {
        bookRepository.insert(book)
    }
    fun delete(book:BookModel) = viewModelScope.launch {
        bookRepository.delete(book)
    }




}