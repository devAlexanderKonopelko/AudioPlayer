package com.konopelko.skooving.presentation.utils.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.konopelko.skooving.presentation.utils.livedata.Event

fun <T : Any, L : LiveData<Event<T>>> LifecycleOwner.observeEvent(liveData: L, func: (T) -> Unit) {
	liveData.observe(this) { event ->
		event?.getContentIfNotHandled()?.let(func)
	}
}