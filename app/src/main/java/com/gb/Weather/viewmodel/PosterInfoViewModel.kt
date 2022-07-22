package com.gb.Weather.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.shared.WeatherLoader

class PosterInfoViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(), CallBackResult, CallBackError {

    lateinit var repositoryWeather: RepositorySingleCity
    val getLiveData = {
        repositoryWeather = RepositoryRemoteImpl
        liveData
    }

    fun init(){
        if (liveData.value == null){
            liveData.postValue(AppState.Empty)
        }
    }

    fun getWeather(){
        val weather = RepositoryRemoteImpl.getWeather()
        Log.d("@@@","LoadWeather")
        WeatherLoader.requestWeatherTDO(weather,this,this)
        liveData.postValue(AppState.Loading(weather))
    }

    override fun setError(errorMsg: String) {
        Log.d("@@@","ErrorPoster")
        liveData.postValue(AppState.Error(Exception(errorMsg)))
    }

    override fun returnedResult(weather: Weather) {
        Log.d("@@@","SuccesPoster")
        liveData.postValue(AppState.Success(weather))
    }

}