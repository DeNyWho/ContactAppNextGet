package com.example.contactappnextget.util

import android.graphics.drawable.PaintDrawable

class RoundedRectDrawable(color: Int, radius: Float) : PaintDrawable(color) {
    init {
        setCornerRadius(radius)
    }
}