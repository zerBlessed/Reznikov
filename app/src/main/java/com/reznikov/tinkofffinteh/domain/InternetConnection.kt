package com.reznikov.tinkofffinteh.domain

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class InternetConnection(private val context: Context) {
    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isAvailable(): Boolean {
        val cap = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (cap != null) {
            if (cap.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (cap.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (cap.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }

        return false
    }
}