package com.gb.Weather.model
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.WeatherLoader

class RepositoryRemoteImpl:RepositorySingleCity {
    override fun getWeather(weatherDTO: WeatherDTO): WeatherDTO {
        return weatherDTO
    }
}