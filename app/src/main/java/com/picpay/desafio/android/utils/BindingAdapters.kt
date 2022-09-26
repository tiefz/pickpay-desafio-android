package com.picpay.desafio.android.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.picpay.desafio.android.R

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    url.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_round_account_circle)
                    .error(R.drawable.ic_round_account_circle)
            )
            .into(imageView)
    }
}