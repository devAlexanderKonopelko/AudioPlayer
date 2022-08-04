package com.konopelko.skooving.di.module

import com.konopelko.skooving.data.repository.RealSongRepository
import com.konopelko.skooving.domain.repository.SongRepository
import org.koin.dsl.module

val koinRepositoryModule = module {

	factory<SongRepository> {
		RealSongRepository(get())
	}
}