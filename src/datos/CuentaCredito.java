package datos;

public class CuentaCredito extends CuentaBancaria implements Operaciones {

    private int saldo;

    public CuentaCredito(int saldo, int numeroCuenta, String rutCliente) {
        super(numeroCuenta, rutCliente);
        this.saldo = saldo;
    }

    @Override
    public void girar(int montoGiroCredito) {
        if (montoGiroCredito <= 0) {
            throw new IllegalArgumentException("CUENTA CREDITO: Monto inválido: debe ser mayor que cero.");
        }
        if (montoGiroCredito > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente en su Cuenta Credito.");
        }
        saldo -= montoGiroCredito;
    }

    @Override
    public void depositar(int montoDepositoCredito) {
        if (montoDepositoCredito <= 0) {
            throw new IllegalArgumentException("CUENTA CREDITO: Monto inválido: debe ser mayor que cero.");
        }
        saldo += montoDepositoCredito;
    }

    @Override
    public int getSaldo() {
        return saldo;
    }
}
