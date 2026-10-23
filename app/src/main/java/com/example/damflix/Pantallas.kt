package com.example.damflix

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

// actividad 4: página de información de la aplicación
@Composable
fun PantallaInicio() {
    PerfilUsuario() // actividad 1
}

// actividad 2
@Composable
fun FormularioPelicula() {
    var titulo by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var esVista by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text(text = stringResource(id = R.string.titulo_pelicula)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = director,
            onValueChange = { director = it },
            label = { Text(text = stringResource(id = R.string.director_pelicula)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text(text = stringResource(id = R.string.anio_estreno)) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = esVista, onCheckedChange = { esVista = it })
            Text(text = stringResource(id = R.string.pelicula_vista))
        }
        Button(
            onClick = { },
            enabled = titulo.isNotBlank() && director.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.guardar_pelicula))
        }
    }
}

// actividad 1
@Composable
fun CabeceraUsuario() {
    Image(
        painter = painterResource(id = R.drawable.user_avatar),
        contentDescription = stringResource(id = R.string.avatar_desc),
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape)
    )
}

@Composable
fun InfoUsuario() {
    Column {
        Text(
            text = stringResource(id = R.string.user_name),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(id = R.string.user_rol),
            style = MaterialTheme.typography.bodyLarge
        )
        Row {
            Text(
                text = stringResource(id = R.string.estadisticas_peliculas, 42),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(id = R.string.estadisticas_resenas, 12),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun PerfilUsuario() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CabeceraUsuario()
        Spacer(modifier = Modifier.width(12.dp))
        InfoUsuario()
    }
}