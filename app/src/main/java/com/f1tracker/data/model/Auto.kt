package com.f1tracker.data.model

/**
 * Modelo de datos para representar un auto de Fórmula 1
 * @param *id Identificador único del auto
 * @param *escuderiaId ID de la escudería a la que pertenece
 * @param *modelo Nombre del modelo del auto
 * @param *imagenResId ID del recurso drawable de la imagen del auto
 * @param *motor Tipo de motor
 * @param *year Año del auto
 */
data class Auto(
    val id: Int,
    val escuderiaId: Int,
    val modelo: String,
    val imagenResId: Int,
    val motor: String,
    val year: Int = 2024
)