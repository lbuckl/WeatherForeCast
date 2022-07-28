package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.*

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<WeatherListAppState> = MutableLiveData<WeatherListAppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity
    private lateinit var repositoryRequestHistory: RepositoryRequestHistory
    private lateinit var locCity: LocationCity

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryList = RepositoryLocalImpl()
        repositoryRequestHistory = RepositoryRequestHistory()
        liveData
    }

    //region переключатель списков
    fun getCityListForRussia() = sentRequest(LocationCity.Russian)
    fun getCityListForWorld() = sentRequest(LocationCity.World)
    fun getCityListForFavorite() = sentRequest(LocationCity.Favorite)
    //endregion

    //данные для списка городов. Тригерит загрузку списка городов
    private fun sentRequest(locationCity: LocationCity) {
        locCity = locationCity
        liveData.postValue(WeatherListAppState.LoadCities(repositoryList.getListCity(locationCity)))
    }

    fun loadWeather(city: City){
        //Открываем постер
        liveData.postValue(WeatherListAppState.Loading(city))
        RepositoryRemoteImpl.setCity(city)
    }

    fun refresh(){
        sentRequest(locCity)
    }
}

