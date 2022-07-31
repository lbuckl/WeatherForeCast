package com.gb.Weather.model.requests

import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.YANDEX_API_KEY_NAME
import com.gb.Weather.shared.getLines
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

object WeatherLoaderTest {
    /**
     * Функция принимает на вход объект для запроса
     * по нему выполняет запрос погоды на яндекс и получает ответ в формате JSON
     * далее преобразует его в формат Weather
     * Выполняет коллбэк с результатом(если он получен) или ошибки
     */

    fun requestWeather(city: City):Weather? {
        var weatherData: Weather?
        val lat: Double = city.lat
        val lon: Double = city.lon
        var myConnection: HttpsURLConnection? = null
        val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            myConnection = uri.openConnection() as HttpsURLConnection
            myConnection.apply {
                readTimeout = 5000
                addRequestProperty(YANDEX_API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
            }
            try {
                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                    if (weatherDTO != null) {
                        weatherData = Weather(city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike,"")
                        return weatherData
                    } else {
                        weatherData = null
                        return weatherData
                    }
            }catch (e:RuntimeException){
                e.printStackTrace()
                Log.d("@@@","RuntimeException")
                weatherData = null
                return weatherData
            }catch (e: FileNotFoundException){
                e.printStackTrace()
                Log.d("@@@","FileNotFoundException")
                weatherData = null
                return weatherData
            }catch (e:UnknownHostException){
                Log.d("@@@","UnknownHostException")
                weatherData = null
                return weatherData
            }finally {
                myConnection.disconnect()
            }
    }
}