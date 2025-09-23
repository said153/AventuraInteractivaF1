# AventuraInteractivaF1

## 📱 Descripción de la Aplicación

**F1 Aventura Interactiva** es una aplicación interactiva que permite explorar información sobre las escuderías de Fórmula 1, sus automóviles y pilotos. La aplicación ofrece una jerárquia con tres niveles principales:

### Características Principales
- **📋 Vista de Escuderías**: Lista completa de todas las escuderías con colores e información básica
- **🚗 Vista de Automóviles**: Detalles técnicos de los monoplazas
- **👨‍🚀 Vista de Pilotos**: Perfiles de pilotos con fotografías

### Tecnologías Utilizadas

- **Lenguaje**: Kotlin 100%
- **Arquitectura**: MVVM (Model-View-ViewModel)
- **UI**: View Binding + Fragments
- **Navegación**: Activities con navegación jerárquica

## 🚀 Instrucciones de Instalación y Ejecución

### Prerequisitos

- **Android Studio**
- **SDK Android**

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/said153/AventuraInteractivaF1.git
   
2. **Abrir el proyecto en Android Studio**
   - File → Open → Seleccionar la carpeta del proyecto
   - Esperar a que Gradle sincronice automáticamente

3. **Ejecutar la aplicación**
   - Conectar un dispositivo Android o iniciar un emulador
   - Hacer clic en el botón "Run" (▶️) o usar `Ctrl+R`

### Estructura de Archivos Importantes

```
app/src/main/java/com/f1tracker/
├── data/
│   ├── model/
│   │   ├── Escuderia.kt
│   │   ├── Auto.kt
│   │   └── Piloto.kt
│   └── repository/
│       └── F1Repository.kt
├── ui/
│   ├── teams/
│   │   ├── TeamsActivity.kt
│   │   ├── TeamsFragment.kt
│   │   ├── TeamsAdapter.kt
│   │   └── TeamsViewModel.kt
│   ├── cars/
│   │   ├── CarsActivity.kt
│   │   ├── CarsFragment.kt
│   │   └── CarsViewModel.kt
│   └── drivers/
│       ├── DriversActivity.kt
│       ├── DriversFragment.kt
│       ├── DriversAdapter.kt
│       └── DriversViewModel.kt
└── utils/
    └── AnimationUtils.kt
```

## 🎨 Decisiones de Diseño y Mecanismos de Transición

### Arquitectura MVVM

**Decisión**: Patrón MVVM para separar la lógica de la interfaz de usuario.

**Beneficios**:
- **Separación de responsabilidades**: Cada componente tiene una función específica
- **Reutilización**: Los ViewModels pueden compartirse entre múltiples vistas

### Sistema de Navegación Jerárquica

**Estructura de Navegación**:
```
MainActivity (Escuderías)
    ↓
CarsActivity (Auto de la escudería)
    ↓
DriversActivity (Pilotos de la escudería)
```

**Decisiones de Diseño**:
- **Activities separadas**: Para mantener contextos independientes y facilitar la navegación hacia atrás
- **Fragments reutilizables**: Para componentes UI que pueden necesitar reutilización futura

### Sistema de Animaciones Personalizadas

**AnimationUtils.kt** - Biblioteca personalizada de animaciones:

```kotlin
// Ejemplos de animaciones implementadas:
- zoomIn(): Efecto de zoom suave para elementos importantes
- slideInFromRight(): Entrada deslizante para listas y cards
- fadeIn(): Aparición gradual para textos informativos  
- rotate360(): Animación de rotación para elementos interactivos
```

### Gestión de Estado y Datos

**Repository Pattern**: 
- **F1Repository.kt** actúa como base de datos

## 🛠️ Retos y Soluciones

### Reto 1: Comunicación Fragment-Activity
**Problema**: La navegación desde `CarsFragment` hacia `DriversActivity` no funcionaba correctamente.  
**Solución**: Mejor manejo de datos.

### Reto 2: Debug y Logging Efectivo
**Problema**: Era difícil seguir el flujo de datos y encontrar fallos dentro de la aplicación.  
**Solución**: Se agregó un sistema de mensajes simples en la consola con símbolos y emojis, lo que facilitó detectar en qué parte del proceso ocurrían los problemas.


