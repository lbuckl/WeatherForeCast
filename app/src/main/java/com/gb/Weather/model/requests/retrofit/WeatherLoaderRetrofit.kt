package com.gb.Weather.model.requests.retrofit

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

class WeatherLoaderRetrofit:RemoteRequest {
    override fun requestWeather(weather: Weather, resultCB: CallBackResult, errorCB: CallBackError) {
        val retrofitImpl = Retrofit.Builder()
        val lat: Double = weather.city.lat
        val lon: Double = weather.city.lon

        retrofitImpl.baseUrl("https://api.weather.yandex1.ru")
        retrofitImpl.addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        try {
            val api = retrofitImpl.build().create(WeatherAPI::class.java)
            api.getWeather(BuildConfig.WEATHER_API_KEY,lat,lon).enqueue(object : Callback<WeatherDTO> {
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if(response.isSuccessful&&response.body()!=null){
                        resultCB.returnResult(buildWeatherFromDTO(weather,response.body()!!))
                    }
                    else{
                        errorCB.setError("Request error!!!")
                        when (response.code()){
                            in (300..399) -> Log.d("@@@", "Request error (REDIRECT)!!!")
                            in (400..499) -> Log.d("@@@","Request error (OUTPUT DATA)!!!")
                            in (500..599) -> Log.d("@@@","Request error (INPUT DATA)!!!")
                            else -> Log.d("@@@","Request error (UNKNOWN)!!!")
                        }

                    }
                }
                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    errorCB.setError("Request error!!!")
                }
            })

        }catch (e: IllegalStateException){
            e.printStackTrace()
            errorCB.setError("Request error (Invalid URL)")
        }

    }
}