package com.gb.Weather.model

import com.gb.Weather.MyApp
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.HISTORY_LIMIT
import com.gb.Weather.shared.entityListToWeatherList
import com.gb.Weather.shared.weatherToEntity

class RepositoryRequestHistory:WeatherRequestHistory {
    //Вернуть список отсортированный в порядке обратном добавлению объектов
    override fun getHistoryList(): List<Weather> {
        return entityListToWeatherList(MyApp.getWeatherDatabase().weatherDao().getEntityListInvert())
    }

    fun getSortedHistory(isRight:Boolean):List<Weather> {
        if (isRight) return entityListToWeatherList(MyApp.getWeatherDatabase().weatherDao().getEntityListRightSort())
        else return entityListToWeatherList(MyApp.getWeatherDatabase().weatherDao().getEntityListLeftSort())
    }

    override fun addWeatherToHistory(weather: Weather) {
        MyApp.getWeatherDatabase().weatherDao().insert(weatherToEntity(weather))
        clearLimit()
    }

    override fun clearHistory() {
        Thread{MyApp.getWeatherDatabase().weatherDao().clearHistory()}.start()
    }

    //Функция удаляет истоию сверх лимита
    private fun clearLimit(){
        val baseSize = MyApp.getWeatherDatabase().weatherDao().getEntityList().size
        if (baseSize > HISTORY_LIMIT){

            repeat(baseSize-HISTORY_LIMIT){
                MyApp.getWeatherDatabase().weatherDao().getEntityList()[0].let {
                    MyApp.getWeatherDatabase().weatherDao().delete(it)
                }
            }
        }
    }
}