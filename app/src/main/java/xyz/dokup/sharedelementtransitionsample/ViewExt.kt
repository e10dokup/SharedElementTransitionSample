package xyz.dokup.sharedelementtransitionsample

import android.view.View
import android.view.ViewTreeObserver

/**
 * Created by e10dokup on 2018/03/11.
 */
fun View.addOnetimeOnPreDrawListener(listener: () -> Unit) {
    viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            listener.invoke()

            return true
        }
    })
}