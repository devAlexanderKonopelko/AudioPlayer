package com.konopelko.skooving.data.api.entity.response.song

data class GetSongsResponse(
	val data : List<SongModel>
) {

	data class SongModel(
		val title: String,
		val audio: String,
		val cover: String,
		val totalDurationMs: Int
	)
}
