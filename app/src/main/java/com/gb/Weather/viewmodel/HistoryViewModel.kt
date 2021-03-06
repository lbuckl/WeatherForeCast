package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.model.RepositoryRequestHistory

class HistoryViewModel(private val liveData: MutableLiveData<HistoryAppState> = MutableLiveData<HistoryAppState>())
    :ViewModel() {
     private lateinit var repositoryRequestHistory: RepositoryRequestHistory

    //Определяет прямую или обратную сортировку (по умолчанию прямая)
    private var isRight: Boolean = true

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryRequestHistory = RepositoryRequestHistory()
        liveData
    }

    fun clearHistory(){
        repositoryRequestHistory.clearHistory()
        Thread.sleep(200)
        liveData.postValue(HistoryAppState.LoadedHistory(repositoryRequestHistory.getHistoryList()))
    }

    fun getHistory(){
        Thread{
            liveData.postValue(HistoryAppState.LoadedHistory(repositoryRequestHistory.getHistoryList()))
        }.start()
    }

    fun getSortedHistory(){
        Thread{
            liveData.postValue(HistoryAppState.LoadedHistory(repositoryRequestHistory.getSortedHistory(isRight)))
            isRight = !isRight
        }.start()
    }


}