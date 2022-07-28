package com.gb.Weather.model

import com.gb.Weather.domain.City
import com.gb.Weather.domain.getFavoriteCities
import com.gb.Weather.domain.getRussianCities
import com.gb.Weather.domain.getWorldCities

class RepositoryLocalImpl:RepositoryListCity {
    override fun getListCity(locationCity: LocationCity): List<City> {
        return when(locationCity){
            is LocationCity.Russian -> getRussianCities()
            is LocationCity.World-> getWorldCities()
            is LocationCity.Favorite -> getFavoriteCities()
        }
    }
}