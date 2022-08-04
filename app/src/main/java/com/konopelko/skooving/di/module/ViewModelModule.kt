package com.konopelko.skooving.di.module

import android.os.Bundle
import com.konopelko.skooving.presentation.main.MainViewModel
import com.konopelko.skooving.presentation.song.SongViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModuleViewModel = module {

	viewModel {
		MainViewModel(
			getSongs = get()
		)
	}

	viewModel { (args: Bundle?) ->
		SongViewModel(args ?: Bundle())
	}
}
