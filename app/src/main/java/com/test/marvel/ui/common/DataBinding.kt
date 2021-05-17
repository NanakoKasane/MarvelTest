package com.test.marvel.ui.common

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField

@BindingAdapter("visibleOrGone")
fun View.visibleOrGone(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("visibleOrInvisible")
fun View.visibleOrInvisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("enableButton")
fun View.enableButton(enable: Boolean) {
    this.isEnabled = enable
    this.alpha = if (enable) 1f else 0.7f
}

@BindingAdapter(value = ["bind:userId", "bind:pin"], requireAll = true)
fun View.enableButtonLogin(userIdObservable: ObservableField<String>, pinObservable: ObservableField<String>) {
    val enable = !userIdObservable.get().isNullOrEmpty() && !pinObservable.get().isNullOrEmpty()
    this.isEnabled = enable
    this.alpha = if (enable) 1f else 0.7f
}