import java.time.Year;

/**
 * Clase Vehiculo: representa un automóvil de la empresa Car-REnt.
 * Condición: D = disponible, A = arrendado, M = en mantención.
 */
public class Vehiculo {

    // ------------------------- Atributos -------------------------
    private String patente;        // largo 8, se guarda en mayúscula
    private String marca;          // se guarda en mayúscula
    private String modelo;         // se guarda en mayúscula
    private int anioFabricacion;   // desde 2000 hasta el año actual
    private char condicion;        // 'D', 'A' o 'M'

    // ------------------------ Constructor ------------------------
    /**
     * Crea un vehículo. Los datos pasan por los mutadores para validarse.
     * Un vehículo nuevo siempre parte con condición D (disponible).
     */
    public Vehiculo(String patente, String marca, String modelo, int anioFabricacion) {
        setPatente(patente);
        setMarca(marca);
        setModelo(modelo);
        setAnioFabricacion(anioFabricacion);
        this.condicion = 'D';
    }

    // ------------------------- Accesores -------------------------
    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public char getCondicion() {
        return condicion;
    }

    // ------------------- Mutadores con validación -------------------
    /** Valida (largo 8) y asigna la patente en mayúscula. */
    public boolean setPatente(String patente) {
        if (patente != null && patente.length() == 8) {
            this.patente = patente.toUpperCase();
            return true;
        }
        imprimirMensaje("[Vehículo] Patente inválida: '" + patente + "'. Debe tener largo 8.");
        return false;
    }

    /** Valida (no vacía) y asigna la marca en mayúscula. */
    public boolean setMarca(String marca) {
        if (marca != null && !marca.trim().isEmpty()) {
            this.marca = marca.trim().toUpperCase();
            return true;
        }
        imprimirMensaje("[Vehículo] Marca inválida: no puede estar vacía.");
        return false;
    }

    /** Valida (no vacío) y asigna el modelo en mayúscula. */
    public boolean setModelo(String modelo) {
        if (modelo != null && !modelo.trim().isEmpty()) {
            this.modelo = modelo.trim().toUpperCase();
            return true;
        }
        imprimirMensaje("[Vehículo] Modelo inválido: no puede estar vacío.");
        return false;
    }

    /** Valida (2000 hasta el año actual) y asigna el año de fabricación. */
    public boolean setAnioFabricacion(int anioFabricacion) {
        int anioActual = Year.now().getValue();
        if (anioFabricacion >= 2000 && anioFabricacion <= anioActual) {
            this.anioFabricacion = anioFabricacion;
            return true;
        }
        imprimirMensaje("[Vehículo] Año inválido: " + anioFabricacion
                + ". Debe estar entre 2000 y " + anioActual + ".");
        return false;
    }

    /** Valida y asigna la condición (solo D, A o M; acepta minúsculas). */
    public boolean setCondicion(char condicion) {
        char c = Character.toUpperCase(condicion);
        if (c == 'D' || c == 'A' || c == 'M') {
            this.condicion = c;
            return true;
        }
        imprimirMensaje("[Vehículo] Condición inválida: '" + condicion + "'. Use D, A o M.");
        return false;
    }

    // ------------------------ Comportamiento ------------------------
    /**
     * Asignar vehículo en mantención: deja la condición en M.
     * No es posible si el vehículo está actualmente arrendado (A).
     */
    public boolean asignarMantencion() {
        if (condicion == 'A') {
            imprimirMensaje("[Vehículo] No se puede asignar a mantención: " + patente + " está arrendado.");
            return false;
        }
        setCondicion('M');
        imprimirMensaje("[Vehículo] El vehículo " + patente + " fue asignado a mantención.");
        return true;
    }

    /** Único punto de salida de mensajes al usuario en esta clase. */
    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String toString() {
        return "Vehículo [patente=" + patente + ", marca=" + marca + ", modelo=" + modelo
                + ", año=" + anioFabricacion + ", condición=" + condicion + "]";
    }
}
