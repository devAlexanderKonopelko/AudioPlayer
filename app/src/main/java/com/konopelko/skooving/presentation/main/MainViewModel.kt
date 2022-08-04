package com.konopelko.skooving.presentation.main

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konopelko.skooving.data.utils.Result
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.domain.entity.Song.Companion.KEY_SONG
import com.konopelko.skooving.domain.usecase.get_songs.GetSongsUseCase
import com.konopelko.skooving.presentation.utils.livedata.toEvent
import kotlinx.coroutines.launch

class MainViewModel(
	private val getSongs: GetSongsUseCase
) : ViewModel() {

	private val _action = MutableLiveData<MainAction>()
	val action = _action.toEvent()

	private val _songs = MutableLiveData<List<Song>>()
	val songs: LiveData<List<Song>> = _songs

	init {
		loadSongs()
	}

	private fun loadSongs() {
		viewModelScope.launch {
			getSongs().onSuccess { result ->
				result.value.let {
					_songs.value = it
					_action.value = MainAction.UpdateRefreshing
				}
			}.onError {
				// handle error case
			}
		}
	}

	fun onSongClicked(song: Song) {
		val bundle = bundleOf(KEY_SONG to song)
		_action.value = MainAction.OpenSongDetails(bundle)
	}

	fun onRefreshSongs() {
		loadSongs()
	}

	fun onSongDetailsResult(songBundle: Bundle) {
		songBundle.getParcelable<Song>(KEY_SONG)?.let { updatedSong ->
			val newSongs = mutableListOf<Song>()
			_songs.value?.forEach { song ->
				if (song.title == updatedSong.title) {
					newSongs.add(
						song.copy(
							rating = updatedSong.rating,
							isFavorite = updatedSong.isFavorite
						)
					)
				} else if (song.isFavorite && updatedSong.isFavorite) {
					newSongs.add(song.copy(isFavorite = false))
				} else newSongs.add(song)
			}
			_songs.value = newSongs
		}
	}
}

sealed class MainAction {

	object UpdateRefreshing : MainAction()
	class OpenSongDetails(val songBundle: Bundle) : MainAction()
}