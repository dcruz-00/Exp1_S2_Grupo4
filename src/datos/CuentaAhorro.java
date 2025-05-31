package datos;

public class CuentaAhorro extends CuentaBancaria implements MostrarInfo {

    private int saldo;

    public CuentaAhorro(int saldo, int numeroCuenta, String rutCliente) {
        super(numeroCuenta, rutCliente);
        this.saldo = saldo;
    }


    @Override
    public void girar(int montoGiroAhorro) {
        if (montoGiroAhorro <= 0) {
            throw new IllegalArgumentException("CUENTA DE AHORRO: Monto inválido: debe ser mayor que cero.");
        }
        if (montoGiroAhorro > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente en su Cuenta de Ahorro.");
        }
        saldo -= montoGiroAhorro;
    }

    @Override
    public void depositar(int montoDepositoAhorro) {
        if (montoDepositoAhorro <= 0) {
            throw new IllegalArgumentException("CUENTA DE AHORRO: Monto inválido: debe ser mayor que cero.");
        }
        saldo += montoDepositoAhorro;
    }

    @Override
    public int getSaldo() {
        return saldo;
    }
    
    
    @Override
    public void mostrarDatos(){
     
        
    }
       
}
