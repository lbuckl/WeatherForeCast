package com.gb.Weather.model
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.Weather.domain.Weather
import com.gb.Weather.domain.getDefaultCity
import com.gb.Weather.model.requests.WeatherLoader
import com.gb.Weather.model.requests.WeatherLoaderTest
import com.gb.Weather.model.requests.retrofit.WeatherLoaderRetrofitTest
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

object RepositoryRemoteImpl:RepositorySingleCity {
    private var data = Weather(getDefaultCity(),0,0)

    override fun setWeather(weather: Weather) {
        data = weather
    }

    override fun getWeather(): Weather{
        return data
    }

    fun getRemoteWeather(weather: Weather,callBackResult: CallBackResult,callBackError: CallBackError){
        val handler = Handler(Looper.myLooper()!!)
        Thread {
            var errorCount = 0
            var weatherRemote: Weather?

            //ВАЖНО!!! сровнять n в "errorCount < n" с колличеством запросов getWeatgerFromLoader
            while (errorCount < 2){
                weatherRemote = getWeatgerFromLoader(weather,errorCount)
                if (weatherRemote != null) {
                    //handler.post {
                        data = weatherRemote
                        callBackResult.returnResult(weatherRemote)
                    //}
                    break
                }
                else errorCount++
            }
            if (errorCount != 0) callBackError.setError("Error!!!")

        }.start()
    }

    //Вызов резервных способов запроса
    private fun getWeatgerFromLoader(weather: Weather, count: Int):Weather?{
        return when (count) {
            0 -> {
                Log.d("@@@","LoadWeatherRetro")
                WeatherLoaderRetrofitTest().requestWeather(weather)
            }
            1 -> {
                Log.d("@@@","LoadWeatherHTTPS")
                WeatherLoaderTest.requestWeather(weather)
            }
            else -> null
        }
    }
}