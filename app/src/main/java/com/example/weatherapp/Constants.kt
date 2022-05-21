package com.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

//to check if we have internet or not
object Constants{

    const val APP_ID: String = "57e8c296cf429a3bd66faf9caa723bda"
    const val BASE_URL: String = "http://api.openweathermap.org/data/"
    const val METRIC_UNIT : String = "metric"
    const val PREFERENCE_NAME : String = "WeatherAppPreference"
    const val WEATHER_RESPONSE_DATA : String = "weather_response_data"

    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager = context.
        getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            //if the active network doesn't exist return false
            val network = connectivityManager.activeNetwork ?: return false

            //checks if the active network capabilities doesn't exist return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when{
                //return true if there is wifi, cellular data or ethernet
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }

        }else{
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}