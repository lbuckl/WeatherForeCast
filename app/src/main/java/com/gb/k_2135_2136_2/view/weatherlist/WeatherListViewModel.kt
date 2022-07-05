package com.gb.k_2135_2136_2.view.weatherlist

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.k_2135_2136_2.model.Repository
import com.gb.k_2135_2136_2.model.RepositoryLocalImpl
import com.gb.k_2135_2136_2.model.RepositoryRemoteImpl
import com.gb.k_2135_2136_2.viewmodel.AppState
import java.lang.Thread.sleep
import java.util.*

class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel() {

    lateinit var repository: Repository

    //Возвращаем лайвдата
    fun getLiveData():MutableLiveData<AppState>{
        choiceRepository()
        return liveData
    }

    //Выбираем БД для загрузки
    private fun choiceRepository(){
        repository = if(isConnection()){
            RepositoryRemoteImpl()
        }else{
            RepositoryLocalImpl()
        }
    }

    fun sentRequest() {
        liveData.value = AppState.Loading// изменил
        val rand = Random(System.nanoTime())
        val randVal = rand.nextInt(3)
        Log.d("RandVal", "$randVal")
        if(randVal == 1){ //FIXME
            liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))
        }else{
            liveData.postValue(AppState.Success(repository.getWeather(55.755826, 37.617299900000035)))
        }

    }

    private fun isConnection(): Boolean {
        return false
    }

    override fun onCleared() { // TODO HW ***
        super.onCleared()
    }
}
