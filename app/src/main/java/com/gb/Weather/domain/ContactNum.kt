package com.gb.Weather.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactNum(
    val name:String,
    val num:String): Parcelable