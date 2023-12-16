package com.example.mypencilbook.roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mypencilbook.model.BookModel

@Database(entities = [BookModel::class], version = 1, exportSchema = false)
abstract class BookDatabase:RoomDatabase() {
  abstract fun bookDao():BookDao
}