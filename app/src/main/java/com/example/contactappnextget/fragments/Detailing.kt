package com.example.contactappnextget.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.transform.CircleCropTransformation
import com.example.contactappnextget.R
import com.example.contactappnextget.model.Contact
import com.example.contactappnextget.util.RoundedRectDrawable
import com.example.contactappnextget.viewModel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.saket.cascade.CascadePopupMenu
import me.saket.cascade.allChildren
import java.io.File
import kotlin.properties.Delegates

@AndroidEntryPoint
class Detailing : Fragment() {
    private val args: DetailingArgs by navArgs()

    private var favouriteId by Delegates.notNull<Int>()
    private lateinit var viewModel: ContactViewModel
    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var address: TextView

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.title = "Contact Info"
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailing, container, false)
        name = view.findViewById<TextView>(R.id.name)
        phone = view.findViewById<TextView>(R.id.phone)
        address = view.findViewById<TextView>(R.id.address)
        val edit = view.findViewById<FrameLayout>(R.id.edit)
        val image = view.findViewById<ImageView>(R.id.image)
        val call = view.findViewById<FrameLayout>(R.id.call)
        val rate = view.findViewById<ImageView>(R.id.rate)
        val menuBtn = view.findViewById<AppCompatButton>(R.id.menuBtn)
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]

        menuBtn.setOnClickListener {
            showCascadeMenu(anchor = menuBtn)
        }

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

        call.setOnClickListener {}

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

    private fun showCascadeMenu(anchor: View) {
        val popupMenu = CascadePopupMenu(requireContext(), anchor, styler = cascadeMenuStyler())
        popupMenu.menu.apply {
            MenuCompat.setGroupDividerEnabled(this, true)
            addSubMenu("Share").also {
                it.setIcon(R.drawable.ic_outline_share_24)
            }
            addSubMenu("Remove").also {
                it.setIcon(R.drawable.ic_outline_delete_sweep_24)
                it.setHeaderTitle("Are you sure?")
                it.add("Yep").setIcon(R.drawable.ic_outline_check_24).setOnMenuItemClickListener {
                    val contact = Contact(
                        name = name.text.toString(),
                        number = phone.text.toString(),
                        address = address.text.toString(),
                        image = args.image,
                        id = args.id,
                        favourite = favouriteId
                    )
                    viewModel.deleteContact(contact)
                    findNavController().navigate(R.id.action_detailing_to_contactList)
                    true
                }
                it.add("Go back").setIcon(R.drawable.ic_outline_close_24).setOnMenuItemClickListener {
                    popupMenu.navigateBack()
                }
            }

            popupMenu.show()
        }
    }
    private fun cascadeMenuStyler(): CascadePopupMenu.Styler {
        val rippleDrawable = {
            RippleDrawable(ColorStateList.valueOf(Color.parseColor("#B1DDC6")), null, ColorDrawable(Color.WHITE))
        }

        return CascadePopupMenu.Styler(
            background = {
                RoundedRectDrawable(Color.parseColor("#232B3B"), radius = 8f.dip)
            },
            menuTitle = {
                it.titleView.typeface = ResourcesCompat.getFont(requireContext(), R.font.font)
                it.setBackground(rippleDrawable())
            },
            menuItem = {
                it.titleView.typeface = ResourcesCompat.getFont(requireContext(), R.font.font)
                it.setBackground(rippleDrawable())
                it.setGroupDividerColor(Color.parseColor("#232B3B"))
            }
        )
    }
    private val Float.dip: Float
        get() {
            val metrics = resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, metrics)
        }

}