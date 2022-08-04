package com.konopelko.skooving.domain.repository

import com.konopelko.skooving.data.api.entity.response.song.GetSongsResponse
import com.konopelko.skooving.data.utils.Result

interface SongRepository {

	suspend fun getSongs(): Result<GetSongsResponse?>
}