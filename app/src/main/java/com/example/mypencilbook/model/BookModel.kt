package com.example.mypencilbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "notes")
data class BookModel (@PrimaryKey(autoGenerate = true ) val id :Int = 0,
                      val title : String ,
                      val img : String,
                      val description : String
                    )