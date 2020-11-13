package com.craft.furniture.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.craft.furniture.R
import com.google.android.material.snackbar.Snackbar

/**
 * Load Image in [ImageView]
 */
fun ImageView.loadUrl(url: String) {
    Glide.with(context.applicationContext).load(url).into(this)
}

/**
 * show [Toast]
 * @param message
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * @param id Id of String resource
 * @return String value of given resource id
 * Get String from resource
 */
fun Context.getResString(@StringRes id: Int): String = resources.getString(id)

/**
 * show [Snackbar]
 * @param message
 */
fun View.showSnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
        setTextColor(Color.WHITE)
        setBackgroundTint(ContextCompat.getColor(this.context, R.color.snack_bar_color))
        show()
    }
}

