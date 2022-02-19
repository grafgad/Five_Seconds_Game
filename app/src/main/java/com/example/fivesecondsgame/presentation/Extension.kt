package com.example.fivesecondsgame.presentation

import android.view.View

fun View.visibleIf(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}