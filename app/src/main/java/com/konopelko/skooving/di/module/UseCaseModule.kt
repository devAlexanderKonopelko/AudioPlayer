package com.konopelko.skooving.di.module

import com.konopelko.skooving.domain.usecase.get_songs.GetSongsUseCase
import com.konopelko.skooving.domain.usecase.get_songs.RealGetSongsUseCase
import org.koin.dsl.module

val koinUseCaseModule = module {

	factory<GetSongsUseCase> {
		RealGetSongsUseCase(get())
	}
}