package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.LocationCity
import com.gb.Weather.model.RepositoryListCity
import com.gb.Weather.model.RepositoryLocalImpl
import com.gb.Weather.model.dto.WeatherDTO

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryList = RepositoryLocalImpl()
        liveData
    }

    //region переключатель списков
    fun getWeatherListForRussia() = sentRequest(LocationCity.Russian)
    fun getWeatherListForWorld() = sentRequest(LocationCity.World)
    fun getWeatherListForFavorite() = sentRequest(LocationCity.Favorite)

    /* endregion */

    //данные для списка городов. Тригерит загрузку списка городов
    private fun sentRequest(locationCity: LocationCity) {
        liveData.postValue(AppState.LoadCities(repositoryList.getListWeather(locationCity)))
    }
    fun loadWeather(weather: Weather){
        liveData.postValue(AppState.Loading(weather.city.lat,weather.city.lon))
    }

    fun printWeatherPoster(weatherDTO: WeatherDTO){
        liveData.postValue(AppState.Success(weatherDTO))
    }

    //функция проверки состояния соединения
    private val isConnection = {false}
}
