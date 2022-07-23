package com.gb.Weather.model
import com.gb.Weather.domain.Weather
//Интерфейс репозитория для хранения данных по погоде в определённом городе
interface RepositorySingleCity{
    fun setWeather(weather: Weather)
    fun getWeather():Weather
}
//Интерфейс репозитория для хранения списка с городами и погоды в них
interface RepositoryListCity{fun getListWeather(locationCity: LocationCity):List<Weather>}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}