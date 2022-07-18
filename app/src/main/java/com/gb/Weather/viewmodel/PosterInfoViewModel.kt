package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.shared.WeatherLoader
import java.lang.Exception

class PosterInfoViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    val getLiveData = {
        liveData
    }


    fun loadWeatherData(weather: Weather){
        liveData.postValue(AppState.Success(weather))
        //WeatherLoader.requestWeatherTDO(RepositoryRemoteImpl.getWeather(),this,this)
        //liveData.postValue(AppState.Loading(RepositoryRemoteImpl.getWeather()))
    }

    /*override fun returnedResult(weather: Weather) {
        liveData.postValue(AppState.Success(weather))
    }

    override fun setError(errorMsg: String) {
        liveData.postValue(AppState.Error(Exception(errorMsg)))
    }*/
}