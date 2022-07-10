package com.gb.Weather.model

import com.gb.Weather.domain.Weather

class RepositoryRemoteImpl:RepositorySingleCity,RepositoryListCity {
    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread{
            Thread.sleep(300L)
        }.start()
        return Weather()
    }

    override fun getListWeather(locationCity: LocationCity): List<Weather> {
        Thread{
            Thread.sleep(200L)
        }.start()
        return listOf(Weather())
    }
}