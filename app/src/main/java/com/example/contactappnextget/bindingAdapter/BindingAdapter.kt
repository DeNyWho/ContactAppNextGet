package com.example.contactappnextget.bindingAdapter

//import android.view.PixelCopy.request
//import android.widget.ImageView
//import androidx.databinding.BindingAdapter
//import com.example.contactappnextget.R
//
//@BindingAdapter("ProfilePictureUrl")
//fun bindProfilePicture(imageView: ImageView, profilePictureUrl: String?){
//
//    profilePictureUrl?.let{
//
//        val profilePictureUri = profilePictureUrl.toUri()
//
//        Glide.with(imageView.context)
//            .load(profilePictureUri)
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_loading_profile_picture)
//            .apply(RequestOptions.circleCropTransform())
//            .into(imageView)
//
//    }
//}