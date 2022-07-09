package com.gb.k_2135_2136_2.model
import com.gb.k_2135_2136_2.domain.Weather

interface RepositorySingleCity{
    fun getWeather(lat: Double, lon: Double):Weather
}

interface RepositoryListCity{
    fun getListWeather(locationCity: LocationCity):List<Weather>
}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}