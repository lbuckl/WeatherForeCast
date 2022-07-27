package com.gb.Weather.model
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

//Интерфейс репозитория для хранения данных по погоде в определённом городе
interface RepositorySingleCity{
    fun setCity(city: City)
    fun getCity():City
}
//Интерфейс репозитория для хранения списка с городами и погоды в них
interface RepositoryListCity{fun getListWeather(locationCity: LocationCity):List<City>}

interface RemoteRequest{
    fun requestWeather(city: City, resultCB: CallBackResult, errorCB: CallBackError)
}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}