package datos;

public class CuentaCorriente extends CuentaBancaria implements MostrarInfo {

    private int saldo;

    public CuentaCorriente(int saldo, int numeroCuenta, String rutCliente) {
        super(numeroCuenta, rutCliente);
        this.saldo = saldo;
    }

    @Override
    public void girar(int montoGiroCorriente) {
        if (montoGiroCorriente <= 0) {
            throw new IllegalArgumentException("CUENTA CORRIENTE: Monto inválido: debe ser mayor que cero.");
        }
        if (montoGiroCorriente > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente en su Cuenta Corriente.");
        }
        saldo -= montoGiroCorriente;
    }

    @Override
    public void depositar(int montoDepositoCorriente) {
        if (montoDepositoCorriente <= 0) {
            throw new IllegalArgumentException("CUENTA CORRIENTE: Monto inválido: debe ser mayor que cero.");
        }
        saldo += montoDepositoCorriente;
    }
    
    @Override
    public int getSaldo() {
    return saldo;
    }

    @Override
    public void mostrarDatos(){
          System.out.println("\n>> Cuenta Corriente:");
            System.out.println("Número de Cuenta: " + getNumeroCuenta());
            System.out.println("Saldo: $" + getSaldo());
        
    }
    
}
