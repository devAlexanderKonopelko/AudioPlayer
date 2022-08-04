package com.konopelko.skooving.data.api

import com.konopelko.skooving.data.api.entity.response.song.GetSongsResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface SongsApi {

	@GET("data/manifest.json")
	suspend fun getSongs(): Response<GetSongsResponse>
}