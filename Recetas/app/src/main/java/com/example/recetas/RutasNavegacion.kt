package com.example.recetas.vistas

enum class RutasNavegacion(val ruta: String) {
    PANTALLA_PRINCIPAL("pantallaPrincipal"),
    PANTALLA_RESULTADOS("pantallaResultados"),
    PANTALLA_DETALLE("pantallaDetalle/{nombreReceta}"),
    PANTALLA_AGREGAR("pantallaAgregar");

}
