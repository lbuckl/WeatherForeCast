package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity
import com.gb.Weather.model.dto.WeatherDTO

class PosterInfoViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){
    lateinit var repositoryWeatherCity: RepositorySingleCity

    fun setLiveData(weather: Weather){
        repositoryWeatherCity
    }

    val getLiveData = {
        repositoryWeatherCity = RepositoryRemoteImpl()
        liveData
    }

    fun postWeatherData(weather: Weather, weatherDTO: WeatherDTO){
        liveData.postValue(AppState.Success(weather))
    }

}