package com.gb.Weather.model.dto


import com.google.gson.annotations.SerializedName

data class Part(
    @SerializedName("part_name")
    val partName: String,
    @SerializedName("temp_min")
    val tempMin: Int,
    @SerializedName("temp_avg")
    val tempAvg: Int,
    @SerializedName("temp_max")
    val tempMax: Int,
    @SerializedName("temp_water")
    val tempWater: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mm")
    val pressureMm: Int,
    @SerializedName("pressure_pa")
    val pressurePa: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("prec_mm")
    val precMm: Int,
    @SerializedName("prec_prob")
    val precProb: Int,
    @SerializedName("prec_period")
    val precPeriod: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("daytime")
    val daytime: String,
    @SerializedName("polar")
    val polar: Boolean
)