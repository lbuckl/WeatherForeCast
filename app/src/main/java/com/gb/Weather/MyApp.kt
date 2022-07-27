package com.gb.Weather

import android.app.Application
import androidx.room.Room
import com.gb.Weather.model.room.WeatherDatabase
import com.gb.Weather.shared.ROOM_DB_NAME


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this
    }

    companion object {
        private var myApp: MyApp? = null
        private var weatherDatabase: WeatherDatabase? = null
        fun getMyApp() = myApp!!
        fun getWeatherDatabase(): WeatherDatabase {
            if (weatherDatabase == null) {
                weatherDatabase = Room.databaseBuilder(
                    getMyApp(),
                    WeatherDatabase::class.java,
                    ROOM_DB_NAME
                ).allowMainThreadQueries() // TODO HW убрать allowMainThreadQueries
                    .build()
            }
            return weatherDatabase!!
        }
    }
}