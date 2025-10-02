package com.f1tracker.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

/**
 * Utilidad para gestionar el tema de la aplicación (claro/oscuro)
 */
object ThemeUtils {

    private const val PREFS_NAME = "theme_preferences"
    private const val KEY_DARK_MODE = "dark_mode_enabled"

    /**
     * Obtiene las SharedPreferences de tema
     */
    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Guarda la preferencia de modo oscuro
     * @param context Contexto de la aplicación
     * @param isDarkMode true para modo oscuro, false para modo claro
     */
    fun setDarkMode(context: Context, isDarkMode: Boolean) {
        getPreferences(context).edit().apply {
            putBoolean(KEY_DARK_MODE, isDarkMode)
            apply()
        }
    }

    /**
     * Obtiene si el modo oscuro está activado
     * @param context Contexto de la aplicación
     * @return true si el modo oscuro está activado, false en caso contrario
     */
    fun isDarkMode(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_DARK_MODE, false)
    }

    /**
     * Aplica el tema según la preferencia guardada
     * Debe llamarse antes de setContentView() en cada Activity
     * @param context Contexto de la aplicación
     */
    fun applyTheme(context: Context) {
        val isDarkMode = isDarkMode(context)
        val nightMode = if (isDarkMode) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    /**
     * Cambia entre modo claro y oscuro
     * @param context Contexto de la aplicación
     * @param isDarkMode true para activar modo oscuro, false para modo claro
     */
    fun toggleTheme(context: Context, isDarkMode: Boolean) {
        setDarkMode(context, isDarkMode)
        applyTheme(context)
    }
}