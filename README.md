<div align="center">

# 🎬 DAMFlix · Parte 3

**Aplicación Android para almacenar y consultar películas**

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)

**Módulo:** Programación Multimedia y Dispositivos Móviles  
**Unidad de Trabajo Nº2:** Desarrollo de aplicaciones Android  
**Parte 3 · Actividad 3:** Catálogo Dinámico  

2º DAM · CIFP Villa de Agüimes

</div>

---

## 🎯 Objetivo

Convertir los datos de las películas en una lista scrollable altamente optimizada, utilizando la estructura de componentes de **Material Design 3**.

## ✅ Requisitos funcionales

| Requisito | Cómo se cumple |
|---|---|
| **1. Estructura Scaffold** | La pantalla `PantallaCatalogoDAMFlix()` está dentro de un `Scaffold` con una `TopAppBar` ("DAMFlix - Catálogo") |
| **2. Catálogo dinámico** | Las 15 películas se muestran con un `LazyColumn` scrollable, una `Card` por película |
| **3. Acción interactiva** | Cada tarjeta tiene un `IconButton` con icono de corazón para marcar o desmarcar la película como favorita, y la tarjeta se recompone al instante |
| **4. Optimizaciones** | Se asigna una clave única a cada elemento con `key = { p -> p.id }` en `items()` |

## ⚙️ Funcionamiento

### Modelo de datos

Cada película es un objeto `Pelicula` con un identificador, un título y un estado de favorita:

```kotlin
class Pelicula(val id: Int, val titulo: String) {
    var esFavorita by mutableStateOf(false)
}
```

`esFavorita` es un estado de Compose: cuando cambia, las tarjetas que lo leen se recomponen.

### Scaffold y TopAppBar

```kotlin
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
        // ListaPeliculas(...)
    }
}
```

El `innerPadding` evita que la lista quede tapada por la barra superior.

### LazyColumn con claves

```kotlin
LazyColumn(
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(items = peliculas, key = { p -> p.id }) { pelicula ->
        // Card con el título y el IconButton
    }
}
```

`LazyColumn` solo dibuja los elementos visibles en pantalla. La `key` permite a Compose identificar cada película, aunque cambie el orden o el contenido de la lista.

### Botón de favorito

```kotlin
IconButton(onClick = { onToggleFavorito(pelicula.id) }) {
    Icon(
        imageVector = if (pelicula.esFavorita) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        contentDescription = "Favorito",
        tint = if (pelicula.esFavorita) Color.Red else Color.Gray
    )
}
```

- Película favorita: corazón relleno y rojo.
- Película no favorita: corazón vacío y gris.

Al pulsar el botón, la pantalla busca la película por su `id` y cambia su estado:

```kotlin
val pelicula = peliculas.first { it.id == id }
pelicula.esFavorita = !pelicula.esFavorita
```

> En `ListaPeliculas` están comentados `PerfilUsuario()` y `FormularioPelicula()`, porque son de las partes 1 y 2 y esta actividad no los pide. Las funciones siguen en `MainActivity.kt`.

## 🗂️ Estructura

```
app/src/main
├── java/com/example/damflix
│   └── MainActivity.kt        # PantallaCatalogoDAMFlix, ListaPeliculas, Pelicula
│                              # (más el perfil y el formulario de las partes 1 y 2)
└── res
    └── values
        └── strings.xml        # Textos del perfil y del formulario
```

## 🧪 Cómo probar el catálogo

1. Al abrir la app se ve la barra superior "DAMFlix - Catálogo" y la lista de 15 películas de ejemplo.
2. Todos los corazones empiezan vacíos y grises.
3. Pulsa el corazón de una película: se pone rojo y relleno al momento.
4. Púlsalo otra vez: vuelve a gris.
5. Desliza la lista hacia arriba: como las 15 películas no caben en pantalla, se desplaza hasta "Toy Story".
6. Marca un favorito, desplázate y vuelve: el corazón sigue rojo, porque cada película conserva su estado.

## 🚀 Cómo ejecutarlo

1. Clona el repositorio:
```bash
git clone https://github.com/ivanhernandezbetancor/DAMFlix-PARTE-3.git
```
2. Ábrelo con **Android Studio**.
3. Espera a que termine la sincronización de Gradle.
4. Ejecuta la app en un emulador o en un dispositivo físico.

## 🛠️ Tecnologías

- Kotlin
- Jetpack Compose
- Material 3

### Dependencia necesaria

Los iconos `Favorite` y `FavoriteBorder` requieren esta línea en `dependencies` de `app/build.gradle.kts`:

```kotlin
implementation("androidx.compose.material:material-icons-core")
```
