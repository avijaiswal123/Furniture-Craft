package com.craft.furniture.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import com.craft.furniture.di.scopes.AppScope
import javax.inject.Inject

/**
 * Observe Live changes in NetworkConnection.
 */
@AppScope
class NetworkLiveData @Inject constructor(
     private val context: Application
): LiveData<Boolean>() {

    private lateinit var connectivityManager :ConnectivityManager
    private val networkCallback = NetworkCallBack()

    init {
        initConnectivityManager()
    }

    private fun initConnectivityManager() {
        connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    }

    /**
     * Register to [NetworkCallBack] when [LiveData] is Active
     */
    override fun onActive() {
        super.onActive()
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().build(),
            networkCallback
        )
    }
    /**
     * UnRegister to [NetworkCallBack] when [LiveData] is InActive
     */
    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(
            networkCallback
        )
    }


    inner class NetworkCallBack : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }
}

