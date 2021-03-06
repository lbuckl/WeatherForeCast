package com.gb.Weather.examples

import android.app.IntentService
import android.content.Intent

class WeatherLoaderService: IntentService("LOAD_WEATHER"){
    /**
     * onHandleIntent принимает на вход интент с данными типа Weather
     * достаёт оттуда координаты города для запроса погоды
     * выполняет запрос в яндекс погоду и:
     * -при удачном ответе создаёт новый объект с акруальной погодой,
     * создаёт рессивер и помещает в него сообщение с данными по погоде.
     * -при неудаче выводит тост с ошибкой
     */
    override fun onHandleIntent(intent: Intent?) {
        /*intent?.let { itIntent ->
            itIntent.getParcelableExtra<Weather>(BUNDLE_WEATHER_KEY)?.let{
                val lat: Double = it.city.lat
                val lon: Double = it.city.lon
                val uri = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
                Thread {
                    Thread.sleep(5000)
                    var myConnection: HttpsURLConnection? = null
                    myConnection = uri.openConnection() as HttpsURLConnection
                    myConnection.apply {
                        readTimeout = 5000
                        addRequestProperty(YANDEX_API_KEY_NAME, BuildConfig.WEATHER_API_KEY)
                    }
                    try {
                        val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                        val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                            if (weatherDTO != null) {
                                val weatherData = Weather(it.city,weatherDTO.fact.temp,weatherDTO.fact.feelsLike)
                                LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
                                    putExtra(BUNDLE_WEATHER_KEY,weatherData)
                                    action = WEATHER_LOADED_WAVE
                                    Log.d("@@@@","Load_weather_done")
                                })

                            } else {
                                Toast.makeText(applicationContext,"ошибка загрузки данных погоды",LENGTH_LONG)
                                Log.d("@@@@","Load_weather_false")
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
        }*/
    }
}

/*Пример активации в акивити или фрагменте
requireActivity().startService(
    Intent(requireContext(),
        WeatherLoaderService::class.java).apply {
        putExtra(BUNDLE_WEATHER_KEY, appState.weather)
        })
 */