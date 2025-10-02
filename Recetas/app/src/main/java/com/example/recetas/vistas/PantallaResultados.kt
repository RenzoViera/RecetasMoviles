package com.example.recetas.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaResultados(
    viewModel: RecetasViewModel,
    onClickReceta: (String) -> Unit,
    onClickAgregar: () -> Unit,
    onVolver: () -> Unit
) {
    val recetasFiltradas = viewModel.filtrarRecetas().map { it.nombre }

    Column(modifier = Modifier.fillMaxSize()) {
        if (recetasFiltradas.isEmpty()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "No hay una receta disponible, ¿desea agregarla?")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onClickAgregar) {
                    Text(text = "Agregar receta")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recetasFiltradas) { receta ->
                    Button(
                        onClick = { onClickReceta(receta) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = receta)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                viewModel.limpiarSeleccion()
                onVolver()
            }) {
                Text(text = "Volver")
            }
        }
    }
}
