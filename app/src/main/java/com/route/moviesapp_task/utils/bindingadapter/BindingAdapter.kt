package com.route.moviesapp_task.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(image: ImageView, url:String){
    if (url.isNullOrBlank()) return
    Glide
        .with(image)
        .load(url)
        .into(image)
}