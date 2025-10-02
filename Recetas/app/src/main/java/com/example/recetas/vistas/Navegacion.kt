package com.example.recetas.vistas

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navegacion(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel: RecetasViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RutasNavegacion.PANTALLA_PRINCIPAL.ruta,
        modifier = modifier
    ) {
        composable(RutasNavegacion.PANTALLA_PRINCIPAL.ruta) {
            PantallaPrincipal(
                listaIngredientes = viewModel.listaIngredientes,
                onClickIngrediente = { viewModel.toggleIngrediente(it) },
                onClickBuscar = {
                    navController.navigate(RutasNavegacion.PANTALLA_RESULTADOS.ruta)
                }
            )
        }

        composable(RutasNavegacion.PANTALLA_RESULTADOS.ruta) {
            PantallaResultados(
                viewModel = viewModel,
                onClickReceta = { nombre ->
                    navController.navigate("pantallaDetalle/$nombre")
                },
                onClickAgregar = {
                    navController.navigate(RutasNavegacion.PANTALLA_AGREGAR.ruta)
                },
                onVolver = {
                    viewModel.limpiarSeleccion()
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "pantallaDetalle/{nombreReceta}",
            arguments = listOf(navArgument("nombreReceta") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreReceta") ?: "Receta"
            val receta = viewModel.listaRecetas.find { it.nombre == nombre }
            PantallaDetalleReceta(
                nombreReceta = receta?.nombre ?: nombre,
                listaIngredientes = receta?.ingredientes ?: emptyList(),
                preparacion = receta?.preparacion ?: "Aquí iría la preparación de la receta..."
            )
        }

        composable(RutasNavegacion.PANTALLA_AGREGAR.ruta) {
            PantallaAgregarReceta(
                viewModel = viewModel,
                onVolver = { navController.popBackStack(RutasNavegacion.PANTALLA_PRINCIPAL.ruta, false) }
            )
        }
    }
}
