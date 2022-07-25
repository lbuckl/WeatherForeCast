package com.gb.Weather.model

import com.gb.Weather.domain.Weather
import com.gb.Weather.domain.getFavoriteCities
import com.gb.Weather.domain.getRussianCities
import com.gb.Weather.domain.getWorldCities

class RepositoryLocalImpl:RepositoryListCity {
    override fun getListWeather(locationCity: LocationCity): List<Weather> {
        return when(locationCity){
            is LocationCity.Russian -> getRussianCities()
            is LocationCity.World-> getWorldCities()
            is LocationCity.Favorite -> getFavoriteCities()
        }
    }
}