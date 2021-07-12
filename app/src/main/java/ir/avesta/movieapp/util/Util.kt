package ir.avesta.movieapp.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun View.beGone() {
    this.visibility = GONE
}

fun View.beVisible() {
    this.visibility = VISIBLE
}