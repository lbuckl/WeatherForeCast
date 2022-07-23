package com.gb.Weather.shared

import android.view.View
import com.gb.Weather.R
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






