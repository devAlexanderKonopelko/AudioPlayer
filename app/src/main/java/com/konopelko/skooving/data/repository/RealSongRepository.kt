package com.konopelko.skooving.data.repository

import com.konopelko.skooving.data.api.SongsApi
import com.konopelko.skooving.data.api.entity.response.song.GetSongsResponse
import com.konopelko.skooving.data.utils.Result
import com.konopelko.skooving.data.utils.apiCall
import com.konopelko.skooving.domain.repository.SongRepository
import kotlinx.coroutines.Dispatchers.IO

class RealSongRepository(
	private val api: SongsApi
): SongRepository {

	override suspend fun getSongs(): Result<GetSongsResponse?> = apiCall(IO) {
		api.getSongs().body()
	}
}