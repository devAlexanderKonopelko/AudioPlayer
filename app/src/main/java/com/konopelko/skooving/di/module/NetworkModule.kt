package com.konopelko.skooving.di.module

import com.konopelko.skooving.BuildConfig
import com.konopelko.skooving.data.api.http.createApi
import com.konopelko.skooving.data.api.http.createHttpClient
import org.koin.dsl.module

val koinNetworkModule = module {
	single {
		createHttpClient()
	}
	single {
		createApi(
			httpClient = get(),
			hostUrl = BuildConfig.SONGS_API_HOST
		)
	}
}
