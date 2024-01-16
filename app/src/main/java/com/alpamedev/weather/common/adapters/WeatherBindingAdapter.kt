package com.alpamedev.weather.common.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("bindIsGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("app:bindMaterialTextViewStyle")
fun seMaterialTextViewStyle(view: MaterialTextView, styleName: String?) {
    if (!styleName.isNullOrEmpty()) {
        val styleResId = view.resources.getIdentifier(styleName, "style", view.context.packageName)
        if (styleResId != 0) {
            view.setTextAppearance(styleResId)
        }
    }
}