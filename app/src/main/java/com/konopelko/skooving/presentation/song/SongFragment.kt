package com.konopelko.skooving.presentation.song

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.konopelko.skooving.R
import com.konopelko.skooving.databinding.FragmentSongBinding
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.domain.entity.Song.Companion.KEY_SONG
import com.konopelko.skooving.presentation.base.fragment.BaseFragment
import com.konopelko.skooving.presentation.utils.lifecycle.observeEvent
import com.konopelko.skooving.presentation.utils.string.getTimeFormatString
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SongFragment : BaseFragment<FragmentSongBinding>() {

	val viewModel: SongViewModel by viewModel {
		parametersOf(arguments)
	}

	override val layout: Int = R.layout.fragment_song

	override fun setupViewModelBinding() {
		binding.viewModel = viewModel
	}

	private lateinit var mediaPlayer: MediaPlayer
	private val runnable: Runnable by lazy {
		Runnable {
			binding.songSeekBar.progress = mediaPlayer.currentPosition
			handler.postDelayed(runnable, 500)
		}
	}
	private val handler = Handler(Looper.getMainLooper())

	private var isSongLoaded = false

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupOnBackPressedListener()
		observeEvent(viewModel.action, ::handleActions)
	}

	private fun handleActions(action: SongAction) {
		when (action) {
			is SongAction.PlaySong         -> playSong()
			is SongAction.SetupMediaPlayer -> setupMediaPlayer(action.song)
		}
	}

	private fun setupOnBackPressedListener() {
		requireActivity().onBackPressedDispatcher.addCallback {
			val bundle = bundleOf(KEY_SONG to viewModel.song.value)
			setFragmentResult(KEY_SONG_FRAGMENT, bundle)
			isEnabled = false
			requireActivity().onBackPressed()
		}
	}

	private fun setupMediaPlayer(song: Song) {
		val audioAttributes = AudioAttributes.Builder()
			.setUsage(AudioAttributes.USAGE_MEDIA)
			.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
			.build()

		mediaPlayer = MediaPlayer()
		mediaPlayer.setAudioAttributes(audioAttributes)
		mediaPlayer.setDataSource(song.audio)
		mediaPlayer.prepareAsync()

		mediaPlayer.setOnPreparedListener {
			isSongLoaded = true
			setupSongSeekBar()
			setupSongDurationText()
			setupClickListeners()
		}

		mediaPlayer.setOnCompletionListener {
			binding.playBtn.visibility = View.VISIBLE
			binding.pauseBtn.visibility = View.GONE

			if (isSongLoaded) mediaPlayer.seekTo(0)
		}
	}

	private fun setupSongDurationText() {
		val duration = mediaPlayer.duration.toLong()
		binding.songDuration.text = getTimeFormatString(duration)
	}

	private fun setupSongSeekBar() {
		binding.songSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
			override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
				val currentPosition = mediaPlayer.currentPosition.toLong()
				if (fromUser) {
					mediaPlayer.seekTo(progress)
				}
				binding.songCurrentPosition.text = getTimeFormatString(currentPosition)
			}

			override fun onStartTrackingTouch(p0: SeekBar?) { /* no-op */ }
			override fun onStopTrackingTouch(p0: SeekBar?) { /* no-op */ }
		})
	}

	private fun setupClickListeners() {
		binding.playBtn.setOnClickListener {
			playSong()
		}

		binding.pauseBtn.setOnClickListener {
			binding.playBtn.visibility = View.VISIBLE
			binding.pauseBtn.visibility = View.GONE

			mediaPlayer.pause()
			handler.removeCallbacks(runnable)
		}

		binding.playForwardBtn.setOnClickListener {
			var currentPosition = mediaPlayer.currentPosition.toLong()
			val duration = mediaPlayer.duration.toLong()

			if (mediaPlayer.isPlaying && currentPosition != duration) {
				currentPosition += 5000
				binding.songCurrentPosition.text = getTimeFormatString(currentPosition)
				mediaPlayer.seekTo(currentPosition.toInt())
			}
		}

		binding.playBackBtn.setOnClickListener {
			var currentPosition = mediaPlayer.currentPosition.toLong()

			if (mediaPlayer.isPlaying) {
				currentPosition -= 5000
				binding.songCurrentPosition.text = getTimeFormatString(currentPosition)
				mediaPlayer.seekTo(currentPosition.toInt())
			}
		}
	}

	private fun playSong() {
		if (isSongLoaded) {
			binding.playBtn.visibility = View.INVISIBLE
			binding.pauseBtn.visibility = View.VISIBLE
			binding.songSeekBar.max = mediaPlayer.duration

			mediaPlayer.start()
			handler.postDelayed(runnable, 0)
		}
	}

	override fun onStop() {
		super.onStop()
		handler.removeCallbacks(runnable)
		mediaPlayer.release()
	}

	companion object {

		const val KEY_SONG_FRAGMENT = "key.fragment.song"
	}
}