package com.gb.Weather

import android.app.Application
import androidx.room.Room
import com.gb.Weather.model.room.WeatherHistoryDatabase
import com.gb.Weather.shared.ROOM_DB_NAME


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this
    }

    companion object {
        private var myApp: MyApp? = null
        private var weatherHistoryDatabase: WeatherHistoryDatabase? = null
        fun getMyApp() = myApp!!
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
    }
}