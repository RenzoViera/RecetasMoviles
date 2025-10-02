package com.example.recetas.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PantallaDetalleReceta(
    nombreReceta: String,
    listaIngredientes: List<String>,
    preparacion: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(text = nombreReceta, fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Ingredientes
        Text(text = "Ingredientes:", fontSize = 18.sp)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(listaIngredientes) { ingrediente ->
                Text(text = "- $ingrediente")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preparación
        Text(text = "Preparación:", fontSize = 18.sp)
        Text(text = preparacion, modifier = Modifier.padding(top = 8.dp))
    }
}
