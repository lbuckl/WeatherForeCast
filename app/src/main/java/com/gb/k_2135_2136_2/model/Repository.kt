package com.gb.k_2135_2136_2.model

import com.gb.k_2135_2136_2.domain.Weather

interface Repository {
    fun getListWeather():List<Weather>
    fun getWeather(lat: Double, lon: Double):Weather
}