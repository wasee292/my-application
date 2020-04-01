package com.example.myapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
  @JvmStatic
  @BindingAdapter("imageUrl")
  fun getImageFromUrl(view: ImageView, imageUrl: String) {
    Picasso.get().load(imageUrl).into(view)
  }
}
