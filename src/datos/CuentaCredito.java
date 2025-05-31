package datos;

public class CuentaCredito extends CuentaBancaria implements Operaciones {

    private int saldo;

    public CuentaCredito(int numeroCuenta, int saldoInicial) {
        super(numeroCuenta);
        this.saldo = saldoInicial;
    }

    @Override
    public void girar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto inválido: debe ser mayor que cero.");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
        saldo -= monto;
    }

    @Override
    public void depositar(int monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("Monto inválido: debe ser mayor que cero.");
        }
        saldo += monto;
    }

    @Override
    public int getSaldo() {
        return saldo;
    }
}
