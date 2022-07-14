package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.model.LocationCity
import com.gb.Weather.model.RepositoryListCity
import com.gb.Weather.model.RepositoryLocalImpl

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        choiceRepository()
        liveData
    }

    //Выбираем БД для загрузки
    private fun choiceRepository(){
        /*repository = if(isConnection()){
            RepositoryRemoteImpl()
        }else{
            RepositoryLocalImpl()
        }*/
        repositoryList = RepositoryLocalImpl()
    }

    //region переключатель списков
    fun getWeatherListForRussia() = sentRequest(LocationCity.Russian)
    fun getWeatherListForWorld() = sentRequest(LocationCity.World)
    fun getWeatherListForFavorite() = sentRequest(LocationCity.Favorite)
    /* endregion */

    //данные для списка городов. Тригерит загрузку списка городов
    private fun sentRequest(locationCity: LocationCity) {
        liveData.postValue(AppState.LoadCities(repositoryList.getListWeather(locationCity)))
        //liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))
        //Имитируем загрузку
        //liveData.value = AppState.Loading

        liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))

    }
    //функция проверки состояния соединения
    private val isConnection = {false}
}
