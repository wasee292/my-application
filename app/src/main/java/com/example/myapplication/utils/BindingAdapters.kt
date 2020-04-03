package com.example.myapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

object BindingAdapters {
  @JvmStatic
  @BindingAdapter("imageUrl")
  fun getImageFromUrl(view: ImageView, imageUrl: String) {
    view.load(imageUrl)
  }
}
