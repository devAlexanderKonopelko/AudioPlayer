package com.konopelko.skooving.presentation.utils.livedata

class Event<T>(content: T?) {

	private val eventContent: T
	private var hasBeenHandled = false

	init {
		requireNotNull(content) { "null values in Event are not allowed." }
		eventContent = content
	}

	fun getContentIfNotHandled(): T? {
		return if (hasBeenHandled) {
			null
		} else {
			hasBeenHandled = true
			eventContent
		}
	}

	fun hasBeenHandled(): Boolean {
		return hasBeenHandled
	}

	fun peekContent(): T = eventContent
}