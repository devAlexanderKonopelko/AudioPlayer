package com.konopelko.skooving.presentation.main.adapter

import com.konopelko.skooving.databinding.ItemSongBinding
import com.konopelko.skooving.domain.entity.Song
import com.konopelko.skooving.presentation.base.viewholder.BaseViewHolder

class SongsViewHolder(
	override val binding: ItemSongBinding,
	private val adapter: SongsAdapter
) : BaseViewHolder<Song>(binding) {

	init {
		binding.viewHolder = this
	}

	override fun bind(item: Song) {
		binding.song = item
	}

	fun onSongClicked(song: Song) = adapter.onSongClicked(song)

	fun onFavoriteClicked(song: Song) {
		updateSong(song.copy(isFavorite = !song.isFavorite))
		adapter.onSongFavoriteClicked(song, !song.isFavorite)
	}

	private fun updateSong(song: Song) {
		binding.song = song
	}
}