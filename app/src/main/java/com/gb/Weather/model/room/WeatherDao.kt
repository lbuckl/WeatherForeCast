package com.gb.Weather.model.room

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntity")
    fun all(): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity WHERE city LIKE :city")
    fun getDataByWord(city: String): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: WeatherEntity)

    @Update
    fun update(entity: WeatherEntity)
    @Delete
    fun delete(entity: WeatherEntity)
}