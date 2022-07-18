package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather

class PosterInfoViewModel (private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel(){

    val getLiveData = {
        liveData
    }

    fun loadWeatherData(weather: Weather){
        liveData.postValue(AppState.Success(weather))
    }

}