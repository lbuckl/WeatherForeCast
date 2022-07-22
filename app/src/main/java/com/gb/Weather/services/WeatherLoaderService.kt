package com.gb.Weather.services

import android.app.IntentService
import android.content.Intent
import android.content.ServiceConnection
import android.telephony.ServiceState
import android.util.Log
import com.gb.Weather.domain.Weather

class WeatherLoaderService: IntentService("LOAD_WEATHER"){
    override fun onHandleIntent(p0: Intent?) {
        Log.d("@@@@","WeatherLoaderService_start")
    }
}