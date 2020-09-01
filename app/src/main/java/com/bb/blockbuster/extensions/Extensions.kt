package com.bb.blockbuster.extensions

import android.view.View

/**
 * Extension function to show a view
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Extension function to hide a view
 */
fun View.hide() {
    visibility = View.GONE
}

/**
 * Helper extension method to build image url
 */
fun String.buildImageUri(): String {
    return "https://image.tmdb.org/t/p/w500${this}"
}
