package com.gb.Weather.model.dto


import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("obs_time")
    val obsTime: Int,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("temp_water")
    val tempWater: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mm")
    val pressureMm: Int,
    @SerializedName("pressure_pa")
    val pressurePa: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("daytime")
    val daytime: String,
    @SerializedName("polar")
    val polar: Boolean,
    @SerializedName("season")
    val season: String,
    @SerializedName("wind_gust")
    val windGust: Double
)