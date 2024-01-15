package com.alpamedev.weather.common.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("bindIsGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}