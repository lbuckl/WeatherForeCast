package com.gb.Weather.model.requests

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.City
import com.gb.Weather.model.RemoteRequest
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

object WeatherLoader:RemoteRequest {
    /**
     * Функция принимает на вход объект для запроса
     * по нему выполняет запрос погоды на яндекс и получает ответ в формате JSON
     * далее преобразует его в формат Weather
     * Выполняет коллбэк с результатом(если он получен) или ошибки
     */

    override fun requestWeather(city: City, resultCB: CallBackResult, errorCB: CallBackError){
        val lat: Double = city.lat
        val lon: Double = city.lon
        val handler = Handler(Looper.myLooper()!!)
            val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
            Thread {
                var myConnection: HttpsURLConnection? = null
                myConnection = uri.openConnection() as HttpsURLConnection
                myConnection.apply {
                    readTimeout = 5000
                    //addRequestProperty("YANDEX_API_KEY_NAME", BROKEN_API_KEY)
                    addRequestProperty(YANDEX_API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
                }
                try {
                    val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                    val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                    //val weatherDTO: WeatherDTO? = null
                    //handler.post {
                        if (weatherDTO != null) {
                            //weatherData = Weather(weather.city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike)
                            resultCB.returnResult(buildWeatherFromDTO(city,weatherDTO))
                        } else errorCB.setError("Не корректные данные!!!")
                    //}
                }catch (e:RuntimeException){
                    e.printStackTrace()
                    Log.d("@@@","RuntimeException")
                    errorCB.setError("Ошибка запроса по API ключу!!!")
                }catch (e: FileNotFoundException){
                    e.printStackTrace()
                    Log.d("@@@","FileNotFoundException")
                    errorCB.setError("Не корректные данные!!!")
                }catch (e:UnknownHostException){
                    e.printStackTrace()
                    Log.d("@@@","UnknownHostException")
                    errorCB.setError("Отсутствие связи с хостом!!!")
                }finally {
                    myConnection.disconnect()
                }
            }.start()
    }
}