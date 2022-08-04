package com.konopelko.skooving.data.api.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun createHttpClient(): OkHttpClient =
	OkHttpClient
		.Builder()
		.addNetworkInterceptor(HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		})
		.build()