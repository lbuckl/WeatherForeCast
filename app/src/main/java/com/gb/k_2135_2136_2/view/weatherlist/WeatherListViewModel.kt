package com.gb.k_2135_2136_2.view.weatherlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gb.k_2135_2136_2.model.Repository
import com.gb.k_2135_2136_2.model.RepositoryLocalImpl
import com.gb.k_2135_2136_2.model.RepositoryRemoteImpl
import com.gb.k_2135_2136_2.viewmodel.AppState
import java.util.*

/**
 * Класс для реализации LiveData
 * Тригерит состояния AppState
 */
class WeatherListViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>()) :
    ViewModel() {

    lateinit var repository: Repository

    //Выбираем БД и возвращаем данные
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

    //Непосредственно действия
    fun sentRequest() {
        //Имитируем загрузку
        liveData.value = AppState.Loading
        //Рандомим результат загрузки
        val rand = Random(System.nanoTime())
        val randVal = rand.nextInt(3)
        Log.d("RandVal", "$randVal")
        if(randVal == 1){
            liveData.postValue(AppState.Error(IllegalStateException("Что-то пошло не так")))
        }else{
            liveData.postValue(AppState.Success(repository.getWeather(55.755826, 37.617299900000035)))
        }
    }

    //Имитация результата состояния сединения с БД
    private fun isConnection(): Boolean {
        return false
    }

    //Отменяет подписку. Выполняется после onDestroy во фрагменте
    override fun onCleared() { // TODO HW ***
        super.onCleared()
        Log.d("TEST", "onCleared ViewModel!!!")
        liveData.removeObservers(WeatherListFragment.newInstance().viewLifecycleOwner)
    }

}
