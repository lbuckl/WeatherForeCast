package com.gb.Weather.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WeatherEntity::class), version = 2)
abstract class FavoriteCitiesDatabase: RoomDatabase() {
    abstract fun favoriteCityDao():WeatherDao
}