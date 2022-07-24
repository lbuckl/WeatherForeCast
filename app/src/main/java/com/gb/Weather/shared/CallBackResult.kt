package com.gb.Weather.shared
import com.gb.Weather.domain.Weather

interface CallBackResult {
    fun returnResult(weather: Weather)
}