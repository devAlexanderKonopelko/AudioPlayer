package com.konopelko.skooving.presentation.utils.binding

import android.widget.RatingBar
import androidx.databinding.BindingAdapter

@BindingAdapter("onRating")
fun setRating(ratingBar: RatingBar, func: ((Float) -> Unit)?) {
	func?.let {
		ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { p0, rating, p2 ->
			func.invoke(rating)
		}
	}
}