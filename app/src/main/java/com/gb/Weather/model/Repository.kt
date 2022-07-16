package com.gb.Weather.model
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO

interface RepositorySingleCity{fun getWeather(weatherDTO: WeatherDTO):WeatherDTO}

interface RepositoryListCity{fun getListWeather(locationCity: LocationCity):List<Weather>}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}