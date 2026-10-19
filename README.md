<div align="center">

# 🎬 DAMFlix · Parte 2

**Aplicación Android para almacenar y consultar películas**

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-757575?style=for-the-badge&logo=materialdesign&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white)

**Módulo:** Programación Multimedia y Dispositivos Móviles  
**Unidad de Trabajo Nº2:** Desarrollo de aplicaciones Android  
**Parte 2 · Actividad 2:** Formulario de Alta e Interacción con Estado  

2º DAM · CIFP Villa de Agüimes

</div>

---

## 🎯 Objetivo

Añadir interactividad a la aplicación construyendo un formulario de registro de nuevas películas, utilizando la gestión de estado de **Jetpack Compose**.

## ✅ Requisitos funcionales

| Requisito | Cómo se cumple |
|---|---|
| **1. Campos de entrada** | Tres `OutlinedTextField`: título de la película, director/a y año de estreno |
| **2. Selección e interacción** | `Checkbox` para indicar si la película ya ha sido vista o está pendiente |
| **3. Gestión de estado** | El contenido de los campos se guarda con `remember` y `mutableStateOf` |
| **4. Validación dinámica** | El botón "Guardar Película" permanece deshabilitado si el título o el director están vacíos |

## ⚙️ Funcionamiento

El formulario es el composable `FormularioPelicula()`, que guarda cuatro estados:

```kotlin
var titulo by remember { mutableStateOf("") }
var director by remember { mutableStateOf("") }
var anio by remember { mutableStateOf("") }
var esVista by remember { mutableStateOf(false) }
```

1. Cada `OutlinedTextField` muestra su estado con `value` y lo actualiza con `onValueChange`. Al escribir, el estado cambia y Compose vuelve a dibujar la pantalla (recomposición).
2. El `Checkbox` funciona igual, con un estado booleano (`esVista`).
3. El botón recalcula si está habilitado en cada recomposición:

```kotlin
enabled = titulo.isNotBlank() && director.isNotBlank()
```

Si falta el título o el director, el botón se muestra deshabilitado. En cuanto los dos tienen texto, se activa. El año no entra en la validación.

> De momento el botón no guarda la película: el `onClick` está vacío porque esta actividad solo pide el formulario y la validación.

Todos los textos se consumen desde `strings.xml` con `stringResource()`, sin cadenas escritas a mano.

## 🗂️ Estructura

```
app/src/main
├── java/com/example/damflix
│   └── MainActivity.kt        # FormularioPelicula (y la pantalla de perfil de la Parte 1)
└── res
    └── values
        └── strings.xml        # Textos de la interfaz
```

## 🧪 Cómo probar el formulario

1. Al abrir la app, el botón "Guardar Película" está deshabilitado.
2. Escribe solo el título: sigue deshabilitado.
3. Escribe también el director: se habilita.
4. Borra el título o el director: vuelve a deshabilitarse.

## 🚀 Cómo ejecutarlo

1. Clona el repositorio:
```bash
git clone https://github.com/ivanhernandezbetancor/DAMFlix-PARTE-2.git
```
2. Ábrelo con **Android Studio**.
3. Espera a que termine la sincronización de Gradle.
4. Ejecuta la app en un emulador o en un dispositivo físico.

## 🛠️ Tecnologías

- Kotlin
- Jetpack Compose
- Material 3
