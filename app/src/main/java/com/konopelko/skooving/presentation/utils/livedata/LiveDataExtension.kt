package com.konopelko.skooving.presentation.utils.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.toEvent(): LiveData<Event<T>> {
	return map { Event(it) }
}

fun <T> LiveData<T>.await(
	timeout: Long = 200,
	timeUnit: TimeUnit = TimeUnit.MILLISECONDS
): T? {
	val liveData = this
	var data: T? = null
	val latch = CountDownLatch(1)

	val observer = Observer<T> { t ->
		data = t
		latch.countDown()
	}

	liveData.observeForever(observer)
	latch.await(timeout, timeUnit)
	liveData.removeObserver(observer)

	return data
}

/**
 * Await for the event live data.
 * Peek event's content.
 */
fun <T> LiveData<Event<T>>.awaitEvent() = await()!!.peekContent()