package datos;

public abstract class CuentaBancaria {

    final private int numeroCuenta;
    final private String rutCliente;

    public CuentaBancaria(int numeroCuenta, String rutCliente) {
        this.numeroCuenta = numeroCuenta;
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
