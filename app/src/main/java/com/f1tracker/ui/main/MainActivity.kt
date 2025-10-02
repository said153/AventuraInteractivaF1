package com.f1tracker.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.f1tracker.R
import com.f1tracker.databinding.ActivityMainBinding
import com.f1tracker.ui.cars.CarsActivity
import com.f1tracker.utils.AnimationUtils
import com.f1tracker.utils.ThemeUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeUtils.applyTheme(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupThemeSwitch()
        setupFragment()
        setupAnimations()
        updateBackground()
        updateTextColors() // <-- Añadir esta línea
    }

    /**
     * Configura el Switch de tema con iconos de sol/luna
     */
    private fun setupThemeSwitch() {
        val isDarkMode = ThemeUtils.isDarkMode(this)

        // Sincronizar el estado del switch con la preferencia guardada
        binding.themeSwitch.isChecked = isDarkMode

        // Actualizar icono según el estado actual
        updateThemeIcon(isDarkMode)

        // Listener para cambios en el switch
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar la nueva preferencia
            ThemeUtils.setDarkMode(this, isChecked)

            // Recrear la activity con animación suave
            recreate()

            // Aplicar transición de fade
            overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }
    }

    /**
     * Actualiza el icono del tema: Sol para modo claro, Luna para modo oscuro
     */
    private fun updateThemeIcon(isDarkMode: Boolean) {
        val iconRes = if (isDarkMode) {
            R.drawable.ic_moon // Luna cuando está en modo oscuro
        } else {
            R.drawable.ic_sun // Sol cuando está en modo claro
        }
        binding.themeIcon.setImageResource(iconRes)
    }

    /**
     * Actualiza el fondo según el tema actual
     */
    private fun updateBackground() {
        val isDarkMode = ThemeUtils.isDarkMode(this)
        val backgroundRes = if (isDarkMode) {
            R.drawable.bg_main // Fondo oscuro
        } else {
            R.drawable.bg_main_light // Fondo claro
        }
        binding.root.setBackgroundResource(backgroundRes)
    }

    private fun setupFragment() {
        val fragment = MainFragment()

        // Configurar listener para navegación a CarsActivity
        fragment.onEscuderiaSelected = { escuderia ->
            val intent = Intent(this, CarsActivity::class.java).apply {
                putExtra("escuderia_id", escuderia.id)
                putExtra("escuderia_nombre", escuderia.nombre)
            }
            startActivity(intent)
            // Aplicar transición personalizada
            overridePendingTransition(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    private fun updateTextColors() {
        val isDarkMode = ThemeUtils.isDarkMode(this)
        val textColor = if (isDarkMode) {
            resources.getColor(R.color.f1_white, null)
        } else {
            resources.getColor(R.color.f1_black, null)
        }

        // Aplicar color al título
        binding.textViewMainTitle.setTextColor(textColor)
    }

    /**
     * Configura las animaciones iniciales
     */
    private fun setupAnimations() {
        // Animación de entrada del contenedor principal
        AnimationUtils.fadeIn(binding.root, 600)
    }
}