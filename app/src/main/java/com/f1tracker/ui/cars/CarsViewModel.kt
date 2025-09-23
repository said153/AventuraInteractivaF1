package com.f1tracker.ui.cars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.f1tracker.data.model.Auto
import com.f1tracker.data.model.Escuderia
import com.f1tracker.data.repository.F1Repository

/**
 * ViewModel para la pantalla de autos
 * Maneja los datos del auto y la escudería seleccionada
 */
class CarsViewModel : ViewModel() {

    private val repository = F1Repository()

    private val _auto = MutableLiveData<Auto?>()
    val auto: LiveData<Auto?> = _auto

    private val _escuderia = MutableLiveData<Escuderia?>()
    val escuderia: LiveData<Escuderia?> = _escuderia

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /**
     * Carga los datos del auto y la escudería
     */
    fun loadAutoData(escuderiaId: Int) {
        println("🔍 DEBUG: CarsViewModel.loadAutoData() llamado con ID: $escuderiaId")

        _isLoading.value = true

        // Usar Handler con Looper específico (API moderna)
        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            println("🔍 DEBUG: Cargando datos para escudería ID: $escuderiaId")

            val escuderia = repository.getEscuderiaById(escuderiaId)
            val auto = repository.getAutoByEscuderiaId(escuderiaId)

            println("🏎️ DEBUG: Escudería encontrada: ${escuderia?.nombre}")
            println("🚗 DEBUG: Auto encontrado: ${auto?.modelo}")

            _escuderia.value = escuderia
            _auto.value = auto
            _isLoading.value = false
        }, 300)
    }
}