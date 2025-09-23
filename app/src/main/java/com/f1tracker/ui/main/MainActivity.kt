package com.f1tracker.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.f1tracker.databinding.ActivityMainBinding
import com.f1tracker.ui.cars.CarsActivity
import com.f1tracker.utils.AnimationUtils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupFragment()
        setupAnimations()
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

    /**
     * Configura las animaciones iniciales
     */
    private fun setupAnimations() {
        // Animación de entrada del contenedor principal
        AnimationUtils.fadeIn(binding.root, 600)
    }
}