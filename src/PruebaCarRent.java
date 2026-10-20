import java.time.LocalDate;

/**
 * Clase de prueba: contiene el método main que ejecuta los casos a) a f) del enunciado.
 * Todos los datos se asignan directamente (sin teclado).
 */
public class PruebaCarRent {

    private static void titulo(String texto) {
        System.out.println("\n=== " + texto + " ===");
    }

    public static void main(String[] args) {

        // ---------- a) Crear cliente y probar validaciones ----------
        titulo("a) Crear cliente (con datos inválidos para probar validaciones)");
        Cliente cliente1 = new Cliente("123", "   ");          // cédula y nombre inválidos
        cliente1.imprimirMensaje("Estado tras crear: " + cliente1);
        cliente1.setCedula("12345678-X");                        // dígito verificador inválido
        cliente1.setCedula("12345678-9");                        // válida
        cliente1.setNombre("Ana Rojas");                         // válido
        cliente1.imprimirMensaje("Estado tras corregir: " + cliente1);

        // ---------- b) Crear vehículo y probar validaciones ----------
        titulo("b) Crear vehículo (con datos inválidos para probar validaciones)");
        Vehiculo vehiculo1 = new Vehiculo("abc", "toyota", "yaris", 1999); // patente y año inválidos
        vehiculo1.imprimirMensaje("Estado tras crear: " + vehiculo1);
        vehiculo1.setPatente("abcd12-3");                        // válida (se guarda en mayúscula)
        vehiculo1.setAnioFabricacion(2020);                      // válido
        vehiculo1.imprimirMensaje("Estado tras corregir: " + vehiculo1);

        // ---------- c) Deshabilitar cliente ----------
        titulo("c) Deshabilitar cliente");
        cliente1.deshabilitar();
        cliente1.imprimirMensaje("Estado: " + cliente1);

        // ---------- d) Asignar vehículo en mantención ----------
        titulo("d) Asignar vehículo en mantención");
        vehiculo1.asignarMantencion();
        vehiculo1.imprimirMensaje("Estado: " + vehiculo1);

        // ---------- e) Ingreso de arriendos (agregación) ----------
        titulo("e) Ingreso de arriendos");
        Cliente cliente2 = new Cliente("20023066-3", "Juan Pérez");
        Cliente cliente3 = new Cliente("15555666-K", "María López");
        Vehiculo vehiculo2 = new Vehiculo("CZSL60-6", "Toyota", "Corolla", 2019);

        // Arriendo 1: debe ingresarse correctamente
        Arriendo arriendo1 = new Arriendo(23453, vehiculo2, cliente2, LocalDate.of(2021, 1, 10), 3);
        if (arriendo1.ingresarArriendo(45000)) {
            arriendo1.imprimirMensaje("Resultado: arriendo 1 ingresado. Se genera el ticket:");
            arriendo1.generarTicket();
        } else {
            arriendo1.imprimirMensaje("Resultado: arriendo 1 rechazado.");
        }
        vehiculo2.imprimirMensaje("Estado del vehículo arrendado: " + vehiculo2);

        // Arriendo 2: días inválidos (se corrige) y el vehículo ya está arrendado -> debe fallar
        Arriendo arriendo2 = new Arriendo(23454, vehiculo2, cliente3, LocalDate.of(2021, 1, 11), 12);
        arriendo2.setDiasArriendo(5);
        if (arriendo2.ingresarArriendo(30000)) {
            arriendo2.imprimirMensaje("Resultado: arriendo 2 ingresado.");
        } else {
            arriendo2.imprimirMensaje("Resultado: arriendo 2 rechazado (no se genera ticket).");
        }

        // Regla extra: no se puede asignar mantención a un vehículo arrendado
        vehiculo2.asignarMantencion();

        // ---------- f) Ingreso de devoluciones (asociación) ----------
        titulo("f) Ingreso de devoluciones");

        // Devolución 1: correcta
        Devolucion devolucion1 = new Devolucion(arriendo1, LocalDate.of(2021, 1, 13));
        if (devolucion1.ingresarDevolucion(vehiculo2)) {
            devolucion1.imprimirMensaje("Resultado: devolución 1 ingresada.");
        } else {
            devolucion1.imprimirMensaje("Resultado: devolución 1 rechazada.");
        }
        vehiculo2.imprimirMensaje("Estado del vehículo devuelto: " + vehiculo2);

        // Devolución 2: fecha inválida (se corrige) y vehículo distinto al del arriendo -> debe fallar
        Vehiculo vehiculo3 = new Vehiculo("PQRS11-2", "Kia", "Rio", 2022);
        Devolucion devolucion2 = new Devolucion(arriendo1, LocalDate.of(2021, 1, 5));
        devolucion2.setFechaDevolucion(LocalDate.of(2021, 1, 14));
        if (devolucion2.ingresarDevolucion(vehiculo3)) {
            devolucion2.imprimirMensaje("Resultado: devolución 2 ingresada.");
        } else {
            devolucion2.imprimirMensaje("Resultado: devolución 2 rechazada.");
        }
        vehiculo3.imprimirMensaje("Estado del vehículo (devolución rechazada): " + vehiculo3);
    }
}
