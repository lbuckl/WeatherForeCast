package com.gb.Weather.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WeatherEntity::class), version = 1)
abstract class WeatherHistoryDatabase: RoomDatabase() {
    abstract fun weatherDao():WeatherDao
}