package com.gb.Weather.model.room

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntity")
    fun getEntityList(): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity WHERE city LIKE :city")
    fun getEntityByCity(city: String): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weatherEntity: WeatherEntity)

    @Update
    fun update(weatherEntity: WeatherEntity)
    @Delete
    fun delete(weatherEntity: WeatherEntity)
}