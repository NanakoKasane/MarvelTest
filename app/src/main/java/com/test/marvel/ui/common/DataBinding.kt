package com.test.marvel.ui.common

import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("setLink")
fun TextView.setLink(concreteLink : String?, wordToSee : String ){
    isClickable = true
    movementMethod = LinkMovementMethod.getInstance()
    val link = "<a href='${concreteLink}'>${wordToSee}</a>"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        text = Html.fromHtml(link,  Html.FROM_HTML_MODE_COMPACT)
    } else
        text = Html.fromHtml(link)
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
