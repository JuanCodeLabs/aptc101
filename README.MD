# APTC101 - Proyecto: Sistema Car-REnt

## Información del curso

|                 |                                          |
| --------------- | ---------------------------------------- |
| **Asignatura**  | Programación Orientada a Objetos |
| **Módulo**      | APTC101                                  |
| **Integrante**  | Juan Díaz                                |

---

## Descripción del proyecto

Este repositorio contiene el desarrollo del proyecto de la asignatura, cuyo objetivo es diseñar e implementar un sistema de software en Java (POO) para la empresa **Car-REnt**, que permita **administrar vehículos, clientes, arriendos y devoluciones**.

El sistema implementa las clases principales del negocio con validaciones, cálculos de montos, generación de tickets y manejo de estados (deshabilitar clientes, asignar mantención a vehículos).

---

## Problemática a resolver

La empresa Car-REnt necesita un sistema para gestionar su operación de arriendo de vehículos. De esto surge la necesidad de diseñar un sistema funcional que permita:

- **Administrar** clientes con validación de cédula y capacidad de deshabilitarlos.
- **Gestionar** vehículos con validaciones de patente, año y estado de mantención.
- **Procesar** arriendos evaluando condiciones, calculando montos y generando tickets.
- **Registrar** devoluciones validando fechas y estados.

El sistema debe implementar POO con encapsulamiento, validaciones en los setters, y demostrar el funcionamiento a través de casos de prueba que cubran las diferentes situaciones.

---

## Contenido de la carpeta

```
src/
  Cliente.java        Clase Cliente (validación de cédula, deshabilitar).
  Vehiculo.java       Clase Vehiculo (validaciones, asignar mantención).
  Arriendo.java       Clase Arriendo (evaluar, ingresar, monto, ticket).
  Devolucion.java     Clase Devolucion (ingresar devolución).
  PruebaCarRent.java  Clase de prueba con el método main (resolviendo casos a al f).
```

---

## IDE y versión de Java

- Desarrollado con Java 21 (JDK 21). Funciona con Java 8 o superior.
- No depende de librerías externas ni usa paquetes (package): todos los archivos están en la misma carpeta src.
- Se puede abrir con cualquier IDE (Eclipse, IntelliJ IDEA, NetBeans, VS Code).

### Cómo ejecutarlo desde un IDE

1. Cree un proyecto Java nuevo y copie los 5 archivos .java a la carpeta src.
2. Ejecute la clase PruebaCarRent (tiene el método main).

### Cómo ejecutarlo desde la terminal

1. Abra una terminal dentro de la carpeta del proyecto.
2. Compile:   `javac -encoding UTF-8 -d bin src/*.java`
3. Ejecute:   `java -cp bin PruebaCarRent`

---

## Consideraciones

- El programa NO usa teclado: todos los datos se asignan directamente en el main, como pide el enunciado.
- Si en la consola de Windows las tildes se ven mal, ejecute antes "chcp 65001" o use la consola del IDE.
- Los datos inválidos se informan con mensajes. Por ejemplo, en la prueba hay una cédula, una patente, un año, unos días y una fecha de devolución inválidos a propósito, para comprobar las validaciones de los set.
- La prueba muestra: validaciones (a y b), deshabilitar cliente (c), mantención (d), un arriendo exitoso con ticket y uno rechazado (e), y una devolución exitosa y una rechazada (f).
- Regla interpretada: "días mayor que 1 y menor que 10" = valores de 2 a 9.

### Semana 3 - Guia de Aprendizaje 1
- Se realiza un software PruebaCarRent.java que demuestra el funcionamiento de las clases Cliente, Vehiculo, Arriendo y Devolucion.
- Se genera el documento Informe_Car-REnt.pdf explicando el procedimiento, las deciciones de clases y los diagramas correspondientes de Casos de Uso y Diagramas de Clases.

---

_Este README se irá actualizando a medida que el proyecto avance hasta llegar a su finalización._# aptc101
