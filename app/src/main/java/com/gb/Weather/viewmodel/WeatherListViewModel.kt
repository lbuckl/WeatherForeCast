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
    ViewModel(),CallBackResult,CallBackError{

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
        WeatherLoader.requestWeatherTDO(weather,this,this)
        //RepositoryRemoteImpl.setWeather(weather)
    }

    //получили колбэк от запроса погоды
    override fun returnedResult(weather: Weather) {
        RepositoryRemoteImpl.setWeather(weather)
        //Перевели вью фрагмента погоды в состояние Успешно, передаём данные
        PosterWeatherFragment.viewModel_poster.loadWeatherData(weather)
        //Возвращаем фрагмент в начальное состояние
        sentRequest(locCity)
        //liveData.postValue(AppState.Success(weather))
    }

    //получили ошибку от запроса погоды
    override fun setError(errorMsg: String) {
        liveData.postValue(AppState.Error(Exception("Ошибка загрузки: $errorMsg")))
    }

    fun refresh(){
        sentRequest(locCity)
    }
}
