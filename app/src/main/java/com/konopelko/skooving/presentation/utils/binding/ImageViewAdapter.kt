package com.konopelko.skooving.presentation.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.konopelko.skooving.R

@BindingAdapter("imageSource")
fun loadImage(imageView: ImageView, url: String?) {
	url?.let {
		Glide.with(imageView)
			.load(it)
			.placeholder(R.drawable.ic_cover_template)
			.into(imageView)
	}
}