package com.vald3nir.my_events.core.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vald3nir.my_events.R

@BindingAdapter(value = ["loadImage"])
fun ImageView.bindingLoadImage(url: String?) = loadImage(url)


fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        Picasso.get().load(url).placeholder(R.drawable.placeholder_image_not_found).into(this)
    } else {
        this.setImageResource(R.drawable.placeholder_image_not_found)
    }
}

