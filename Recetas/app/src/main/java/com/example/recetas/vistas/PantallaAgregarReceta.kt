package com.example.recetas.vistas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@Composable
fun PantallaAgregarReceta(
    viewModel: RecetasViewModel,
    onVolver: () -> Unit
) {
    var nombreReceta by remember { mutableStateOf("") }
    var ingredienteActual by remember { mutableStateOf("") }
    var preparacion by remember { mutableStateOf("") }
    val ingredientesTemp = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = nombreReceta,
            onValueChange = { nombreReceta = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = ingredienteActual,
                onValueChange = { ingredienteActual = it },
                label = { Text("Ingrediente") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (ingredienteActual.isNotBlank() && !ingredientesTemp.contains(ingredienteActual)) {
                            ingredientesTemp.add(ingredienteActual.trim())
                        }
                        ingredienteActual = ""
                    }
                )
            )

            Button(
                onClick = {
                    if (ingredienteActual.isNotBlank() && !ingredientesTemp.contains(ingredienteActual)) {
                        ingredientesTemp.add(ingredienteActual.trim())
                    }
                    ingredienteActual = ""
                },
                modifier = Modifier.alignByBaseline()
            ) {
                Text("Añadir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(ingredientesTemp.size) { index ->
                Text("- ${ingredientesTemp[index]}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = preparacion,
            onValueChange = { preparacion = it },
            label = { Text("Preparación") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (nombreReceta.isNotBlank() && ingredientesTemp.isNotEmpty() && preparacion.isNotBlank()) {
                    val nuevaReceta = Receta(
                        nombre = nombreReceta.trim(),
                        ingredientes = ingredientesTemp.toList(),
                        preparacion = preparacion.trim()
                    )
                    viewModel.agregarReceta(nuevaReceta)
                    viewModel.limpiarSeleccion()
                    nombreReceta = ""
                    preparacion = ""
                    ingredientesTemp.clear()
                    onVolver()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar receta")
        }
    }
}
