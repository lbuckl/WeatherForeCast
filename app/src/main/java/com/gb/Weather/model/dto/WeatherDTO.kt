package com.gb.Weather.model.dto


import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("now")
    val now: Int,
    @SerializedName("now_dt")
    val nowDt: String,
    //Объект информации о населенном пункте
    @SerializedName("info")
    val info: Info,
    //Объект фактической информации о погоде.
    @SerializedName("fact")
    val fact: Fact,
    //Объект прогнозной информации о погоде.
    @SerializedName("forecast")
    val forecast: Forecast
)