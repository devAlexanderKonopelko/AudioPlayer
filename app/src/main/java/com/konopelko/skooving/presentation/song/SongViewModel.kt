package com.konopelko.skooving.presentation.song

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.domain.entity.Song.Companion.KEY_SONG
import com.konopelko.skooving.presentation.utils.livedata.toEvent

class SongViewModel(
	args: Bundle
) : ViewModel() {

	private val _action = MutableLiveData<SongAction>()
	val action = _action.toEvent()

	private val _song = MutableLiveData<Song>()
	val song: LiveData<Song> = _song

	init {
		args.getParcelable<Song?>(KEY_SONG)?.let {
			_song.value = it
			_action.value = SongAction.SetupMediaPlayer(it)
		}
	}

	val onRatingChanged: (Float) -> Unit = {newRating ->
		_song.value?.let {
			_song.value = it.copy(rating = newRating)
		}
	}

	fun onFavoriteClicked() {
		_song.value?.let {
			_song.value = it.copy(isFavorite = !it.isFavorite)
		}
	}

	fun onCoverClicked() {
		_action.value = SongAction.PlaySong
	}
}

sealed class SongAction {

	class SetupMediaPlayer(val song: Song): SongAction()
	object PlaySong: SongAction()
}