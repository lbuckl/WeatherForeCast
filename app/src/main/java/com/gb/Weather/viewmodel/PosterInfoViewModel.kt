package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.*
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

class PosterInfoViewModel (private val liveData: MutableLiveData<PosterInfoAppState> = MutableLiveData<PosterInfoAppState>()) :
    ViewModel(), CallBackResult, CallBackError {

    private lateinit var repositoryWeather: RepositorySingleCity
    private lateinit var repositoryList: RepositoryListCity
    private lateinit var repositoryFavoriteCity: RepositoryFavoriteCity
    private var errCount: Int = 0


    val getLiveData = {
        repositoryWeather = RepositoryRemoteImpl
        repositoryList = RepositoryLocalImpl()
        repositoryFavoriteCity = RepositoryFavoriteCity()
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
        val city = RepositoryRemoteImpl.getCity()
        liveData.postValue(PosterInfoAppState.Loading(city))
        RepositoryRemoteImpl.getRemoteWeather(city,this,this)
    }

    override fun setError(errorMsg: String) {
                liveData.postValue(PosterInfoAppState.Error(Exception(errorMsg)))
    }

    override fun returnResult(weather: Weather) {
            liveData.postValue(PosterInfoAppState.Success(weather))
    }

    fun setFavoriteCity(){
            repositoryFavoriteCity.addCityToRoom(repositoryWeather.getCity())
    }
}