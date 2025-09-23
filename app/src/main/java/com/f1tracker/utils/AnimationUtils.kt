package com.f1tracker.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd

/**
 * Utilidades para animaciones personalizadas en la aplicación
 */
object AnimationUtils {

    /**
     * Animación de fade in para una vista
     */
    fun fadeIn(view: View, duration: Long = 300) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        view.animate()
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }

    /**
     * Animación de fade out para una vista
     */
    fun fadeOut(view: View, duration: Long = 300, onComplete: (() -> Unit)? = null) {
        view.animate()
            .alpha(0f)
            .setDuration(duration)
            .setInterpolator(AccelerateInterpolator())
            .withEndAction {
                view.visibility = View.GONE
                onComplete?.invoke()
            }
            .start()
    }

    /**
     * Animación de zoom in para una vista
     */
    fun zoomIn(view: View, duration: Long = 400) {
        view.scaleX = 0.3f
        view.scaleY = 0.3f
        view.alpha = 0f
        view.visibility = View.VISIBLE

        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }

    /**
     * Animación de slide in desde la derecha
     */
    fun slideInFromRight(view: View, duration: Long = 500) {
        val screenWidth = view.context.resources.displayMetrics.widthPixels
        view.translationX = screenWidth.toFloat()
        view.visibility = View.VISIBLE

        view.animate()
            .translationX(0f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }

    /**
     * Animación de slide out hacia la izquierda
     */
    fun slideOutToLeft(view: View, duration: Long = 500, onComplete: (() -> Unit)? = null) {
        val screenWidth = view.context.resources.displayMetrics.widthPixels

        view.animate()
            .translationX(-screenWidth.toFloat())
            .setDuration(duration)
            .setInterpolator(AccelerateInterpolator())
            .withEndAction {
                view.visibility = View.GONE
                onComplete?.invoke()
            }
            .start()
    }

    /**
     * Animación de pulso para resaltar una vista
     */
    fun pulse(view: View, scaleFactor: Float = 1.1f, duration: Long = 200) {
        val scaleUp = ObjectAnimator.ofFloat(view, "scaleX", 1f, scaleFactor).apply {
            this.duration = duration
        }
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 1f, scaleFactor).apply {
            this.duration = duration
        }

        scaleUp.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                val scaleDown = ObjectAnimator.ofFloat(view, "scaleX", scaleFactor, 1f).apply {
                    this.duration = duration
                }
                val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", scaleFactor, 1f).apply {
                    this.duration = duration
                }
                scaleDown.start()
                scaleDownY.start()
            }
        })

        scaleUp.start()
        scaleUpY.start()
    }

    /**
     * Animación de rotación
     */
    fun rotate360(view: View, duration: Long = 1000) {
        view.animate()
            .rotationBy(360f)
            .setDuration(duration)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }
}