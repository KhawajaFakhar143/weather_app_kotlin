package com.example.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object Constants {

    const val APP_ID : String = "ee3239b09481be9d6b4749919e2bb7ce"
    const val BASE_URL :String = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT: String = "metric"

    fun isNetworkAvailable(context: Context): Boolean{
        val connectivityManager = context.
        getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

           return when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
               activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
               activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return  true
               else -> return false
            }
        }else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}