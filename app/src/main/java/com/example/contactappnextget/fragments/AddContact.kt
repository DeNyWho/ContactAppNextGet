package com.example.contactappnextget.fragments

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.viewModel.ContactViewModel
import com.makeramen.roundedimageview.RoundedImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_contact.*
import kotlinx.android.synthetic.main.fragment_add_contact.view.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*
import kotlin.properties.Delegates

@AndroidEntryPoint
class AddContact : Fragment(R.layout.fragment_add_contact) {

    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    private lateinit var roundedImageView: RoundedImageView
    private lateinit var image: ImageView

    lateinit var tempBitmap: Bitmap
    var temp by Delegates.notNull<Int>()

    private lateinit var name: String
    private lateinit var number: String
    private lateinit var address: String
    private lateinit var viewModel: ContactViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        temp = 0

        roundedImageView = view.findViewById(R.id.setImage)
        image = view.findViewById(R.id.roundedImageView)

        roundedImageView.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(requireContext())
            pictureDialog.setTitle("Select action")
            val pictureDialogItem = arrayOf("Select photo from Gallery", "Capture photo from Camera")
            pictureDialog.setItems(pictureDialogItem) { _, which ->

                when (which) {
                    0 -> gallery()
                    1 -> camera()
                }
            }

            pictureDialog.show()
        }

        view.create_contact.setOnClickListener {
            name = textInputEditText.text.toString().trim()
            number = textInputEditText2.text.toString().trim()
            address = textInputEditText3.text.toString().trim()
            if (temp != 1) {
                tempBitmap = (image.drawable as BitmapDrawable).bitmap
            }
            val uri: Uri = saveImage()
            val uriPath = uri.path.toString()

            when {
                name.isEmpty() -> textInputEditText.error = "Please enter the name"
                number.isEmpty() -> textInputEditText2.error = "Please enter the number"
                else -> {
                    // save data
                    val contact = Contact(name = name, number = number, address = address, image = uriPath)
                    viewModel.insertContact(contact)
                }
            }
            findNavController().navigate(R.id.navigate_to_contactList)
        }
        return view
    }

    private fun saveImage(): Uri {

        val wrapper = ContextWrapper(requireContext())

        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
//        create file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
//            compress bitmap
            tempBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            stream.flush()
            stream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)

    }

    //      need to change in the future
    private fun gallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    private fun camera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {

            when (requestCode) {

                CAMERA_REQUEST_CODE -> {
                    tempBitmap = data?.extras?.get("data") as Bitmap

                    //we are using coroutine image loader (coil)
                    image.load(tempBitmap) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                    temp = 1
                }

                GALLERY_REQUEST_CODE -> {
                    val imageUri = data!!.data
                    tempBitmap = MediaStore.Images.Media.getBitmap(activity!!.contentResolver, imageUri)

                    image.load(data.data) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                    temp = 1
                }
            }
        }
    }
}