package com.example.recetas.vistas

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Receta(
    val nombre: String,
    val ingredientes: List<String>,
    val preparacion: String
)

class RecetasViewModel : ViewModel() {

    val listaIngredientes = mutableStateListOf(
        "Tomate", "Cebolla", "Papa", "Arroz", "Pollo",
        "Carne", "Leche", "Huevo", "Queso", "Pan"
    )

    val listaRecetas = mutableStateListOf(
        Receta("Sopa de pollo", listOf("Pollo", "Cebolla", "Papa"), "Cocinar todo junto..."),
        Receta("Arroz con queso", listOf("Arroz", "Queso", "Leche"), "Cocinar el arroz y mezclar con queso...")
    )

    val ingredientesSeleccionados = mutableStateListOf<String>()

    fun toggleIngrediente(ingrediente: String) {
        if (ingredientesSeleccionados.contains(ingrediente)) {
            ingredientesSeleccionados.remove(ingrediente)
        } else {
            ingredientesSeleccionados.add(ingrediente)
        }
    }

    fun filtrarRecetas(): List<Receta> {
        if (ingredientesSeleccionados.isEmpty()) return emptyList()
        return listaRecetas.filter { receta ->
            ingredientesSeleccionados.all { it in receta.ingredientes }
        }
    }

    fun agregarIngrediente(ingrediente: String) {
        if (ingrediente.isNotBlank() && !listaIngredientes.contains(ingrediente)) {
            listaIngredientes.add(ingrediente)
        }
    }

    fun agregarReceta(receta: Receta) {
        listaRecetas.add(receta)
        receta.ingredientes.forEach { agregarIngrediente(it) }
    }

    fun limpiarSeleccion() {
        ingredientesSeleccionados.clear()
    }
}
