package com.f1tracker.data.model

/**
 * Modelo de datos para representar un piloto de Fórmula 1
 * @param *id Identificador único del piloto
 * @param *escuderiaId ID de la escudería para la que corre
 * @param *nombre Nombre completo del piloto
 * @param *numero Número del piloto
 * @param *nacionalidad Nacionalidad del piloto
 * @param *imagenResId ID del recurso drawable de la foto del piloto
 * @param *edad Edad del piloto
 */
data class Piloto(
    val id: Int,
    val escuderiaId: Int,
    val nombre: String,
    val numero: Int,
    val nacionalidad: String,
    val imagenResId: Int,
    val edad: Int
)