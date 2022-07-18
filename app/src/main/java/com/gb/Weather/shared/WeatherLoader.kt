package com.gb.Weather.shared

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {
    /**
     * Функция принимает на вход объект для запроса
     * по нему выполняет запрос погоды на яндекс и получает ответ в формате JSON
     * далее преобразует его в формат Weather
     * Выполняет коллбэк результата(если он получен) или ошибок
     */
    lateinit var weatherData:Weather

    fun requestWeatherTDO(weather: Weather, resultCB:CallBackResult, errorCB:CallBackError){
        weatherData = weather

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
                            weatherData = Weather(weather.city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike)
                            resultCB.returnedResult(weatherData)
                        } else errorCB.setError("Не корректные данные!!!")
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    Log.d("@@@","RuntimeException")
                    errorCB.setError("Ошибка запроса по API ключу!!!")
                }
            }.start()
    }
}