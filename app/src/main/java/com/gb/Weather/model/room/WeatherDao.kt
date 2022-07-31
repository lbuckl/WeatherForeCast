package com.gb.Weather.model.room

import androidx.room.*

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


    @Query ("DELETE FROM WeatherEntity WHERE city = :cityDelete")
    fun deleteByCity(cityDelete: String)

    //Чистит всю базу
    @Query ("DELETE FROM WeatherEntity")
    fun clearHistory()

    //Возвращает всю базу
    @Query("SELECT * FROM WeatherEntity")
    fun getEntityList(): List<WeatherEntity>

    //Возвращает всю базу в обратном порядке
    @Query("SELECT * FROM WeatherEntity ORDER BY id DESC")
    fun getEntityListInvert(): List<WeatherEntity>

    //Возвращает всю базу отсортированную по городам в прямом порядке А-Я
    @Query("SELECT * FROM WeatherEntity ORDER BY city ASC")
    fun getEntityListRightSort(): List<WeatherEntity>

    //Возвращает всю базу отсортированную по городам в прямом порядке А-Я
    @Query("SELECT * FROM WeatherEntity ORDER BY city DESC")
    fun getEntityListLeftSort(): List<WeatherEntity>
}