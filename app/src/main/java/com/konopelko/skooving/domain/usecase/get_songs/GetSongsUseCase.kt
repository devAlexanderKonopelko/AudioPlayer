package com.konopelko.skooving.domain.usecase.get_songs

import com.konopelko.skooving.data.utils.Result
import com.konopelko.skooving.domain.entity.Song

interface GetSongsUseCase {

	suspend operator fun invoke(): Result<List<Song>>
}