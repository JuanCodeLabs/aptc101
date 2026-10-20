/**
 * Clase Cliente: representa a un cliente de la empresa Car-REnt.
 * Los atributos son privados (encapsulamiento) y se modifican solo mediante
 * mutadores (set) que validan los datos antes de asignarlos.
 */
public class Cliente {

    // ------------------------- Atributos -------------------------
    private String cedula;     // largo 10, penúltimo carácter '-', último dígito 0-9 o k
    private String nombre;     // no puede estar vacío
    private boolean vigente;   // true = vigente, false = no vigente

    // ------------------------ Constructor ------------------------
    /**
     * Crea un cliente. Los datos pasan por los mutadores para validarse.
     * Un cliente nuevo siempre comienza como vigente (true).
     */
    public Cliente(String cedula, String nombre) {
        setCedula(cedula);
        setNombre(nombre);
        this.vigente = true;
    }

    // ------------------------- Accesores -------------------------
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isVigente() {
        return vigente;
    }

    // ------------------- Mutadores con validación -------------------
    /** Valida y asigna la cédula. Retorna true si fue asignada. */
    public boolean setCedula(String cedula) {
        if (validarCedula(cedula)) {
            this.cedula = cedula;
            return true;
        }
        imprimirMensaje("[Cliente] Cédula inválida: '" + cedula
                + "'. Debe tener largo 10, guion en el penúltimo lugar y dígito verificador 0-9 o k.");
        return false;
    }

    /** Valida y asigna el nombre. Retorna true si fue asignado. */
    public boolean setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
            return true;
        }
        imprimirMensaje("[Cliente] Nombre inválido: no puede estar vacío.");
        return false;
    }

    /** Cambia la vigencia (true o false). Se usa internamente por deshabilitar(). */
    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    // ------------------------ Validaciones ------------------------
    /** Retorna true si la cédula cumple el formato: 8 dígitos + '-' + (0-9 o k/K). */
    public boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(cedula.charAt(i))) {
                return false;
            }
        }
        if (cedula.charAt(8) != '-') {
            return false;
        }
        char verificador = cedula.charAt(9);
        return Character.isDigit(verificador) || verificador == 'k' || verificador == 'K';
    }

    // ------------------------ Comportamiento ------------------------
    /**
     * Deshabilitar cliente: lo deja no vigente para que no pueda arrendar.
     * Retorna false si ya estaba no vigente.
     */
    public boolean deshabilitar() {
        if (!vigente) {
            imprimirMensaje("[Cliente] El cliente " + nombre + " ya estaba no vigente.");
            return false;
        }
        setVigente(false);
        imprimirMensaje("[Cliente] El cliente " + nombre + " fue deshabilitado.");
        return true;
    }

    /** Único punto de salida de mensajes al usuario en esta clase. */
    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String toString() {
        return "Cliente [cédula=" + cedula + ", nombre=" + nombre + ", vigente=" + vigente + "]";
    }
}
