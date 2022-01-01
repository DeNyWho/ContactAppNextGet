package com.example.contactappnextget.fragments

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.viewModel.ContactViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.makeramen.roundedimageview.RoundedImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_contact.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

@AndroidEntryPoint
class EditContact : Fragment(R.layout.fragment_edit_contact) {
    private val args: EditContactArgs by navArgs()
    private val CAMERA_REQUEST_CODE = 1
    private val GALLERY_REQUEST_CODE = 2
    private lateinit var image: ImageView
    private lateinit var setImage: RoundedImageView
    private lateinit var name: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var address: TextInputEditText
    private lateinit var viewModel: ContactViewModel
    private lateinit var action: EditContactDirections.ActionEditContactToDetailing

    lateinit var tempBitmap: Bitmap

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Edit contact"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_contact, container, false)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        name = view.findViewById(R.id.name)
        phone = view.findViewById(R.id.phone)
        address = view.findViewById(R.id.address)
        image = view.findViewById(R.id.Image)
        setImage = view.findViewById(R.id.setImage)
        val next = view.findViewById<MaterialButton>(R.id.next)

        name.setText(args.name)
        phone.setText(args.phone)
        address.setText(args.address)
        image.load(File(args.image)){
            crossfade(true)
            crossfade(1000)
            transformations(CircleCropTransformation())
        }

        setImage.setOnClickListener {
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

        next.setOnClickListener {
            val pictureDialog = AlertDialog.Builder(requireContext())
            pictureDialog.setTitle("Save the updates?")
            val pictureDialogItem = arrayOf("No", "Yes")

            pictureDialog.setItems(pictureDialogItem) { _, which ->

                when (which) {
                    0 -> {
                        action = EditContactDirections.actionEditContactToDetailing(args.name, args.phone, args.address, args.image)
                        findNavController().navigate(action)
                    }
                    1 -> {
                        val uri: Uri = saveImage()
                        val uriPath = uri.path.toString()
                        Log.e("URIPATH",uriPath)

                        when {
                            name.text!!.isEmpty() -> name.error = "Please enter the name"
                            phone.text!!.isEmpty() -> textInputEditText2.error = "Please enter the number"
                            else -> {
                                // save data
                                val contact = Contact(name = name.text.toString(), number = phone.text.toString(), address = address.text.toString(), image = uriPath)
                                viewModel.saveContact(contact)
                            }
                        }
                    }
                }

            }

            pictureDialog.show()
        }

        return view
    }

//    private fun saveData() {
//
//        val uri: Uri = saveImage()
//        val uriPath = uri.path.toString()
//        Log.e("URIPATH",uriPath)
//
//        when {
//            name.text!!.isEmpty() -> name.error = "Please enter the name"
//            phone.text!!.isEmpty() -> textInputEditText2.error = "Please enter the number"
//            else -> {
//                // save data
//                val contact = Contact(name = name.text.toString(), number = phone.text.toString(), address = address.text.toString(), image = uriPath)
//                viewModel.saveContact(contact)
//            }
//        }
//    }

    private fun saveImage(): Uri {

        val wrapper = ContextWrapper(requireContext())

        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
//        create file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try{
            val stream: OutputStream = FileOutputStream(file)
//            compress bitmap
            tempBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            stream.flush()
            stream.close()

        }
        catch(e: IOException){
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)

    }
    //      need to change in the future
    private fun gallery(){
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
                }

                GALLERY_REQUEST_CODE -> {

                    image.load(data?.data) {
                        crossfade(true)
                        crossfade(1000)
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

}