📱 Formulario de Registro - Android (Compose + MVVM)
====================================================

![GitHub repo size](https://img.shields.io/github/repo-size/jonathand77/LabsCM20252-Gr03)
![GitHub contributors](https://img.shields.io/github/contributors/jonathand77/LabsCM20252-Gr03)
![GitHub last commit](https://img.shields.io/github/last-commit/jonathand77/LabsCM20252-Gr03)
![Languages](https://img.shields.io/github/languages/count/jonathand77/LabsCM20252-Gr03)

## 👥 Integrantes

| 👨‍💻 Nombre | 📧 Correo | 🐙 Usuario GitHub |
|---|---|---|
| **Jonathan David Fernandez Vargas** | jonathand.fernandez@udea.edu.co | [jonathand77](https://github.com/jonathand77) |
| **Valeria Alvarez Fernandez** | valeria.alvarezf@udea.edu.co | [vaf88](https://github.com/vaf88) |
| **Santiago Arenas Gomez** | santiago.arenas1@udea.edu.co | [sag0719](https://github.com/sag0719) |

---

📖 Descripción
--------------

Este proyecto es una aplicación móvil en **Android Jetpack Compose** con arquitectura **MVVM**.Implementa un flujo de registro de usuario dividido en dos pantallas:

1.  **Datos Personales** (nombre, apellido, edad).
    
2.  **Datos de Contacto** (teléfono, email).
    

Cada pantalla valida sus campos antes de permitir avanzar y, al finalizar, se muestra un mensaje confirmando el guardado.

🛠️ Tecnologías utilizadas
--------------------------

*   ⚙️ **Kotlin** como lenguaje principal
    
*   🎨 **Jetpack Compose** para la interfaz de usuario declarativa
    
*   🏗️ **MVVM + StateFlow** para la gestión del estado
    
*   🎭 **Material 3 Theme** con tipografía y colores personalizados
    
*   📦 **Android Studio Giraffe** como entorno de desarrollo
    

📂 Estructura del proyecto
--------------------------

app/   
├── ui/   │   
├── theme/        # 🎨 Archivos de colores, tipografía y tema   │   
├── PersonalDataActivity.kt   │   
├── ContactDataActivity.kt   
├── viewmodel/   │   
├── PersonalViewModel.kt   │   
├── ContactViewModel.kt   
├── MainActivity.kt   # 🚀 Punto de entrada   
├── AndroidManifest.xml   `

🚀 Instalación y ejecución
--------------------------

### 🔹 1. Clonar el repositorio

git clone https://github.com/usuario/proyecto-formulario.git  cd proyecto-formulario   `


### 🔹 2. Abrir en Android Studio

1.  Abrir **Android Studio** → File > Open → Selecciona la carpeta del proyecto.
    
2.  Esperar a que Gradle sincronice dependencias.
    


### 🔹 3. Ejecutar la app

#### 📱 En un emulador

*   Ir a Tools > Device Manager → Crear un dispositivo virtual.
    
*   Seleccionar imagen de sistema (Android 13/14).
    
*   Correr con ▶ Run.
    


#### 📲 En un dispositivo físico

1.  Activar **Opciones de desarrollador** → Depuración USB.
    
2.  Conectar el teléfono al PC vía cable.
    
3.  Seleccionar el dispositivo en Android Studio y ejecutar ▶.

    

🧩 Flujo de la aplicación
-------------------------

1.  El usuario llena sus **datos personales** → botón **Siguiente**.
    
2.  Llena sus **datos de contacto** → botón **Guardar**.
    
3.  Se muestra un **Toast de confirmación** ✅.
    


✨ Mejores prácticas aplicadas
-----------------------------

*   📌 Separación clara de capas (**UI**, **ViewModel**, **Theme**).
    
*   ⚡ Uso de **StateFlow** para un manejo reactivo del estado.
    
*   🎨 Consistencia en colores y tipografía con MaterialTheme.
    
*   🔒 Validación de campos antes de permitir navegación.
    
*   📂 Código organizado y fácil de escalar.
    


📜 Licencia
-----------

Este proyecto está bajo la licencia MIT.
