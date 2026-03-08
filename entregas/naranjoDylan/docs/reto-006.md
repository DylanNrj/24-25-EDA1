# Reto 006 - Edlin

Editor de texto por lineas en consola con operaciones basicas y persistencia por serializacion.

## Estructura

- Codigo: `src/reto-006`
- Diagrama UML (fuente): `modelosUML/reto-006/diagram.puml`
- Documentacion: `docs/reto-006.md`
- Ejemplo de guardado: `docs/guardado/HOLA.edin`

## Responsabilidades

- `Edlin.java`: punto de entrada, creacion/carga y guardado final.
- `Menu.java`: interaccion con el usuario y lectura de opciones.
- `Editor.java`: modelo del documento, edicion y deshacer/rehacer.
- `SerializadorEditor.java`: persistencia en archivos `.edin`.

## Opciones del menu

1. Editar linea actual
2. Borrar linea actual
3. Copiar linea actual
4. Pegar en linea actual
5. Cambiar linea activa
6. Intercambiar linea actual con otra
7. Deshacer
8. Rehacer
9. Salir

## Como ejecutar

Desde `entregas/naranjoDylan/src/reto-006`:

```bash
javac *.java
java Edlin
```

**Nota**: Compilar todos los archivos con `*.java` es necesario por las dependencias entre clases. No usar comandos individuales obsoletos.

### Modo rápido sin persistencia

Para edición temporal sin guardar/cargar archivos:

```bash
java Edlin --no-persist
```

Este flag omite los prompts de carga inicial y guardado final, útil para pruebas rápidas.

## Mejoras de legibilidad aplicadas

- Eliminada ruta absoluta del sistema para permitir ejecucion en cualquier equipo.
- Entrada de usuario robusta en `Menu` y `Edlin` para evitar errores por `nextInt`.
- Historial de deshacer/rehacer simplificado con pila y limite fijo en `Editor`.
- Nombres y mensajes consistentes en todas las clases.
