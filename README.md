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

## Evidencias
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/470a6eb0-8264-4fd5-a2b1-86dbd4eb2560" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/5c9c81fa-eb3f-401b-9f82-5360089d9575" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/b451114d-3be4-4e53-9cf0-a55a3b88b667" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/2df16fb2-0bf8-493b-a86e-dc9512bc6054" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/fdccf510-dacc-4fd9-ba27-d7e9f7bed82e" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/a3caf526-0c17-4919-b5e1-ddc4402010d7" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/ee81e031-57a1-4e17-82db-bd347abeb754" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/28a07393-374c-4274-a377-a4ff6ad1e68d" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/616dfdd9-9430-40d0-a0ee-0a3a5b97b360" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/e310a7d9-c238-451c-bc48-9f496aaa8244" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/5284e6f3-c9d1-440b-a49d-bdff09bb5274" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/3fb5dd8c-e8f1-49d0-964b-9ca2dd2e0b84" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/59dd55e2-be88-4eef-af89-7d6379123eb4" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/5597d8bd-a31d-4726-827b-8f412bed2d36" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/0134b333-2e43-49fa-8314-166fc6f4deb3" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/d3340625-7427-46a3-b58f-b9316133cda8" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/925f2bb8-5d65-42ae-bcc4-204bbd3371a9" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/dd806499-eda7-4a9b-a082-29bd710ffb53" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/6cdbf7d3-52cf-4ca5-98e8-a2c869b91709" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/829f74af-e500-4a16-8dc9-ed70e2cc8183" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/196a1fe7-fc85-4793-b468-1267512b4620" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/58b2c220-331b-41d6-9e34-fb95e0427bcf" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/fdbc23b3-c070-49bf-a5dc-8ab368d9a483" />
<img width="720" height="1600" alt="image" src="https://github.com/user-attachments/assets/aa6b2206-8f9c-4ade-a03e-16d83693f478" />


