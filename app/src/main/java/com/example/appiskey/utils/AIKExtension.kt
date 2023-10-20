package com.example.appiskey.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE)
    if (connectivityManager is ConnectivityManager) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
    return result
}

fun Context.stringToArrayList(tagStr: String): ArrayList<String> {
    val tags = tagStr.split(",").map { it.trim() }
    return ArrayList<String>(tags)
}

fun HashMap<String, Any>.appendRequestParam(): HashMap<String, Any> {
    this["q"] = "kitten"
    this["image_type"] = "photo"
    this["pretty"] = true
    return this
}
