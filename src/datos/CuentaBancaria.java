package datos;

public abstract class CuentaBancaria {

    private int numeroCuenta;
    //falta 1 atributo

    public CuentaBancaria(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public abstract void depositar(int monto);
    public abstract void girar(int monto);
    public abstract int getSaldo();
}
