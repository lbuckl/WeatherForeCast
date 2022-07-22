package com.gb.Weather.services

import android.app.IntentService
import android.content.Intent
import android.content.ServiceConnection
import android.os.Handler
import android.os.Looper
import android.telephony.ServiceState
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gb.Weather.BuildConfig
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.shared.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

class WeatherLoaderService: IntentService("LOAD_WEATHER"){

    override fun onHandleIntent(intent: Intent?) {

        intent?.let { itIntent ->
            itIntent.getParcelableExtra<Weather>(BUNDLE_WEATHER_KEY)?.let{
                val lat: Double = it.city.lat
                val lon: Double = it.city.lon
                val handler = Handler(Looper.myLooper()!!)
                val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
                Thread {
                    var myConnection: HttpsURLConnection? = null
                    myConnection = uri.openConnection() as HttpsURLConnection
                    myConnection.apply {
                        readTimeout = 5000
                        //addRequestProperty("YANDEX_API_KEY_NAME", BROKEN_API_KEY)
                        addRequestProperty(YANDEX_API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
                    }
                    try {
                        val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                        val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                        //val weatherDTO: WeatherDTO? = null
                            if (weatherDTO != null) {
                                val weatherData = Weather(it.city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike)
                                LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
                                    putExtra(BUNDLE_WEATHER_KEY,weatherData)
                                    action = WEATHER_LOADED_WAVE
                                    Log.d("@@@@","Load_weather_done")
                                })

                            } else {
                                Toast.makeText(baseContext,"ошибка загрузки данных погоды",LENGTH_LONG)
                            }
                    }catch (e:RuntimeException){
                        e.printStackTrace()
                        Log.d("@@@","RuntimeException")
                    }catch (e: FileNotFoundException){
                        e.printStackTrace()
                        Log.d("@@@","FileNotFoundException")
                    }catch (e: UnknownHostException){
                        e.printStackTrace()
                        Log.d("@@@","UnknownHostException")
                    }finally {
                        myConnection.disconnect()
                    }
                }.start()
            }
        }
    }
}