package com.gb.Weather.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.MainActivity
import com.gb.Weather.MyApp
import com.gb.Weather.domain.City
import com.gb.Weather.model.*
import com.gb.Weather.shared.LAST_LIST
import com.gb.Weather.shared.SAVE_CITYES_NAMES

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<WeatherListAppState> = MutableLiveData<WeatherListAppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity
    private lateinit var repositoryRequestHistory: RepositoryRequestHistory
    private lateinit var repositoryFavoriteCity: RepositoryFavoriteCity
    private lateinit var locCity: LocationCity
    val sharedPref: SharedPreferences = MyApp.getMyApp()
        .getSharedPreferences(SAVE_CITYES_NAMES, Context.MODE_PRIVATE)

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryList = RepositoryLocalImpl()
        repositoryRequestHistory = RepositoryRequestHistory()
        repositoryFavoriteCity = RepositoryFavoriteCity()
        liveData
    }


    //region переключатель списков
    fun getCityListForRussia() = sentRequest(LocationCity.Russian)
    fun getCityListForWorld() = sentRequest(LocationCity.World)
    fun getCityListForFavorite() = sentRequest(LocationCity.Favorite)
    //endregion

    //Возврат последнего сохранённого списка в SharedPref
    fun getLastCityList(){
        return when (sharedPref.getInt(LAST_LIST,1)){
            (1) -> getCityListForFavorite()
            (2) -> getCityListForRussia()
            (3) -> getCityListForWorld()
            else -> {getCityListForFavorite()}
        }
    }

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

    fun deleteFavoriteCity(city: City){
        //repositoryList.deleteFavoriteCity(city)
        repositoryFavoriteCity.deleteCityFromRoom(city)
        Thread.sleep(200)
        refresh()
    }

    fun refresh(){
        sentRequest(locCity)
    }
}

