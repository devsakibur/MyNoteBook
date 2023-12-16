package com.example.mypencilbook.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mypencilbook.model.BookModel

@Dao
interface BookDao {
    @Query("SELECT * FROM notes")
    fun getAllUsers():LiveData<List<BookModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user : BookModel)

    @Delete
    suspend fun deleteUser(user: BookModel)

}