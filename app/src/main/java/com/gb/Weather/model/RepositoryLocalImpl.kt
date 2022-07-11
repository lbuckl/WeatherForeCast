package com.gb.Weather.model

import com.gb.Weather.domain.*

//@@@ записал return перед when, опустил скобки
class RepositoryLocalImpl:RepositoryListCity {
    override fun getListWeather(locationCity: LocationCity): List<Weather> {
        return when(locationCity){
            is LocationCity.Russian -> getRussianCities()
            is LocationCity.World-> getWorldCities()
            is LocationCity.Favorite -> getFavoriteCities()
        }
    }
}