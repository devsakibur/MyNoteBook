package com.example.mypencilbook.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mypencilbook.R
import com.example.mypencilbook.adapter.RecyclerViewAdapter
import com.example.mypencilbook.databinding.FragmentNotesBinding
import com.example.mypencilbook.model.BookModel
import com.example.mypencilbook.repository.BookRepository
import com.example.mypencilbook.roomdatabase.BookDatabase
import com.example.mypencilbook.viewmodel.BookViewModel
import com.example.mypencilbook.viewmodel.ViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.IOException

class NotesFragment : Fragment() {
private lateinit var binding: FragmentNotesBinding
private var myImgToSave:String = ""
private lateinit var myImgToShow : ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)

        val database = Room.databaseBuilder(requireContext() , BookDatabase::class.java , "app_database_notes").build()
        val dao = database.bookDao()
        val bookRepository = BookRepository(dao)
        val viewModelFactory = ViewModelFactory(bookRepository)
        val viewModel = ViewModelProvider(this , viewModelFactory)[BookViewModel::class.java]


        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allBooks.observe(this , Observer {
            binding.recyclerView.adapter = RecyclerViewAdapter(it , viewModel)
        })



        binding.addDataToDatabase.setOnClickListener {

            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.not_dialog_layout)

            dialog.findViewById<Button>(R.id.cancelNote).setOnClickListener {
                dialog.dismiss()
            }

            myImgToShow = dialog.findViewById(R.id.imgDV)
            dialog.findViewById<ImageView>(R.id.imgD).setOnClickListener {
                val pickImg = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                changeImg.launch(pickImg)
            }

            dialog.findViewById<Button>(R.id.submitNote).setOnClickListener {
                val title = dialog.findViewById<EditText>(R.id.titieD).text.toString() ?: ""
                val desc = dialog.findViewById<EditText>(R.id.descD).text.toString() ?:""

                val img = myImgToSave

                val user = BookModel(title = title , img = img , description = desc)
                viewModel.insert(user)
                dialog.dismiss()
            }



            dialog.show()
        }











        return binding.root
    }


    private val changeImg = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == Activity.RESULT_OK){
            val data = it.data
            val imgUri = data?.data
            myImgToShow.setImageURI(imgUri)
            myImgToSave = imgUri?.let { it1 -> myImgUriToString(requireContext(), it1) }.toString()



        }
    }


    private fun myImgUriToString(context: Context, imgUri : Uri) : String? {
        val contentResolver = context.contentResolver
        try {
            val inputStream = contentResolver.openInputStream(imgUri)

            val bitmap = BitmapFactory.decodeStream(inputStream)

            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG , 90 , outputStream)

            val byteArray = outputStream.toByteArray()

            return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)




        }catch (e :IOException){
            e.printStackTrace()
        }



        return null

    }

}