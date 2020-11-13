package com.craft.furniture.data.remote

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.craft.furniture.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Intercept Internet Connectivity while doing network call.
 * Throws [NoInternetException] if connectivity issue occurs.
 */
@Suppress("DEPRECATION")
class NetworkInterceptor @Inject constructor(
    context: Application
) : Interceptor {
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable()){
            throw NoInternetException("Make sure you have working network connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean{

        val connectivityManager: ConnectivityManager
                = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities
                    = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            try {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: Exception) {
            }
        }
        return false
    }
}
