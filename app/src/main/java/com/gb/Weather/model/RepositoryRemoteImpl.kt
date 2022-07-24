package com.gb.Weather.model
import android.os.Handler
import com.gb.Weather.domain.Weather
import com.gb.Weather.domain.getDefaultCity
import com.gb.Weather.model.requests.WeatherLoader
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

object RepositoryRemoteImpl:RepositorySingleCity {
    private var data = Weather(getDefaultCity(),0,0)

    override fun setWeather(weather: Weather) {
        data = weather
    }

    override fun getWeather(): Weather{
        return data
    }

}