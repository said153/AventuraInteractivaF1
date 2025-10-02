package com.f1tracker.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.f1tracker.R

/**
 * Utilidad para gestionar colores de texto según el tema
 */
object ColorUtils {

    /**
     * Obtiene el color de texto apropiado según el tema actual
     */
    fun getTextColor(context: Context): Int {
        val isDarkMode = ThemeUtils.isDarkMode(context)
        return if (isDarkMode) {
            ContextCompat.getColor(context, R.color.f1_white)
        } else {
            ContextCompat.getColor(context, R.color.f1_black)
        }
    }

    /**
     * Obtiene el color de texto secundario apropiado según el tema actual
     */
    fun getSecondaryTextColor(context: Context): Int {
        val isDarkMode = ThemeUtils.isDarkMode(context)
        return if (isDarkMode) {
            ContextCompat.getColor(context, R.color.f1_silver)
        } else {
            ContextCompat.getColor(context, R.color.f1_dark_gray)
        }
    }

    /**
     * Actualiza recursivamente todos los TextViews dentro de un ViewGroup
     * Útil para actualizar todos los textos de una Activity de golpe
     */
    fun updateAllTextViews(view: View, context: Context) {
        val textColor = getTextColor(context)

        when (view) {
            is TextView -> {
                // Solo actualizar si el color actual es blanco o negro
                // para no afectar textos con colores especiales (rojos, etc.)
                val currentColor = view.currentTextColor
                val whiteColor = ContextCompat.getColor(context, R.color.f1_white)
                val blackColor = ContextCompat.getColor(context, R.color.f1_black)
                val androidWhite = ContextCompat.getColor(context, android.R.color.white)
                val androidBlack = ContextCompat.getColor(context, android.R.color.black)

                if (currentColor == whiteColor ||
                    currentColor == blackColor ||
                    currentColor == androidWhite ||
                    currentColor == androidBlack) {
                    view.setTextColor(textColor)
                }
            }
            is ViewGroup -> {
                // Recursivamente actualizar todos los hijos
                for (i in 0 until view.childCount) {
                    updateAllTextViews(view.getChildAt(i), context)
                }
            }
        }
    }
}