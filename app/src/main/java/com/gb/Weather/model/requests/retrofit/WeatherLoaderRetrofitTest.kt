package com.gb.Weather.model.requests.retrofit

import android.util.AndroidRuntimeException
import android.util.Log
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RemoteRequest
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.shared.buildWeatherFromDTO
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

class WeatherLoaderRetrofitTest {
    private val retrofitImpl = Retrofit.Builder()

    fun requestWeather(city: City):Weather? {

        val lat: Double = city.lat
        val lon: Double = city.lon
        try {
            retrofitImpl.baseUrl("https://api.weather.yandex.ru")
            retrofitImpl.addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            val api = retrofitImpl.build().create(WeatherAPI::class.java)
            val weatherDTO = api.getWeather(BuildConfig.WEATHER_API_KEY,lat,lon).execute().body()
            return buildWeatherFromDTO(city,weatherDTO!!)
        }catch (e: IllegalStateException){
            e.printStackTrace()
            Log.d("@@@","IllegalStateException")
            return null
        }catch (e: RuntimeException){
            e.printStackTrace()
            Log.d("@@@","RuntimeException")
            return null
        }catch (e: UnknownHostException){
            e.printStackTrace()
            Log.d("@@@","UnknownHostException")
            return null
        }

    }
}