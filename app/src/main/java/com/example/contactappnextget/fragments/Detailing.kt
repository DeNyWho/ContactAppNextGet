package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
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
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import kotlin.properties.Delegates

@AndroidEntryPoint
class Detailing : Fragment() {
    private val args: DetailingArgs by navArgs()
    var PERMISSION_CODE = 100
    private var favouriteId by Delegates.notNull<Int>()
    private lateinit var viewModel: ContactViewModel

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailing, container, false)
        val name = view.findViewById<TextView>(R.id.name)
        val phone = view.findViewById<TextView>(R.id.phone)
        val address = view.findViewById<TextView>(R.id.address)
        val edit = view.findViewById<FrameLayout>(R.id.edit)
        val image = view.findViewById<ImageView>(R.id.image)
        val call = view.findViewById<FrameLayout>(R.id.call)
        val rate = view.findViewById<ImageView>(R.id.rate)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        when(args.favourite){
            0 -> rate.setImageResource(R.drawable.ic_outline_star_outline_24)
            1 -> rate.setImageResource(R.drawable.ic_outline_star_24)
        }

        favouriteId = args.favourite

        rate.setOnClickListener {
            if (favouriteId == 1) {
                val contact = Contact(
                    name = name.text.toString(),
                    number = phone.text.toString(),
                    address = address.text.toString(),
                    image = args.image,
                    id = args.id,
                    favourite = 0
                )
                favouriteId = 0
                rate.setImageResource(R.drawable.ic_outline_star_outline_24)
                viewModel.updateContact(contact)
            }
            else {
                val contact = Contact(
                    name = name.text.toString(),
                    number = phone.text.toString(),
                    address = address.text.toString(),
                    image = args.image,
                    id = args.id,
                    favourite = 1
                )
                favouriteId = 1
                rate.setImageResource(R.drawable.ic_outline_star_24)
                viewModel.updateContact(contact)
            }

        }

        call.setOnClickListener {
//            val temp = phone.text.toString()
//            val c = "+()-"
//            val phoneNumber = temp.replace(Regex("[$c]"), "")
//            if (ContextCompat.checkSelfPermission(
//                    requireContext(),
//                    Manifest.permission.CALL_PHONE
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    requireActivity(),
//                    arrayOf(Manifest.permission.CALL_PHONE),
//                    PERMISSION_CODE
//                )
//                val i = Intent(Intent.ACTION_CALL)
//                Log.e("PHONE","Phone:$phoneNumber")
//                i.data = Uri.parse("tel:$phoneNumber")
//                startActivity(i)
//
//            }
//

        }

        name.text = args.name
        phone.text = args.phone
        address.text = args.address
        image.load(File(args.image)){
            transformations(CircleCropTransformation())
        }

        edit.setOnClickListener {
            val action = DetailingDirections.actionDetailingToEditContact(args.name, args.phone, args.address, args.image, args.id, favouriteId)
            findNavController().navigate(action)
        }

        return view
    }

}