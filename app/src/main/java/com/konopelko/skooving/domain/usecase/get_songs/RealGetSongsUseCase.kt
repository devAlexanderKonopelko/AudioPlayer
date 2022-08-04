package com.konopelko.skooving.domain.usecase.get_songs

import com.konopelko.skooving.data.api.entity.response.song.GetSongsResponse
import com.konopelko.skooving.data.utils.Result
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.domain.repository.SongRepository

class RealGetSongsUseCase(
	private val songRepository: SongRepository
) : GetSongsUseCase {

	override suspend fun invoke(): Result<List<Song>> = songRepository.getSongs().map {
		Result.Success(it.value?.toSongList() ?: listOf())
	}

	private fun GetSongsResponse.toSongList(): List<Song> = data.map {
		Song(
			title = it.title,
			audio = it.audio,
			cover = it.cover,
			totalDurationMs = it.totalDurationMs.toDouble()
		)
	}
}
