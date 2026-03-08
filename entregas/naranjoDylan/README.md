# Entrega - naranjoDylan

Entrega personal organizada con el estandar solicitado por la asignatura.

## Navegacion

- Codigo fuente: `src/`
- Modelos UML: `modelosUML/`
- Documentacion: `docs/`
- Imagenes: `images/`

## Artefactos incluidos

- `src/reto-006/`: implementacion de editor en consola (Edlin).
- `modelosUML/reto-006/diagram.puml`: diagrama UML del reto.
- `docs/reto-006.md`: explicacion de solucion, estructura y ejecucion.
- `docs/legibilidad.md`: plantilla completable para Reto 002 - Legibilidad.
- `docs/guardado/HOLA.edin`: archivo de ejemplo serializado.

## Ejecucion

Desde `entregas/naranjoDylan/src/reto-006`:

```bash
javac *.java
java Edlin
```

## Notas

- Se elimino dependencia de rutas absolutas para mejorar portabilidad.
- Se mejoro entrada de datos y control de errores.
- Se simplifico historial de deshacer/rehacer para mejorar mantenibilidad.
