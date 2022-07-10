package com.gb.Weather.view.weatherlist
import com.gb.Weather.domain.Weather


fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}