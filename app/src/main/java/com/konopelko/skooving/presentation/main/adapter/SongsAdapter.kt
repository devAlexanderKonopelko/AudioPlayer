package com.konopelko.skooving.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.konopelko.skooving.R
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.presentation.base.adapter.BaseRecyclerAdapter
import com.konopelko.skooving.presentation.utils.binding.adapter.BindableAdapter

class SongsAdapter(
	private val onSongClick: (Song) -> Unit
) : BaseRecyclerAdapter<Song, SongsViewHolder>(), BindableAdapter<List<Song>> {

	fun onSongClicked(song: Song) = onSongClick(song)

	override fun getItemLayoutId(viewType: Int): Int = R.layout.item_song

	override fun getViewHolder(
		inflater: LayoutInflater,
		layoutId: Int,
		parent: ViewGroup
	): SongsViewHolder =
		SongsViewHolder(DataBindingUtil.inflate(inflater, layoutId, parent, false), this)

	fun onSongFavoriteClicked(songFavorite: Song, isFavorite: Boolean) {
		var previousIsFavoriteIndex = -1
		val newSongs = mutableListOf<Song>()
		items.forEachIndexed { index, song ->
			if (song == songFavorite) {
				newSongs.add(song.copy(isFavorite = isFavorite))
			} else if (song.isFavorite && isFavorite) {
				newSongs.add(song.copy(isFavorite = false))
				previousIsFavoriteIndex = index
			} else newSongs.add(song)
		}
		items = newSongs
		if (previousIsFavoriteIndex != -1) notifyItemChanged(previousIsFavoriteIndex)
	}

	override fun setData(data: List<Song>?) {
		data?.let { updateItems(data) }
	}
}