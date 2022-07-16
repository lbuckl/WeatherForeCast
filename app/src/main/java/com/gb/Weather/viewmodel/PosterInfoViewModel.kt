package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity

class PosterInfoViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){
    private lateinit var repositoryWeatherCity: RepositorySingleCity

    val getLiveData = {
        repositoryWeatherCity = RepositoryRemoteImpl()
        liveData
    }

    fun postWeatherData(lat: Double, lon: Double){
        //liveData.postValue(AppState.Loading(repositoryWeatherCity.getWeather(lat,lon)))
    }

}