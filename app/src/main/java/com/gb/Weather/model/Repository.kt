package com.gb.Weather.model
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

//Интерфейс репозитория для хранения данных по погоде в определённом городе
interface RepositorySingleCity{
    fun setWeather(weather: Weather)
    fun getWeather():Weather
}
//Интерфейс репозитория для хранения списка с городами и погоды в них
interface RepositoryListCity{fun getListWeather(locationCity: LocationCity):List<Weather>}

interface RemoteRequest{
    fun requestWeather(weather: Weather, resultCB: CallBackResult, errorCB: CallBackError)
}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}