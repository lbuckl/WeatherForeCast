package com.gb.Weather.model.room

import androidx.room.*
import com.gb.Weather.MyApp
import com.gb.Weather.shared.ROOM_DB_NAME
import retrofit2.http.DELETE

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntity")
    fun getEntityList(): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity ORDER BY id DESC")
    fun getEntityListInvert(): List<WeatherEntity>

    @Query("SELECT * FROM WeatherEntity WHERE city LIKE :city")
    fun getEntityByCity(city: String): List<WeatherEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weatherEntity: WeatherEntity)

    @Update
    fun update(weatherEntity: WeatherEntity)

    @Delete
    fun delete(weatherEntity: WeatherEntity)

    @Query ("DELETE FROM WeatherEntity")
    fun clearHistory()
}