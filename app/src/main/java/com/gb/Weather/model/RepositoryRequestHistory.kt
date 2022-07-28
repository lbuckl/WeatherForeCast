package com.gb.Weather.model

import com.gb.Weather.MyApp
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.HISTORY_LIMIT
import com.gb.Weather.shared.entityListToWeatherList
import com.gb.Weather.shared.weatherToEntity

class RepositoryRequestHistory:WeatherRequestHistory {
    override fun getHistoryList(): List<Weather> {
        return entityListToWeatherList(MyApp.getWeatherDatabase().weatherDao().getEntityListInvert())
    }

    override fun addWeatherToHistory(weather: Weather) {
        MyApp.getWeatherDatabase().weatherDao().insert(weatherToEntity(weather))
        clearLimit()
    }

    override fun clearHistory() {
        MyApp.getWeatherDatabase().weatherDao().clearHistory()
    }

    private fun clearLimit(){
        val baseSize = MyApp.getWeatherDatabase().weatherDao().getEntityList().size
        if (baseSize > HISTORY_LIMIT){

            repeat(baseSize-HISTORY_LIMIT){
                MyApp.getWeatherDatabase().weatherDao().getEntityList()[0].id.let {
                    MyApp.getWeatherDatabase().weatherDao().deleteById(it)
                    }
                }
        }
    }
}