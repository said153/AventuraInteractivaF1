package com.f1tracker.ui.cars

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.f1tracker.R
import com.f1tracker.databinding.ActivityCarsBinding
import com.f1tracker.ui.drivers.DriversActivity
import com.f1tracker.utils.AnimationUtils
import com.f1tracker.utils.ThemeUtils
import com.f1tracker.data.model.Auto
import com.f1tracker.data.model.Escuderia

class CarsActivity : AppCompatActivity(), CarsFragment.OnAutoSelectedListener {

    private lateinit var binding: ActivityCarsBinding
    private val viewModel: CarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Aplicar tema ANTES de super.onCreate()
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        println("🚀 DEBUG: CarsActivity.onCreate() iniciado")

        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cambiar fondo según el tema
        updateBackground()

        // Obtener datos del Intent
        val escuderiaId = intent.getIntExtra("escuderia_id", -1)
        val escuderiaNombre = intent.getStringExtra("escuderia_nombre") ?: ""

        println("📋 DEBUG: Datos recibidos - ID: $escuderiaId, Nombre: $escuderiaNombre")

        setupActionBar(escuderiaNombre)
        setupFragment(escuderiaId)
        setupAnimations()
    }

    /**
     * Actualiza el fondo según el tema actual
     */
    private fun updateBackground() {
        val isDarkMode = ThemeUtils.isDarkMode(this)
        val backgroundRes = if (isDarkMode) {
            R.drawable.bg_cars // Fondo oscuro
        } else {
            R.drawable.bg_cars_light // Fondo claro
        }
        binding.root.setBackgroundResource(backgroundRes)
    }

    private fun setupActionBar(escuderiaNombre: String) {
        supportActionBar?.apply {
            title = escuderiaNombre
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupFragment(escuderiaId: Int) {
        println("🔧 DEBUG: Configurando fragment con ID: $escuderiaId")

        val existingFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (existingFragment != null) {
            println("🔄 DEBUG: Removiendo fragment existente")
            supportFragmentManager.beginTransaction()
                .remove(existingFragment)
                .commitNow()
        }

        val fragment = CarsFragment().apply {
            arguments = Bundle().apply {
                putInt("escuderia_id", escuderiaId)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment, "CarsFragment")
            .commitNow()

        println("✅ DEBUG: Fragment añadido al FragmentManager")
    }

    override fun onAutoSelected(auto: Auto, escuderia: Escuderia) {
        println("🚀 DEBUG: CarsActivity.onAutoSelected() llamado")
        println("🚗 DEBUG: Auto: ${auto.modelo}")
        println("🎏 DEBUG: Escudería: ${escuderia.nombre}")

        val intent = Intent(this, DriversActivity::class.java).apply {
            putExtra("escuderia_id", escuderia.id)
            putExtra("escuderia_nombre", escuderia.nombre)
            putExtra("auto_modelo", auto.modelo)
        }

        println("🎯 DEBUG: Iniciando DriversActivity")
        startActivity(intent)

        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }

    private fun setupAnimations() {
        AnimationUtils.slideInFromRight(binding.root, 500)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
    }
}