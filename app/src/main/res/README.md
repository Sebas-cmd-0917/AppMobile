# Taller 1 - Hello Android

## Información del Estudiante
**Nombre:** Joan Sebastian Jaimes
**Código:** [Inserta tu código estudiantil aquí]
**Fecha:** 27 de febrero de 2026

## Respuestas

### 1. Función del AndroidManifest.xml
El archivo `AndroidManifest.xml` funciona como el documento de configuración global de la aplicación. Su función principal es proporcionar al sistema operativo Android información esencial sobre la app antes de que pueda ejecutar su código. En este archivo se declaran componentes fundamentales como las *Activities*, los servicios, los metadatos, el icono y el tema visual. También define los permisos necesarios para que la aplicación funcione y especifica qué *Activity* es el punto de entrada principal (`LAUNCHER`) al abrir la app.

### 2. Diferencia entre activity_main.xml y MainActivity.kt
La diferencia radica en sus responsabilidades dentro del patrón de diseño de la aplicación:
* **`activity_main.xml`**: Es un archivo de recursos XML que define el **diseño y la interfaz gráfica** de la pantalla. En él se estructuran y configuran visualmente los elementos (vistas) como botones, textos (`TextView`), márgenes, colores y posiciones.
* **`MainActivity.kt`**: Es el archivo de código fuente escrito en Kotlin que maneja la **lógica y el comportamiento** de esa pantalla. Se encarga de interactuar con la interfaz gráfica (referenciando los elementos del XML) para darle funcionalidad, como por ejemplo, reaccionar a los clics del usuario, incrementar un contador o mostrar notificaciones (Toast).

### 3. Gestión de recursos en Android
Android gestiona los recursos limitados del dispositivo móvil (como la memoria RAM y la batería) principalmente a través de un estricto control del **ciclo de vida de las aplicaciones**. Si el sistema necesita liberar memoria para la aplicación que está en uso, puede detener o destruir automáticamente las aplicaciones que están en segundo plano. Además, Android optimiza el rendimiento separando los recursos estáticos (imágenes, textos en diferentes idiomas, diseños) en la carpeta `res/`, cargando en la memoria únicamente los que corresponden a la configuración actual del dispositivo en ese momento.

### 4. Aplicaciones famosas que utilizan Kotlin
1. **Netflix**: Ha integrado Kotlin en su aplicación de Android para mejorar la estabilidad de la interfaz y la experiencia general de reproducción.
2. **Pinterest**: Fue una de las primeras grandes empresas en adoptar oficialmente Kotlin para su app móvil, aprovechando su código conciso.
3. **Uber**: Utiliza Kotlin extensamente en sus herramientas internas y ha migrado partes críticas de su aplicación para usuarios y conductores a este lenguaje.
   *(Otras aplicaciones destacadas incluyen Duolingo, Coursera y Trello).*

## Capturas de Pantalla

![Captura del emulador](captura_emulador.png)

*(Nota: Asegúrate de guardar la imagen de tu emulador exactamente con el nombre `captura_emulador.png` en la misma carpeta donde ubiques este README).*