package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.LocationCity
import com.gb.Weather.model.RepositoryListCity
import com.gb.Weather.model.RepositoryLocalImpl
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.shared.WeatherLoader
import com.gb.Weather.view.Poster.PosterWeatherFragment

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity
    private lateinit var locCity: LocationCity

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryList = RepositoryLocalImpl()
        liveData
    }

    //region переключатель списков
    fun getWeatherListForRussia() = sentRequest(LocationCity.Russian)
    fun getWeatherListForWorld() = sentRequest(LocationCity.World)
    fun getWeatherListForFavorite() = sentRequest(LocationCity.Favorite)
    //endregion

    //данные для списка городов. Тригерит загрузку списка городов
    private fun sentRequest(locationCity: LocationCity) {
        locCity = locationCity
        liveData.postValue(AppState.LoadCities(repositoryList.getListWeather(locationCity)))
    }

    fun loadWeather(weather: Weather){
        //Открываем постер
        liveData.postValue(AppState.Loading(weather))
        RepositoryRemoteImpl.setWeather(weather)
    }

    fun refresh(){
        sentRequest(locCity)
    }
}

