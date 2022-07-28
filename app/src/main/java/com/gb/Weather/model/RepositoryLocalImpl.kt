package com.gb.Weather.model

import com.gb.Weather.domain.*

class RepositoryLocalImpl:RepositoryListCity {
    override fun getListCity(locationCity: LocationCity): List<City> {
        return when(locationCity){
            is LocationCity.Russian -> getRussianCities()
            is LocationCity.World-> getWorldCities()
            is LocationCity.Favorite -> getFavoriteCities()
        }
    }
}