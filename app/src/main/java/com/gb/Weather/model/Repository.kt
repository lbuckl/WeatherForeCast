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
//Интерфейс репозитория для хранения списка с городами
interface RepositoryListCity{
    fun getListCity(locationCity: LocationCity):List<City>
}

//Интерфейс с колбэками для удалённого запроса погоды
interface RemoteRequest{
    fun requestWeather(city: City, resultCB: CallBackResult, errorCB: CallBackError)
}

//Интерфейс для запроса истории открытия погоды room
interface WeatherRequestHistory{
    fun getHistoryList():List<Weather>
    fun addWeatherToHistory(weather: Weather)
    fun clearHistory()
}

interface RoomCityExchange{
    fun getListCity(locationCity: LocationCity):List<City>
    fun addCityToRoom(city: City)
    fun deleteCityFromRoom(city: City)
}

sealed class LocationCity{
    object Russian:LocationCity()
    object World:LocationCity()
    object Favorite:LocationCity()
}