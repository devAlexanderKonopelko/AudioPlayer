package com.konopelko.skooving.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
	val title: String,
	val audio: String,
	val cover: String,
	val totalDurationMs: Double,
	val isFavorite: Boolean = false,
	val rating: Float = 0F
) : Parcelable {

	companion object {
		const val KEY_SONG = "key.song"
	}
}
