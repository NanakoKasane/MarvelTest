package com.test.marvel.ui.common

import androidx.databinding.ObservableBoolean

interface HasLoading {
    val loading: ObservableBoolean

    fun showLoading() {
        loading.set(true)
    }

    fun hideLoading() {
        loading.set(false)
    }
}

class HasLoadingImpl : HasLoading {
    override val loading: ObservableBoolean = ObservableBoolean(false)

}