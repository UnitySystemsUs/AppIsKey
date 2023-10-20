package com.example.appiskey.utils

class OneShotEvent<out T>(private val content: T) {
    private var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled)
            null
        else {
            hasBeenHandled = true
            content
        }
    }
}