package com.gb.Weather.shared

import com.gb.Weather.BuildConfig
import com.gb.Weather.BuildConfig.WEATHER_API_KEY
import com.gb.Weather.model.dto.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {

    fun requestWeatherTDO(lat: Double,lon: Double){
        WEATHER_API_KEY
        val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
        var myConnection: HttpsURLConnection? = null
        myConnection = uri.openConnection() as HttpsURLConnection
        myConnection.readTimeout = 5000
        myConnection.addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
        //Выполняем запрос
        Thread{
            val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
            val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
        }.start()
    }
}