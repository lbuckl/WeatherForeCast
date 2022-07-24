package com.gb.Weather.model.requests.retrofit



import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.YANDEX_API_KEY_NAME
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v2/informers")
    fun getWeather(
        @Header(YANDEX_API_KEY_NAME) keyValue: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>
}