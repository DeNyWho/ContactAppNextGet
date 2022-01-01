package com.example.contactappnextget.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contactappnextget.R
import java.io.File


class Detailing : Fragment() {

    private val args: DetailingArgs by navArgs()
    var PERMISSION_CODE = 100
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
            crossfade(true)
            crossfade(1000)
            transformations(CircleCropTransformation())
        }

        edit.setOnClickListener {
            val action = DetailingDirections.actionDetailingToEditContact(args.name, args.phone, args.address, args.image)
            findNavController().navigate(action)
        }

        return view
    }

}