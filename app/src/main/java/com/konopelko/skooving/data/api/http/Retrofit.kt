package com.konopelko.skooving.data.api.http

import com.konopelko.skooving.data.api.SongsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createApi(
	httpClient: OkHttpClient,
	hostUrl: String
): SongsApi =
	Retrofit
		.Builder()
		.baseUrl(hostUrl)
		.addConverterFactory(GsonConverterFactory.create())
		.client(httpClient)
		.build()
		.create(SongsApi::class.java)