package com.f1tracker.data.model

/**
 * Modelo de datos para representar una escudería de Fórmula 1
 * @param *id Identificador único de la escudería
 * @param *nombre Nombre de la escudería
 * @param *logoResId ID del recurso drawable del logo
 * @param *colorPrimario Color principal de la escudería en formato hex
 * @param *pais País de origen de la escudería
 */
data class Escuderia(
    val id: Int,
    val nombre: String,
    val logoResId: Int,
    val colorPrimario: String,
    val pais: String
)