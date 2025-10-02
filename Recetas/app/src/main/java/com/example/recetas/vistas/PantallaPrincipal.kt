package com.example.recetas.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal(
    listaIngredientes: List<String>,
    onClickIngrediente: (String) -> Unit,
    onClickBuscar: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaIngredientes) { ingrediente ->
                Button(
                    onClick = { onClickIngrediente(ingrediente) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = ingrediente)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = onClickBuscar) {
                Text(text = "Buscar receta")
            }
        }
    }
}
