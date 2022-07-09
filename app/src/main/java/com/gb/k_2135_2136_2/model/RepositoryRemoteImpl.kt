package com.gb.k_2135_2136_2.model

import com.gb.k_2135_2136_2.domain.Weather

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