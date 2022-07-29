package com.gb.Weather.model

import android.os.Handler
import android.os.Looper
import com.gb.Weather.MyApp
import com.gb.Weather.domain.City
import com.gb.Weather.shared.cityToEntity
import com.gb.Weather.shared.entityListToCityList

class RepositoryFavoriteCity:RoomCityExchange {
    override fun getListCity(locationCity: LocationCity): List<City> {
            MyApp.getFavoriteCitiesDatabase().favoriteCityDao().getEntityList().let {
                return entityListToCityList(it)
            }
    }

    override fun addCityToRoom(city: City) {
        MyApp.getFavoriteCitiesDatabase().favoriteCityDao().insert(cityToEntity(city))
    }

    override fun deleteCityFromRoom(city: City) {
        MyApp.getFavoriteCitiesDatabase().favoriteCityDao().deleteByCity(city.name)
    }
}