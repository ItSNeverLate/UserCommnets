package com.getyourguide.assignment.presentation.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import java.util.*

object CommonUtils {

    private fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(200), random.nextInt(200), random.nextInt(200))
    }

    fun getRandomColorCircularDrawable():Drawable  {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.OVAL
        drawable.setColor(getRandomColor())
        return drawable
    }

}
