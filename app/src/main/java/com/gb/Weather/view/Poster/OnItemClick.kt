package com.gb.Weather.view.Poster

import com.gb.Weather.domain.Weather

interface OnItemClick {
    fun onItemClick(weather: Weather)
}