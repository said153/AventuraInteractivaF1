package com.f1tracker.ui.drivers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.f1tracker.data.model.Escuderia
import com.f1tracker.data.model.Piloto
import com.f1tracker.data.repository.F1Repository

/**
 * ViewModel para la pantalla de pilotos
 * Maneja los datos de los pilotos y la escudería
 */
class DriversViewModel : ViewModel() {

    private val repository = F1Repository()

    private val _pilotos = MutableLiveData<List<Piloto>>()
    val pilotos: LiveData<List<Piloto>> = _pilotos

    private val _escuderia = MutableLiveData<Escuderia?>()
    val escuderia: LiveData<Escuderia?> = _escuderia

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedPiloto = MutableLiveData<Piloto?>()
    val selectedPiloto: LiveData<Piloto?> = _selectedPiloto

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    /**
     * Carga los pilotos de una escudería específica
     */
    fun loadPilotos(escuderiaId: Int) {
        println("🔍 DEBUG: DriversViewModel.loadPilotos() llamado con ID: $escuderiaId")

        if (escuderiaId <= 0) {
            println("❌ DEBUG: ID de escudería inválido: $escuderiaId")
            _error.value = "ID de escudería inválido"
            return
        }

        _isLoading.value = true
        _error.value = null

        try {
            // Simular carga asíncrona
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                println("🔍 DEBUG: Cargando datos para escudería ID: $escuderiaId")

                // Cargar escudería
                val escuderia = repository.getEscuderiaById(escuderiaId)
                println("🏎️ DEBUG: Escudería encontrada: ${escuderia?.nombre}")

                if (escuderia == null) {
                    println("❌ DEBUG: No se encontró escudería con ID: $escuderiaId")
                    _error.value = "No se encontró la escudería"
                    _isLoading.value = false
                    return@postDelayed
                }

                _escuderia.value = escuderia

                // Cargar pilotos
                val pilotos = repository.getPilotosByEscuderiaId(escuderiaId)
                println("👥 DEBUG: Pilotos encontrados: ${pilotos.size}")

                if (pilotos.isEmpty()) {
                    println("⚠️ DEBUG: No se encontraron pilotos para escudería ID: $escuderiaId")
                    // Verificar todas las escuderías disponibles
                    val todasEscuderias = repository.getEscuderias()
                    println("🔍 DEBUG: Escuderías disponibles: ${todasEscuderias.map { "${it.id}:${it.nombre}" }}")

                    // Verificar todos los pilotos disponibles
                    val todosPilotos = repository.getPilotos()
                    println("🔍 DEBUG: Todos los pilotos: ${todosPilotos.map { "${it.escuderiaId}:${it.nombre}" }}")
                } else {
                    pilotos.forEach { piloto ->
                        println("   👤 DEBUG: ${piloto.nombre} (#${piloto.numero}) - EscuderíaID: ${piloto.escuderiaId}")
                    }
                }

                _pilotos.value = pilotos
                _isLoading.value = false

            }, 400)
        } catch (e: Exception) {
            println("❌ DEBUG: Error cargando pilotos: ${e.message}")
            _error.value = "Error cargando datos: ${e.message}"
            _isLoading.value = false
        }
    }

    /**
     * Selecciona un piloto específico
     */
    fun selectPiloto(piloto: Piloto) {
        println("👤 DEBUG: Piloto seleccionado: ${piloto.nombre}")
        _selectedPiloto.value = piloto
    }

    /**
     * Limpia el error
     */
    fun clearError() {
        _error.value = null
    }
}