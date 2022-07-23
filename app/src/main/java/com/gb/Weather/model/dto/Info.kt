package com.gb.Weather.model.dto


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("url")
    val url: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)