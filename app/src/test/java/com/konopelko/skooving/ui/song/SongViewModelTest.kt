package com.konopelko.skooving.ui.song

import android.os.Bundle
import androidx.core.os.bundleOf
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.presentation.song.SongViewModel
import com.konopelko.skooving.presentation.utils.livedata.await
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.konopelko.skooving.presentation.song.SongAction
import com.konopelko.skooving.presentation.utils.livedata.awaitEvent
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SongViewModelTest {

	private lateinit var subject: SongViewModel

	private fun initViewModel(args: Bundle = Bundle()) {
		subject = SongViewModel(args = args)
	}

	private val testSong = Song(
		title = "",
		audio = "",
		cover = "",
		totalDurationMs = 0.0
	)

	@Before
	fun setup() {
		initViewModel(args = bundleOf(Song.KEY_SONG to testSong))
	}

	@Test
	fun `when on favorite clicked song data changes correctly`() {
		val expectedIsFavorite = !testSong.isFavorite
		subject.onFavoriteClicked()
		val realIsFavorite = subject.song.await()?.isFavorite
		assertThat(expectedIsFavorite).isEqualTo(realIsFavorite)
	}

	@Test
	fun `when song cover clicked song PlaySong action called`() {
		val expectedAction = SongAction.PlaySong
		subject.onCoverClicked()
		val realSongAction = subject.action.awaitEvent()
		assertThat(expectedAction).isEqualTo(realSongAction)
	}
}