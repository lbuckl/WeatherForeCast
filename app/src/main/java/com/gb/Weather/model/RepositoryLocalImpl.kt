package com.gb.Weather.model

import com.gb.Weather.MyApp
import com.gb.Weather.domain.City
import com.gb.Weather.domain.getRussianCities
import com.gb.Weather.domain.getWorldCities
import com.gb.Weather.shared.entityListToCityList

class RepositoryLocalImpl:RepositoryListCity {
    override fun getListCity(locationCity: LocationCity): List<City> {
        return when(locationCity){
            is LocationCity.Russian -> getRussianCities()
            is LocationCity.World-> getWorldCities()
            is LocationCity.Favorite -> getFavoriteCities()
        }
    }

    private fun getFavoriteCities():List<City>{
        MyApp.getFavoriteCitiesDatabase().favoriteCityDao().getEntityList().let {
            return entityListToCityList(it)
        }
    }
}