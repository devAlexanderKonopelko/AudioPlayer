package com.konopelko

import android.app.Application
import com.konopelko.skooving.di.module.koinNetworkModule
import com.konopelko.skooving.di.module.koinModuleViewModel
import com.konopelko.skooving.di.module.koinRepositoryModule
import com.konopelko.skooving.di.module.koinUseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AudioPlayerApplication: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@AudioPlayerApplication)
			modules(
				koinNetworkModule,
				koinRepositoryModule,
				koinUseCaseModule,
				koinModuleViewModel
			)
		}
	}
}