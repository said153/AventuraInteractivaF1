package com.f1tracker.ui.drivers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.f1tracker.R
import com.f1tracker.databinding.ActivityDriversBinding
import com.f1tracker.utils.AnimationUtils
import com.f1tracker.utils.ThemeUtils
import com.f1tracker.utils.ColorUtils

/**
 * Actividad que muestra los pilotos de la escudería seleccionada
 * Tercer y último nivel de navegación jerárquica
 */
class DriversActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriversBinding
    private val viewModel: DriversViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // IMPORTANTE: Aplicar tema ANTES de super.onCreate()
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        binding = ActivityDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del Intent
        val escuderiaId = intent.getIntExtra("escuderia_id", -1)
        val escuderiaNombre = intent.getStringExtra("escuderia_nombre") ?: ""
        val autoModelo = intent.getStringExtra("auto_modelo") ?: ""

        // IMPORTANTE: Aplicar fondo ANTES de configurar todo lo demás
        updateBackground()

        setupActionBar(escuderiaNombre, autoModelo)
        setupFragment(escuderiaId)
        updateAllTextColors()
        setupAnimations()
    }

    /**
     * Actualiza el fondo según el tema actual - usando colores sólidos
     */
    private fun updateBackground() {
        val isDarkMode = ThemeUtils.isDarkMode(this)

        if (isDarkMode) {
            // Modo oscuro - color #253342
            binding.rootLayout.setBackgroundColor(
                ContextCompat.getColor(this, R.color.drivers_bg_start)
            )
        } else {
            // Modo claro - color #E8F4F8
            binding.rootLayout.setBackgroundColor(
                ContextCompat.getColor(this, R.color.drivers_bg_start_light)
            )
        }

        println("🎨 Fondo DriversActivity: ${if (isDarkMode) "OSCURO (#253342)" else "CLARO (#E8F4F8)"}")
    }

    /**
     * Actualiza todos los colores de texto
     */
    private fun updateAllTextColors() {
        ColorUtils.updateAllTextViews(binding.rootLayout, this)
        println("📝 Colores de texto actualizados")
    }

    /**
     * Configura la barra de acciones
     */
    private fun setupActionBar(escuderiaNombre: String, autoModelo: String) {
        supportActionBar?.apply {
            title = "$escuderiaNombre - $autoModelo"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Configura el fragment de pilotos
     */
    private fun setupFragment(escuderiaId: Int) {
        val fragment = DriversFragment().apply {
            arguments = Bundle().apply {
                putInt("escuderia_id", escuderiaId)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }

    /**
     * Configura animaciones de entrada
     */
    private fun setupAnimations() {
        AnimationUtils.zoomIn(binding.rootLayout, 600)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
}