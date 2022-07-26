package com.gb.Weather.viewmodel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryRemoteImpl
import com.gb.Weather.model.RepositorySingleCity
import com.gb.Weather.model.requests.WeatherLoader
import com.gb.Weather.model.requests.WeatherLoaderTest
import com.gb.Weather.model.requests.retrofit.WeatherLoaderRetrofit
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

class PosterInfoViewModel (private val liveData: MutableLiveData<PosterInfoAppState> = MutableLiveData<PosterInfoAppState>()) :
    ViewModel(), CallBackResult, CallBackError {

    lateinit var repositoryWeather: RepositorySingleCity
    private var errCount: Int = 0


    val getLiveData = {
        repositoryWeather = RepositoryRemoteImpl
        liveData
    }

    //Костыль для инициализации, так как фрагмент выдаёт ошибку,
    //если ни одно из состояний не установлено. Даже если указываешь else во when
    fun init(){
        errCount = 0
        if (liveData.value == null){
            liveData.postValue(PosterInfoAppState.Empty)
        }
    }

    /**
     * Функция берёт старую погоду из репозиторя, чтобы выставить во фрагменте город и координаты
     * пока идёт запрос на обновление погоды
     */
    fun getWeather(){
        val weather = RepositoryRemoteImpl.getWeather()
        liveData.postValue(PosterInfoAppState.Loading(weather))
        Log.d("@@@","LoadWeatherHTTPS")
        RepositoryRemoteImpl.getRemoteWeather(weather,this,this)
    }

    override fun setError(errorMsg: String) {
                Log.d("@@@","ErrorPoster")
                liveData.postValue(PosterInfoAppState.Error(Exception(errorMsg)))
    }

    override fun returnResult(weather: Weather) {
            Log.d("@@@","SuccesPoster")
            liveData.postValue(PosterInfoAppState.Success(weather))
    }
}