import java.time.LocalDate;

/**
 * Clase Devolucion: representa la devolución de un vehículo arrendado.
 * Relación: ASOCIACIÓN con Arriendo (la devolución pertenece a un arriendo específico).
 */
public class Devolucion {

    // ------------------------- Atributos -------------------------
    private Arriendo arriendo;            // asociación
    private LocalDate fechaDevolucion;    // no puede ser menor a la fecha del arriendo

    // ------------------------ Constructor ------------------------
    /** Crea una devolución para un arriendo existente. El arriendo se asigna primero. */
    public Devolucion(Arriendo arriendo, LocalDate fechaDevolucion) {
        setArriendo(arriendo);
        setFechaDevolucion(fechaDevolucion);
    }

    // ------------------------- Accesores -------------------------
    public Arriendo getArriendo() {
        return arriendo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    // ------------------- Mutadores con validación -------------------
    public boolean setArriendo(Arriendo arriendo) {
        if (arriendo != null) {
            this.arriendo = arriendo;
            return true;
        }
        imprimirMensaje("[Devolución] El arriendo no puede ser nulo.");
        return false;
    }

    /** La fecha de devolución no puede ser menor a la fecha del arriendo. */
    public boolean setFechaDevolucion(LocalDate fechaDevolucion) {
        if (fechaDevolucion == null || arriendo == null) {
            imprimirMensaje("[Devolución] Fecha inválida: se requiere una fecha y un arriendo asignado.");
            return false;
        }
        if (fechaDevolucion.isBefore(arriendo.getFechaArriendo())) {
            imprimirMensaje("[Devolución] Fecha inválida: " + fechaDevolucion
                    + " es anterior a la fecha del arriendo (" + arriendo.getFechaArriendo() + ").");
            return false;
        }
        this.fechaDevolucion = fechaDevolucion;
        return true;
    }

    // ------------------------ Comportamiento ------------------------
    /** Retorna true si los datos del vehículo recibido coinciden con los del arriendo. */
    public boolean coincideConArriendo(Vehiculo vehiculoDevuelto) {
        Vehiculo original = arriendo.getVehiculo();
        return original.getPatente().equals(vehiculoDevuelto.getPatente())
                && original.getMarca().equals(vehiculoDevuelto.getMarca())
                && original.getModelo().equals(vehiculoDevuelto.getModelo())
                && original.getAnioFabricacion() == vehiculoDevuelto.getAnioFabricacion();
    }

    /**
     * Ingresar devolución: valida que los datos del vehículo devuelto correspondan
     * a los del arriendo. Si son correctos deja el vehículo en condición D y retorna true.
     */
    public boolean ingresarDevolucion(Vehiculo vehiculoDevuelto) {
        if (arriendo == null || fechaDevolucion == null || vehiculoDevuelto == null) {
            imprimirMensaje("[Devolución] Faltan datos para ingresar la devolución.");
            return false;
        }
        if (!coincideConArriendo(vehiculoDevuelto)) {
            imprimirMensaje("[Devolución] Rechazada: el vehículo " + vehiculoDevuelto.getPatente()
                    + " no corresponde al arriendo N° " + arriendo.getNumero() + ".");
            return false;
        }
        if (arriendo.getVehiculo().getCondicion() != 'A') {
            imprimirMensaje("[Devolución] Rechazada: el vehículo del arriendo N° "
                    + arriendo.getNumero() + " no está arrendado.");
            return false;
        }
        arriendo.getVehiculo().setCondicion('D');
        vehiculoDevuelto.setCondicion('D');
        imprimirMensaje("[Devolución] Devolución del arriendo N° " + arriendo.getNumero()
                + " ingresada correctamente. Vehículo " + vehiculoDevuelto.getPatente() + " disponible.");
        return true;
    }

    /** Único punto de salida de mensajes al usuario en esta clase. */
    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String toString() {
        return "Devolución [arriendo N°=" + (arriendo == null ? null : arriendo.getNumero())
                + ", fechaDevolución=" + fechaDevolucion + "]";
    }
}
