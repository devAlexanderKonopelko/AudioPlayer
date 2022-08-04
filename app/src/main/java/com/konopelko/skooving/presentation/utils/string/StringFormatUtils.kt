package com.konopelko.skooving.presentation.utils.string

import java.util.concurrent.TimeUnit

fun getTimeFormatString(time: Long) = String.format(
	"%02d:%02d",
	TimeUnit.MILLISECONDS.toMinutes(time),
	TimeUnit.MILLISECONDS.toSeconds(time)
)