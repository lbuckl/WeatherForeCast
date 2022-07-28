package com.gb.Weather.model.room

import androidx.room.*
import com.gb.Weather.MyApp

@Dao
interface WeatherDao {

    //region общие запросы
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weatherEntity: WeatherEntity)

    @Update
    fun update(weatherEntity: WeatherEntity)

    @Delete
    fun delete(weatherEntity: WeatherEntity)
    //endregion

    //Чистит всю базу
    @Query ("DELETE FROM WeatherEntity")
    fun clearHistory()

    //Возвращает всю базу
    @Query("SELECT * FROM WeatherEntity")
    fun getEntityList(): List<WeatherEntity>

    //Возвращает всю базу в обратном порядке
    @Query("SELECT * FROM WeatherEntity ORDER BY id DESC")
    fun getEntityListInvert(): List<WeatherEntity>
}