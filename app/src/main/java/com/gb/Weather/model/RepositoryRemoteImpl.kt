package com.gb.Weather.model
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.domain.getDefaultCity
import com.gb.Weather.model.requests.WeatherLoaderTest
import com.gb.Weather.model.requests.retrofit.WeatherLoaderRetrofitTest
import com.gb.Weather.shared.CallBackError
import com.gb.Weather.shared.CallBackResult

object RepositoryRemoteImpl:RepositorySingleCity {
    private var data = Weather(getDefaultCity(),0,0,"bkn_n")
    private var dataCity = getDefaultCity()
    val repositoryRequest = RepositoryRequestHistory()

    override fun setCity(city: City) {
        dataCity = city
    }

    override fun getCity(): City{
        return dataCity
    }

    /**
     * Функиця выполняет запрос погоды и колбэкает резальтат, либо ошибку
     * при неудчном запросе функция предусматривает резервный запрос
     */
    fun getRemoteWeather(city: City,callBackResult: CallBackResult,callBackError: CallBackError){
        val handler = Handler(Looper.myLooper()!!)
        Thread {
            var errorCount = 0
            var weatherRemote: Weather?

            //ВАЖНО!!! сровнять n в "errorCount < n" с колличеством запросов getWeatgerFromLoader
            while (errorCount < 2){
                weatherRemote = getWeatgerFromLoader(city,errorCount)
                if (weatherRemote != null) {
                    //handler.post {
                        data = weatherRemote
                        repositoryRequest.addWeatherToHistory(weatherRemote)
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
    private fun getWeatgerFromLoader(city: City, count: Int):Weather?{
        return when (count) {
            0 -> {
                Log.d("@@@","LoadWeatherRetro")
                WeatherLoaderRetrofitTest().requestWeather(city)
            }
            1 -> {
                Log.d("@@@","LoadWeatherHTTPS")
                WeatherLoaderTest.requestWeather(city)
            }
            else -> null
        }
    }
}