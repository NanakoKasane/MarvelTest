package com.test.marvel.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.marvel.R

@BindingAdapter("loadImageUrl")
fun ImageView.loadImageUrl(imageUrl: String?) {
    Glide.with(this.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(this)
}

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
