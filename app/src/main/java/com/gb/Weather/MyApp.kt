package com.gb.Weather

import android.app.Application
import androidx.room.Room
import com.gb.Weather.model.room.FavoriteCitiesDatabase
import com.gb.Weather.model.room.WeatherHistoryDatabase
import com.gb.Weather.shared.ROOM_DB_NAME
import com.gb.Weather.shared.ROOM_FAVORITE_DB


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this
    }

    companion object {
        private var myApp: MyApp? = null
        private var weatherHistoryDatabase: WeatherHistoryDatabase? = null
        private var favoriteCitiesDatabase: FavoriteCitiesDatabase? = null

        fun getMyApp() = myApp!!
        //База данных истории запросов
        fun getWeatherDatabase(): WeatherHistoryDatabase {
            if (weatherHistoryDatabase == null) {
                weatherHistoryDatabase = Room.databaseBuilder(
                    getMyApp(),
                    WeatherHistoryDatabase::class.java,
                    ROOM_DB_NAME
                ).allowMainThreadQueries() // TODO HW убрать allowMainThreadQueries
                    .build()
            }
            return weatherHistoryDatabase!!
        }
        //База данных избранных городов
        fun getFavoriteCitiesDatabase(): FavoriteCitiesDatabase {
            if (favoriteCitiesDatabase == null) {
                favoriteCitiesDatabase = Room.databaseBuilder(
                    getMyApp(),
                    FavoriteCitiesDatabase::class.java,
                    ROOM_FAVORITE_DB
                ).allowMainThreadQueries() // TODO HW убрать allowMainThreadQueries
                    .build()
            }
            return favoriteCitiesDatabase!!
        }
    }
}