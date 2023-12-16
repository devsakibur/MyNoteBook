package com.example.mypencilbook.repository

import androidx.lifecycle.LiveData
import com.example.mypencilbook.model.BookModel
import com.example.mypencilbook.roomdatabase.BookDao

class BookRepository(private val bookDao: BookDao) {

    val allBooks : LiveData<List<BookModel>> = bookDao.getAllUsers()

    suspend fun insert(data:BookModel){
        bookDao.insertUser(data)
    }
    suspend fun delete(data:BookModel){
        bookDao.deleteUser(data)
    }

}