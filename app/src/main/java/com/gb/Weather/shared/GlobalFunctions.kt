package com.gb.Weather.shared

import android.view.View
import androidx.room.Entity
import com.gb.Weather.R
import com.gb.Weather.domain.City
import com.gb.Weather.domain.Weather
import com.gb.Weather.model.dto.WeatherDTO
import com.gb.Weather.model.room.WeatherEntity
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.util.stream.Collectors

//функция для отображения снэкбара c ошибкой
fun View.showSnackBarError(
    text: String = rootView.resources.getString(R.string.error),
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, text, length).show()
}

//функция для отображения снэкбара c ошибкой и доп. сообщением
fun View.showSnackBarErrorMsg(
    errorMsg:String,
    text: String = "${rootView.resources.getString(R.string.error)}: $errorMsg",
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, text, length).show()
}

//функция для отображения информационного снэкбара
fun View.showSnackBarInfoMsg(
    infoMsg:String,
    text: String = "${rootView.resources.getString(R.string.info)}: $infoMsg",
    length: Int = Snackbar.LENGTH_LONG
) {
    Snackbar.make(this, text, length).show()
}

//Для HTTPS запроса погоды
fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}

//Для сборки Weather из WeatherDTO
fun buildWeatherFromDTO(city: City, weatherDTO: WeatherDTO):Weather{
    return Weather(city,
        weatherDTO.fact.temp,
        weatherDTO.fact.feelsLike,weatherDTO.fact.icon)
}

//для преборазования Entity в Weather
fun entityToWeather(weatherEntity: WeatherEntity):Weather {
    with(weatherEntity) {
        return Weather(City(city, lat, lon),
            temperature, feelsLike, icon)
    }
}

//для преборазования Entity в Weather
fun weatherToEntity(weather: Weather):WeatherEntity {
    with(weather) {
        return WeatherEntity(
            0, city.name, city.lat, city.lon,
            temperature, feelsLike, icon!!
        )
    }
}

//для преобразования EntityList в WeatherList
fun entityListToWeatherList(entityList:List<WeatherEntity>):List<Weather>{

        return entityList.map {Weather(City(it.city,it.lat,it.lon)
            ,it.temperature,it.feelsLike,it.icon)  }
}






