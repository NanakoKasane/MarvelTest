package com.test.marvel.data.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.test.marvel.App

fun Context.hideKeyboard(view: View) {
    val imm =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
}

fun Context.getColorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)

val Context.app: App
    get() = applicationContext as App
