package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.*

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    private lateinit var repositoryList: RepositoryListCity
    /*
    fun getLiveData():MutableLiveData<AppState>{
        choiceRepository()
        return liveData
    }*/

    //@@@ преобразовал в лямбда
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
    //@@@ упростил до 1 строки
    //@@@ из лога стало понятно, что 1 и 2 строка равноценны имеют тип void и возвращают Unit
    fun getWeatherListForRussia() {sentRequest(LocationCity.Russian)}
    fun getWeatherListForWorld() = sentRequest(LocationCity.World)
    fun getWeatherListForFavorite() = sentRequest(LocationCity.Favorite)
    /* endregion */

    //данные для списка городов. Тригерит загрузку списка городов
    private fun sentRequest(locationCity: LocationCity) {
        liveData.postValue(AppState.LoadCities(repositoryList.getListWeather(locationCity)))
        //liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))
        //Имитируем загрузку
        //liveData.value = AppState.Loading
        /*
        liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))
        */
    }

    //@@@ упростил до 1 строки
    //данные для постера. Тригерит открытие постера
    fun openPoster(weather: Weather) = liveData.postValue(AppState.Success(weather))

    //@@@ преобразовал в лямбда, проверил в testConnection отработку
    //Имитация результата состояния сединения с БД
    private fun isConnection2():Boolean{
        return false
    }

    private val isConnection = {false}

    //@@@
    fun testConnection(){
        when(isConnection()) {
            true -> {}
            false -> {}
        }

        when (isConnection2()){
            true -> {}
            false -> {}
        }
    }
}
