package com.gb.Weather.model

import com.gb.Weather.domain.Weather

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(lat: Double, lon: Double): Weather {
        Thread{ Thread.sleep(300L)}.start()
        return Weather()
    }
}