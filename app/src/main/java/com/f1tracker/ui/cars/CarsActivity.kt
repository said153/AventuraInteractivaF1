package com.f1tracker.ui.cars

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.f1tracker.databinding.ActivityCarsBinding
import com.f1tracker.ui.drivers.DriversActivity
import com.f1tracker.utils.AnimationUtils
import com.f1tracker.data.model.Auto
import com.f1tracker.data.model.Escuderia

/**
 * Actividad que muestra el auto de la escudería seleccionada
 * Segundo nivel de navegación jerárquica
 */
class CarsActivity : AppCompatActivity(), CarsFragment.OnAutoSelectedListener {

    private lateinit var binding: ActivityCarsBinding
    private val viewModel: CarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("🏁 DEBUG: CarsActivity.onCreate() iniciado")

        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del Intent
        val escuderiaId = intent.getIntExtra("escuderia_id", -1)
        val escuderiaNombre = intent.getStringExtra("escuderia_nombre") ?: ""

        println("📋 DEBUG: Datos recibidos - ID: $escuderiaId, Nombre: $escuderiaNombre")

        setupActionBar(escuderiaNombre)
        setupFragment(escuderiaId)
        setupAnimations()
    }

    /**
     * Configura la barra de acciones
     */
    private fun setupActionBar(escuderiaNombre: String) {
        supportActionBar?.apply {
            title = escuderiaNombre
            setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Configura el fragment de autos usando interface
     */
    private fun setupFragment(escuderiaId: Int) {
        println("🔧 DEBUG: Configurando fragment con ID: $escuderiaId")

        // Verificar que no hay fragment previo
        val existingFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (existingFragment != null) {
            println("🔄 DEBUG: Removiendo fragment existente")
            supportFragmentManager.beginTransaction()
                .remove(existingFragment)
                .commitNow()
        }

        // Crear nuevo fragment
        val fragment = CarsFragment().apply {
            arguments = Bundle().apply {
                putInt("escuderia_id", escuderiaId)
            }
        }

        // Agregar el fragment
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment, "CarsFragment")
            .commitNow() // commitNow para ejecución inmediata

        println("✅ DEBUG: Fragment añadido al FragmentManager")
    }

    /**
     * Implementación de la interface - se llama cuando se selecciona un auto
     */
    override fun onAutoSelected(auto: Auto, escuderia: Escuderia) {
        println("🚀 DEBUG: CarsActivity.onAutoSelected() llamado")
        println("🚗 DEBUG: Auto: ${auto.modelo}")
        println("🏎️ DEBUG: Escudería: ${escuderia.nombre}")

        val intent = Intent(this, DriversActivity::class.java).apply {
            putExtra("escuderia_id", escuderia.id)
            putExtra("escuderia_nombre", escuderia.nombre)
            putExtra("auto_modelo", auto.modelo)
        }

        println("🎯 DEBUG: Iniciando DriversActivity")
        startActivity(intent)

        // Transición con animación
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }

    /**
     * Configura animaciones de entrada
     */
    private fun setupAnimations() {
        // Animación de deslizamiento desde la derecha
        AnimationUtils.slideInFromRight(binding.root, 500)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Transición de salida personalizada
        overridePendingTransition(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
    }
}