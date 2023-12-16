package com.example.mypencilbook.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.example.mypencilbook.R
import com.example.mypencilbook.model.BookModel
import com.example.mypencilbook.viewmodel.BookViewModel

class RecyclerViewAdapter(private val notes : List<BookModel> ,private val viewModel: BookViewModel):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_view , parent , false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]


        if (note.img == ""){
            holder.img.visibility = View.GONE
        }else{
            holder.img.setImageBitmap(base64ToBitmap(note.img))
        }

        if (note.title == ""){
            holder.title.visibility = View.GONE
        }else{
            holder.title.text = note.title
        }

        if (note.description == ""){
            holder.description.visibility = View.GONE
        }else{
            holder.description.text = note.description
        }



        holder.delete.setOnClickListener {
            viewModel.delete(note)
        }



    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val title = itemView.findViewById<TextView>(R.id.title)
        val img = itemView.findViewById<ImageView>(R.id.img)
        val description = itemView.findViewById<TextView>(R.id.description)
        val delete = itemView.findViewById<ImageView>(R.id.delete)


    }


    fun base64ToBitmap(base64String: String): Bitmap? {
        try {
            val decodedByteArray: ByteArray = Base64.decode(base64String, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}