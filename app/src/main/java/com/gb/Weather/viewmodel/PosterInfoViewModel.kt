package com.gb.Weather.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult
import com.gb.Weather.model.requests.WeatherLoader
import com.gb.Weather.model.requests.retrofit.WeatherLoaderRetrofit

class PosterInfoViewModel (private val liveData: MutableLiveData<PosterInfoAppState> = MutableLiveData<PosterInfoAppState>()) :
    ViewModel(), CallBackResult, CallBackError {

    lateinit var repositoryWeather: RepositorySingleCity
    private var errCount: Int = 0

    val getLiveData = {
        repositoryWeather = RepositoryRemoteImpl
        liveData
    }

    fun init(){
        errCount = 0
        if (liveData.value == null){
            liveData.postValue(PosterInfoAppState.Empty)
        }
    }

    fun getWeather(){
        val weather = RepositoryRemoteImpl.getWeather()
        if (errCount == 0 ) liveData.postValue(PosterInfoAppState.Loading(weather))
        when (errCount){
            0 -> {
                Log.d("@@@","LoadWeatherRetro")
                //WeatherLoader.requestWeather(weather,this,this)
                WeatherLoaderRetrofit().requestWeather(weather,this,this)
            }
            1 -> {
                Log.d("@@@","LoadWeatherHTTPS")
                WeatherLoader.requestWeather(weather,this,this)
                //WeatherLoaderRetrofit().requestWeather(weather,this,this)
            }
        }

    }

    override fun setError(errorMsg: String) {
            if (errCount == 0) {
                errCount++
                getWeather()
            }
            else {
                Log.d("@@@","ErrorPoster")
                liveData.postValue(PosterInfoAppState.Error(Exception(errorMsg)))
            }

    }

    override fun returnResult(weather: Weather) {
            Log.d("@@@","SuccesPoster")
            liveData.postValue(PosterInfoAppState.Success(weather))
    }

}