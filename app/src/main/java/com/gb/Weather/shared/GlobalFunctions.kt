package com.gb.Weather.shared

import android.content.res.Resources
import android.view.View
import com.gb.Weather.R
import com.google.android.material.snackbar.Snackbar

//функция для отображения снэкбара c ошибкой
fun View.showSnackBarError(
    text: String = rootView.resources.getString(R.string.error),
    length: Int = Snackbar.LENGTH_LONG
) {

    Snackbar.make(this, text, length).show()
}
