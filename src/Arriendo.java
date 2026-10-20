import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase Arriendo: representa el arriendo de un vehículo por parte de un cliente.
 * Relaciones: AGREGACIÓN con Vehiculo y con Cliente (existen antes y después del arriendo).
 */
public class Arriendo {

    // ------------------------- Atributos -------------------------
    private int numero;                // número de arriendo (mayor que 0)
    private Vehiculo vehiculo;         // agregación
    private Cliente cliente;           // agregación
    private LocalDate fechaArriendo;
    private int diasArriendo;          // mayor que 1 y menor que 10
    private int precioDiario;          // lo entrega el usuario al ingresar el arriendo

    // ------------------------ Constructor ------------------------
    /** Crea un arriendo. Vehículo y cliente son objetos ya existentes (agregación). */
    public Arriendo(int numero, Vehiculo vehiculo, Cliente cliente,
                    LocalDate fechaArriendo, int diasArriendo) {
        setNumero(numero);
        setVehiculo(vehiculo);
        setCliente(cliente);
        setFechaArriendo(fechaArriendo);
        setDiasArriendo(diasArriendo);
        this.precioDiario = 0; // se define en ingresarArriendo()
    }

    // ------------------------- Accesores -------------------------
    public int getNumero() {
        return numero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaArriendo() {
        return fechaArriendo;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public int getPrecioDiario() {
        return precioDiario;
    }

    // ------------------- Mutadores con validación -------------------
    public boolean setNumero(int numero) {
        if (numero > 0) {
            this.numero = numero;
            return true;
        }
        imprimirMensaje("[Arriendo] Número de arriendo inválido: debe ser mayor que 0.");
        return false;
    }

    public boolean setVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            this.vehiculo = vehiculo;
            return true;
        }
        imprimirMensaje("[Arriendo] El vehículo no puede ser nulo.");
        return false;
    }

    public boolean setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
            return true;
        }
        imprimirMensaje("[Arriendo] El cliente no puede ser nulo.");
        return false;
    }

    public boolean setFechaArriendo(LocalDate fechaArriendo) {
        if (fechaArriendo != null) {
            this.fechaArriendo = fechaArriendo;
            return true;
        }
        imprimirMensaje("[Arriendo] La fecha de arriendo no puede ser nula.");
        return false;
    }

    /** Días de arriendo: mayor que 1 y menor que 10 (es decir, de 2 a 9). */
    public boolean setDiasArriendo(int diasArriendo) {
        if (diasArriendo > 1 && diasArriendo < 10) {
            this.diasArriendo = diasArriendo;
            return true;
        }
        imprimirMensaje("[Arriendo] Días inválidos: " + diasArriendo + ". Deben ser mayor que 1 y menor que 10.");
        return false;
    }

    public boolean setPrecioDiario(int precioDiario) {
        if (precioDiario > 0) {
            this.precioDiario = precioDiario;
            return true;
        }
        imprimirMensaje("[Arriendo] Precio diario inválido: debe ser mayor que 0.");
        return false;
    }

    // ------------------------ Comportamiento ------------------------
    /**
     * Evaluar arriendo: valida que el cliente esté vigente y que el vehículo
     * tenga condición D (disponible). Retorna true si es posible arrendar.
     */
    public boolean evaluarArriendo() {
        if (cliente == null || vehiculo == null) {
            imprimirMensaje("[Arriendo] Faltan datos: se necesita un cliente y un vehículo.");
            return false;
        }
        if (!cliente.isVigente()) {
            imprimirMensaje("[Arriendo] El cliente " + cliente.getNombre() + " no está vigente.");
            return false;
        }
        if (vehiculo.getCondicion() != 'D') {
            imprimirMensaje("[Arriendo] El vehículo " + vehiculo.getPatente()
                    + " no está disponible (condición " + vehiculo.getCondicion() + ").");
            return false;
        }
        return true;
    }

    /**
     * Ingresar arriendo: recibe el precio diario, evalúa el arriendo y, si es
     * posible, deja el vehículo en condición A. Retorna true si se ingresó.
     */
    public boolean ingresarArriendo(int precioDiario) {
        if (!setPrecioDiario(precioDiario)) {
            return false;
        }
        if (!evaluarArriendo()) {
            this.precioDiario = 0; // el arriendo no se guarda
            imprimirMensaje("[Arriendo] El arriendo N° " + numero + " NO fue ingresado.");
            return false;
        }
        vehiculo.setCondicion('A');
        imprimirMensaje("[Arriendo] El arriendo N° " + numero + " fue ingresado correctamente.");
        return true;
    }

    /** Obtener monto a pagar: días de arriendo por precio diario. */
    public int obtenerMontoPagar() {
        return diasArriendo * precioDiario;
    }

    /** Generar ticket de arriendo con el formato solicitado. */
    public void generarTicket() {
        if (precioDiario <= 0) {
            imprimirMensaje("[Arriendo] No se puede generar el ticket: el arriendo no ha sido ingresado.");
            return;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String linea = "--------------------------------------------------------------";
        String ticket = linea + "\n"
                + "            T I C K E T   ARRIENDO   D E   V E H Í C U L O\n"
                + linea + "\n"
                + "NÚMERO ARRIENDO : " + numero + "\n"
                + "VEHÍCULO        : " + vehiculo.getPatente() + " " + vehiculo.getMarca()
                + " " + vehiculo.getModelo() + "\n"
                + "PRECIO DIARIO   : $" + precioDiario + "\n\n"
                + String.format("%-12s %-28s %-8s %s", "FECHA", "CLIENTE", "DÍAS", "A PAGAR") + "\n"
                + linea + "\n"
                + String.format("%-12s %-28s %-8s %s",
                        fechaArriendo.format(formato),
                        cliente.getCedula() + "/" + cliente.getNombre(),
                        diasArriendo + " días",
                        "$" + obtenerMontoPagar()) + "\n"
                + linea + "\n\n"
                + "                                    ___________________\n"
                + "                                       FIRMA CLIENTE\n"
                + linea;
        imprimirMensaje(ticket);
    }

    /** Único punto de salida de mensajes al usuario en esta clase. */
    public void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public String toString() {
        return "Arriendo [número=" + numero + ", vehículo=" + (vehiculo == null ? null : vehiculo.getPatente())
                + ", cliente=" + (cliente == null ? null : cliente.getCedula())
                + ", fecha=" + fechaArriendo + ", días=" + diasArriendo
                + ", precioDiario=" + precioDiario + "]";
    }
}
