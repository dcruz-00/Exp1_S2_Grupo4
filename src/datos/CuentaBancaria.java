package datos;

public abstract class CuentaBancaria {

    private int numeroCuenta;
    private String rutCliente;

    public CuentaBancaria(int numeroCuenta, String rutCliente) {
        this.numeroCuenta = numeroCuenta;
        this.rutCliente = rutCliente;
    }

    public CuentaBancaria(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public CuentaBancaria(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;        
    }
    
    public String getRutCliente () {
        return rutCliente;
    }

    public abstract void depositar(int monto);
    public abstract void girar(int monto);
    public abstract int getSaldo();
}
