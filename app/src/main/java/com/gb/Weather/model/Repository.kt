package com.gb.Weather.model
import com.gb.Weather.domain.Weather

interface RepositorySingleCity{
    fun setWeather(weather: Weather)
    fun getWeather():Weather
}

interface RepositoryListCity{fun getListWeather(locationCity: LocationCity):List<Weather>}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}