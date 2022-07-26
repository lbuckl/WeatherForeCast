package com.gb.Weather.model.requests.retrofit

import android.util.AndroidRuntimeException
import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RemoteRequest
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.shared.buildWeatherFromDTO
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

class WeatherLoaderRetrofitTest {
    private val retrofitImpl = Retrofit.Builder()

    fun requestWeather(weather: Weather):Weather? {

        val lat: Double = weather.city.lat
        val lon: Double = weather.city.lon
        try {
            retrofitImpl.baseUrl("https://api.weather.yandex.ru")
            retrofitImpl.addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))

            val api = retrofitImpl.build().create(WeatherAPI::class.java)
            val weatherDTO = api.getWeather(BuildConfig.WEATHER_API_KEY,lat,lon).execute().body()
            return buildWeatherFromDTO(weather,weatherDTO!!)
        }catch (e: IllegalStateException){
            e.printStackTrace()
            return null
        }catch (e: RuntimeException){
            e.printStackTrace()
            return null
        }catch (e: UnknownHostException){
            e.printStackTrace()
            return null
        }

    }
}