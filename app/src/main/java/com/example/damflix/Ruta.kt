package com.example.damflix

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

// Rutas de la BottomBar centralizadas en una sealed class
sealed class Ruta(val path: String, val label: String, val icon: ImageVector) {
    object Inicio : Ruta(
        path = "inicio",
        label = "Inicio",
        icon = Icons.Filled.Home
    )

    object Listado : Ruta(
        path = "listado",
        label = "Listado",
        icon = Icons.Filled.List
    )

    object Formulario : Ruta(
        path = "alta",
        label = "Añadir",
        icon = Icons.Filled.Add
    )
}