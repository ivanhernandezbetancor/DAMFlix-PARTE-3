package com.example.damflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                PantallaCatalogoDAMFlix() // añadido de la actividad 3
            }
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

// actividad 3
class Pelicula(val id: Int, val titulo: String) {
    var esFavorita by mutableStateOf(false)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCatalogoDAMFlix() {

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
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DAMFlix - Catálogo") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ListaPeliculas(
                peliculas = peliculas,
                onToggleFavorito = { id ->
                    val pelicula = peliculas.first { it.id == id }
                    pelicula.esFavorita = !pelicula.esFavorita
                }
            )
        }
    }
}

@Composable
fun ListaPeliculas(peliculas: List<Pelicula>, onToggleFavorito: (Int) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // No hace falta para la actividad 3 (era de las actividades 1 y 2)
        // item { PerfilUsuario() }
        // item { FormularioPelicula() }

        items(items = peliculas, key = { p -> p.id }) { pelicula ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = pelicula.titulo, modifier = Modifier.weight(1f))
                    IconButton(onClick = { onToggleFavorito(pelicula.id) }) {
                        Icon(
                            imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorito",
                            tint = if (pelicula.esFavorita) Color.Red else Color.Gray
                        )
                    }
                }
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