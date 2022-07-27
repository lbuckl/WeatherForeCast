package com.gb.Weather.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.RepositoryLocalImpl
import com.gb.Weather.model.RepositoryRequestHistory

class HistoryViewModel(private val liveData: MutableLiveData<HistoryAppState> = MutableLiveData<HistoryAppState>())
    :ViewModel() {
        private lateinit var repositoryRequestHistory: RepositoryRequestHistory

    //Выбираем БД и возвращаем данные
    val getLiveData = {
        repositoryRequestHistory = RepositoryRequestHistory()
        liveData
    }

    fun getHistory(){
        liveData.postValue(HistoryAppState.LoadedHistory(repositoryRequestHistory.getHistoryList()))
    }
}