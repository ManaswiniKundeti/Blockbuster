package com.bb.blockbuster.extensions

import android.view.View

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun String.buildImageUri(): String {
    return "https://image.tmdb.org/t/p/w500${this}"
}
