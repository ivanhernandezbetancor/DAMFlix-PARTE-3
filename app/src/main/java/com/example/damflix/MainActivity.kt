package com.example.damflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaCatalogoDAMFlix() // actividad 4
            }
        }
    }
}

// actividad 4
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogoDAMFlix() {
    val navController = rememberNavController()

    val peliculas = remember {
        listOf(
            Pelicula(1, "El Padrino"),
            Pelicula(2, "Pulp Fiction"),
            Pelicula(3, "Matrix"),
            Pelicula(4, "Interstellar"),
            Pelicula(5, "Gladiator"),
            Pelicula(6, "Origen"),
            Pelicula(7, "Forrest Gump"),
            Pelicula(8, "Titanic"),
            Pelicula(9, "El Caballero Oscuro"),
            Pelicula(10, "El Señor de los Anillos"),
            Pelicula(11, "Parásitos"),
            Pelicula(12, "Whiplash"),
            Pelicula(13, "El Resplandor"),
            Pelicula(14, "Alien"),
            Pelicula(15, "Toy Story")
        )
    }

    // Observamos la ruta actual del BackStack
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    // El título de la barra superior cambia según la pantalla
    val titulo = when (rutaActual) {
        Ruta.Listado.path -> "Catálogo"
        Ruta.Formulario.path -> "Formulario"
        else -> "Información de la Aplicación"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(titulo) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                // Definimos una lista inmutable con las rutas
                listOf(
                    Ruta.Inicio,
                    Ruta.Listado,
                    Ruta.Formulario
                ).forEach { ruta ->
                    NavigationBarItem(
                        selected = rutaActual == ruta.path,
                        onClick = {
                            if (rutaActual != ruta.path) {
                                navController.navigate(ruta.path) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = { Icon(ruta.icon, contentDescription = ruta.label) },
                        label = { Text(ruta.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Es obligatorio aplicar innerPadding al NavHost para no superponer vistas
        NavHost(
            navController = navController,
            startDestination = Ruta.Inicio.path,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Ruta.Inicio.path) {
                PantallaInicio()
            }
            composable(Ruta.Listado.path) {
                ListaPeliculas(
                    peliculas = peliculas,
                    onToggleFavorito = { id ->
                        val pelicula = peliculas.first { it.id == id }
                        pelicula.esFavorita = !pelicula.esFavorita
                    }
                )
            }
            composable(Ruta.Formulario.path) {
                FormularioPelicula()
            }
        }
    }
}

// Uso el Preview para verlo mientras modifico el código
@Preview(showBackground = true)
@Composable
fun PantallaCatalogoPreview() {
    MaterialTheme {
        PantallaCatalogoDAMFlix()
    }
}