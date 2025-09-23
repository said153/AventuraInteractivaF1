package com.f1tracker.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.f1tracker.data.model.Escuderia
import com.f1tracker.data.repository.F1Repository


class MainViewModel : ViewModel() {

    private val repository = F1Repository()

    private val _escuderias = MutableLiveData<List<Escuderia>>()
    val escuderias: LiveData<List<Escuderia>> = _escuderias

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadEscuderias() {
        _isLoading.value = true

        // Simular una pequeña carga asíncrona para mostrar animaciones
        android.os.Handler().postDelayed({
            _escuderias.value = repository.getEscuderias()
            _isLoading.value = false
        }, 500)
    }

    fun getEscuderiaById(id: Int): Escuderia? {
        return repository.getEscuderiaById(id)
    }
}