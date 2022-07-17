package com.gb.Weather.shared

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.BuildConfig.WEATHER_API_KEY
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.view.weatherlist.WeatherListFragment
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {

    fun requestWeatherTDO(weather: Weather){
        WEATHER_API_KEY
        val lat: Double = weather.city.lat
        val lon: Double = weather.city.lon
        val handler = Handler(Looper.myLooper()!!)

            val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            var myConnection: HttpsURLConnection? = null

            myConnection = uri.openConnection() as HttpsURLConnection
            myConnection.apply {
                readTimeout = 5000
                //addRequestProperty("X-Yandex-API-Key", BROKEN_API_KEY)
                addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
            }
            Thread {
                Thread.sleep(1000)
                try {
                    val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                    val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                    //val weatherDTO: WeatherDTO? = null
                    handler.post {
                        if (weatherDTO != null) {
                            val weatherData = Weather(weather.city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike)
                            WeatherListFragment.viewModel.printWeatherPoster(weatherData)
                        } else WeatherListFragment.viewModel.error("Не корректные данные!!!")
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.d("@@@","RuntimeException")
                    WeatherListFragment.viewModel.error("Ошибка запроса по API ключу!!!")
                }
            }.start()
    }
}