package datos;

public class Cliente {

// Datos privados para encapsular 
    private String rut;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String domicilio;
    private String comuna;
    private int telefono;
    //private CuentaBancaria cuenta;

    // * 
    private CuentaCorriente cuentaCorriente;
    private CuentaAhorro cuentaAhorro;
    private CuentaCredito cuentaCredito;

// Constructor
    public Cliente(String rut, String nombre, String apellidoMaterno, String apellidoPaterno, String domicilio, String comuna, int telefono, CuentaBancaria cuenta) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.telefono = telefono;
        if (cuenta instanceof CuentaCorriente) {
            this.cuentaCorriente = (CuentaCorriente) cuenta;
        } else if (cuenta instanceof CuentaAhorro) {
            this.cuentaAhorro = (CuentaAhorro) cuenta;
        } else if (cuenta instanceof CuentaCredito) {
            this.cuentaCredito = (CuentaCredito) cuenta;
        }
    }

// Getters y Setters
    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getComuna() {
        return comuna;
    }

    public int getTelefono() {
        return telefono;
    }

    public CuentaBancaria getCuentaCorriente() {
        return cuentaCorriente;
    }

    public CuentaAhorro getCuentaAhorro() {
        return cuentaAhorro;
    }

    public CuentaCredito getCuentaCredito() {
        return cuentaCredito;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public void setCuentaAhorro(CuentaAhorro cuentaAhorro) {
        this.cuentaAhorro = cuentaAhorro;
    }

    public void setCuentaCredito(CuentaCredito cuentaCredito) {
        this.cuentaCredito = cuentaCredito;
    }

    public void mostrarDatos() {
        System.out.println("--------------------------------");
        System.out.println("RUT: " + rut);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido paterno: " + apellidoPaterno);
        System.out.println("Apellido materno: " + apellidoMaterno);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Comuna: " + comuna);
        System.out.println("Teléfono: " + telefono);

        if (cuentaCorriente != null) {
            System.out.println("\n>> Cuenta Corriente:");
            System.out.println("Número de Cuenta: " + cuentaCorriente.getNumeroCuenta());
            System.out.println("Saldo: $" + cuentaCorriente.getSaldo());
        }

        if (cuentaAhorro != null) {
            System.out.println("\n>> Cuenta de Ahorro:");
            System.out.println("Número de cuenta: " + cuentaAhorro.getNumeroCuenta());
            System.out.println("Saldo: $" + cuentaAhorro.getSaldo());
        }

        if (cuentaCredito != null) {
            System.out.println("\n>> Cuenta de Crédito:");
            System.out.println("Número de cuenta: " + cuentaCredito.getNumeroCuenta());
            System.out.println("Saldo: $" + cuentaCredito.getSaldo());
        }
        System.out.println("--------------------------------");
    }
}
