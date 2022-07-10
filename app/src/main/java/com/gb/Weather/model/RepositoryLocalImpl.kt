package com.gb.Weather.model

import com.gb.Weather.domain.*

class RepositoryLocalImpl:RepositorySingleCity,RepositoryListCity {
    override fun getListWeather(locationCity: LocationCity): List<Weather> {
        when(locationCity){
            is LocationCity.Russian ->{
                return getRussianCities()
            }
            is LocationCity.World->{
                return getWorldCities()
            }
            is LocationCity.Favorite ->{
                return getFavoriteCities()
            }
        }
        return listOf()
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}