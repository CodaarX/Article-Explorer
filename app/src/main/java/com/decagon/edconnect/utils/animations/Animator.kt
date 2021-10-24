package com.decagon.edconnect.utils.animations

import android.app.Activity
import android.content.Context
import com.decagon.edconnect.R
import kotlinx.coroutines.CoroutineScope

object Animator {

    fun animateActivityZoom(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.animate_zoom_enter,
            R.anim.animate_zoom_exit
        )
    }

    fun animateActivityFadeContext(context: Context) {
        (context as Activity).overridePendingTransition(
            R.anim.animate_fade_enter,
            R.anim.animate_fade_exit
        )
    }


}