package com.myproject.pixabayapp.constant

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.myproject.pixabayapp.R

fun ImageView.downloadFromUrl(url: String, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).centerCrop().into(this)
}
fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadFromUrl")
fun download(imageView: ImageView, url: String?) {
    if (url != null) {
        imageView.downloadFromUrl(url, placeholderProgressBar(imageView.context))
    }
}