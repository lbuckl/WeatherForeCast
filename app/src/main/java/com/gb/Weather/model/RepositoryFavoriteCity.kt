package com.gb.Weather.model

import android.os.Handler
import android.os.Looper
import com.gb.Weather.MyApp
import com.gb.Weather.domain.City
import com.gb.Weather.shared.cityToEntity
import com.gb.Weather.shared.entityListToCityList

/**
 * Репозиторий для работы с БД избранных городов
 */
class RepositoryFavoriteCity:RoomCityExchange {
    override fun getListCity(locationCity: LocationCity): List<City> {
            MyApp.getFavoriteCitiesDatabase().favoriteCityDao().getEntityList().let {
                return entityListToCityList(it)
            }
    }

    override fun addCityToRoom(city: City) {
        Thread{
            MyApp.getFavoriteCitiesDatabase().favoriteCityDao().insert(cityToEntity(city))
        }.start()
    }

    override fun deleteCityFromRoom(city: City) {
        Thread{
            MyApp.getFavoriteCitiesDatabase().favoriteCityDao().deleteByCity(city.name)
        }.start()
    }
}