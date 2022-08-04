package com.konopelko.skooving.presentation.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.konopelko.skooving.R
import com.konopelko.skooving.databinding.FragmentMainBinding
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.presentation.base.fragment.BaseFragment
import com.konopelko.skooving.presentation.main.adapter.SongsAdapter
import com.konopelko.skooving.presentation.song.SongFragment
import com.konopelko.skooving.presentation.utils.lifecycle.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>() {

	val viewModel: MainViewModel by viewModel()

	override val layout: Int = R.layout.fragment_main

	override fun setupViewModelBinding() {
		binding.viewModel = viewModel
	}

	private val adapter by lazy {
		SongsAdapter { song ->
			viewModel.onSongClicked(song)
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupSongsRecyclerView()
		setupOnRefreshListener()
		setOnSongDetailsResultListener()
		observeEvent(viewModel.action, ::handleActions)
	}

	private fun setOnSongDetailsResultListener() {
		setFragmentResultListener(SongFragment.KEY_SONG_FRAGMENT) { _, bundle ->
			viewModel.onSongDetailsResult(bundle)
		}
	}

	private fun setupSongsRecyclerView() {
		binding.songRecyclerView.adapter = adapter
		binding.songRecyclerView.itemAnimator = object : DefaultItemAnimator() {
			override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean =
				true
		}
	}

	private fun setupOnRefreshListener() {
		binding.mainRefreshLayout.setOnRefreshListener {
			viewModel.onRefreshSongs()
		}
	}

	private fun handleActions(action: MainAction) {
		when (action) {
			is MainAction.UpdateRefreshing -> updateIsRefreshing()
			is MainAction.OpenSongDetails  -> openSongDetailsScreen(action.songBundle)
		}
	}

	private fun updateIsRefreshing() {
		if(binding.mainRefreshLayout.isRefreshing)
			binding.mainRefreshLayout.isRefreshing = false
	}

	private fun openSongDetailsScreen(songBundle: Bundle) {
		findNavController().navigate(R.id.action_mainFragment_to_songFragment, songBundle)
	}
}