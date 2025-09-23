package com.f1tracker.ui.drivers

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.f1tracker.databinding.ActivityDriversBinding
import com.f1tracker.utils.AnimationUtils

/**
 * Actividad que muestra los pilotos de la escudería seleccionada
 * Tercer y último nivel de navegación jerárquica
 */
class DriversActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDriversBinding
    private val viewModel: DriversViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDriversBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del Intent
        val escuderiaId = intent.getIntExtra("escuderia_id", -1)
        val escuderiaNombre = intent.getStringExtra("escuderia_nombre") ?: ""
        val autoModelo = intent.getStringExtra("auto_modelo") ?: ""

        setupActionBar(escuderiaNombre, autoModelo)
        setupFragment(escuderiaId)
        setupAnimations()
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
        // Animación de zoom in desde el centro
        AnimationUtils.zoomIn(binding.root, 600)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Transición de salida con fade
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
}