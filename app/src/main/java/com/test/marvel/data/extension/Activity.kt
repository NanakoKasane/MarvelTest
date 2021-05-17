package com.test.marvel.data.extension

import android.Manifest
import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.BasePermissionListener

fun Activity.checkLocationPermission(error: (() -> Unit)? = null, success: () -> Unit) {
    Dexter.withActivity(this)
        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        .withListener(object : BasePermissionListener() {
            override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                success.invoke()
            }

            override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                error?.invoke()
            }

        })
        .onSameThread()
        .check()
}