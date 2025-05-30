/*
package servicio;

import datos.Cliente;
import datos.CuentaAhorro;
import datos.CuentaBancaria;
import datos.CuentaCorriente;
import datos.CuentaCredito;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServicioCliente {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente registrarCliente(Scanner scanner) {
        String rut;
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        String domicilio;
        String comuna;
        int telefono;
        int numeroCuenta = 0;

        System.out.println("--------------------------------");
        System.out.println("== REGISTRAR CLIENTE ==");
        System.out.println("\nAqui empieza el inicio del registro!");
        System.out.println("Porfavor ingrese los siguientes datos:");

        do {
            System.out.println("\nPor favor ingrese su RUT considerando puntos y guión (X.XXX.XXX-X)):");
            rut = scanner.nextLine();
            if (rut.length() < 11 || rut.length() > 12) {
                System.out.println("RUT invalido. Debe tener entre 11 y 12 caracteres (X.XXX.XXX-X).");
            }
        } while (rut.length() < 11 || rut.length() > 12);

        // La validación llama al método auxiliar (validarYCapitalizar). 
        nombre = validarYCapitalizar("\nIngrese su nombre:", scanner);

        apellidoPaterno = validarYCapitalizar("\nIngrese su apellido paterno:", scanner);

        apellidoMaterno = validarYCapitalizar("\nIngrese su apellido materno:", scanner);

        System.out.println("\nIngrese su domicilio");
        domicilio = scanner.nextLine();
        if (!domicilio.isEmpty()) {
            domicilio = domicilio.substring(0, 1).toUpperCase() + domicilio.substring(1).toLowerCase();
        }

        comuna = validarYCapitalizar("\nIngrese su comuna:", scanner);

        while (true) {
            System.out.println("\nIngrese su telefono (8 digitos):");
            String entrada = scanner.nextLine();

            if (entrada.length() == 8 && entrada.chars().allMatch(Character::isDigit)) {
                telefono = Integer.parseInt(entrada);
                break;
            } else {
                System.out.println("ERROR: El telefono debe incluir 8 digitos.");
            }
        }

        CuentaBancaria cuenta = null;
        String opcionCuenta;
        while (true) {
            System.out.println("\nSeleccione el tipo de cuenta:");
            System.out.println("1) Cuenta Corriente");
            System.out.println("2) Cuenta Ahorro");
            System.out.println("3) Cuenta Crédito");
            opcionCuenta = scanner.nextLine();

            if (opcionCuenta.equals("1")) {
                cuenta = new CuentaCorriente(numeroCuenta, 0);
                System.out.println("\n>> Ha seleccionado la CUENTA CORRIENTE");
                break;
            } else if (opcionCuenta.equals("2")) {
                cuenta = new CuentaAhorro(numeroCuenta, 0);
                System.out.println("\n>> Ha seleccionado la CUENTA DE AHORRO");
                break;
            } else if (opcionCuenta.equals("3")) {
                cuenta = new CuentaCredito(numeroCuenta, 0);
                System.out.println("\n>> Ha seleccionado la CUENTA DE CRÉDITO");
                break;
            } else {
                System.out.println("Opcioón Inválida. Intente nuevamente.");
            }

        }

        while (true) {
            System.out.println("\nIngrese su numero de cuenta (9 digitos):");
            String entrada = scanner.nextLine();

            if (entrada.length() == 9 && entrada.chars().allMatch(Character::isDigit)) {
                numeroCuenta = Integer.parseInt(entrada);
                break;
            } else {
                System.out.println("ERROR: El numero de cuenta debe incluir 9 digitos.");
            }
        }

        Cliente nuevoCliente = new Cliente(rut, nombre, apellidoMaterno, apellidoPaterno, domicilio, comuna, telefono, cuenta);
        clientes.add(nuevoCliente);

        System.out.println("\nCliente registrado exitosamente!");
        return nuevoCliente;
    }

    // Método para realizar depositos
    public void realizarDeposito(Cliente cliente, Scanner scanner) {
        int montoDeposito;

        System.out.println("--------------------------------");
        System.out.println("== DEPOSITO ==");
        System.out.println("\nSu saldo actual es: $" + cliente.getCuenta().getSaldo()); // Agregué esta linea para mostrar el saldo antes del deposito
        System.out.println("\n>> Ingrese el monto a depositar:");
        montoDeposito = scanner.nextInt();
        scanner.nextLine();

        try {
            // Se realiza el depósito a través del método de la CuentaCorriente
            cliente.getCuenta().depositar(montoDeposito);
            System.out.println("\nDeposito exitoso! Su nuevo saldo es: $" + cliente.getCuenta().getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Método para realizar giros desde la cuenta corriente del cliente
    public void realizarGiro(Cliente cliente, Scanner scanner) {
        int montoGiro;

        System.out.println("--------------------------------");
        System.out.println("== GIRO ==");
        System.out.println("\nSu saldo actual es: $" + cliente.getCuenta().getSaldo());
        System.out.println("\n>> Ingrese el monto a girar:");
        montoGiro = scanner.nextInt();
        scanner.nextLine();

        try {
            // Se realiza el giro a través del método de la CuentaCorriente
            cliente.getCuenta().girar(montoGiro);
            System.out.println("\nGiro exitoso! Su nuevo saldo es: $" + cliente.getCuenta().getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Método auxiliar para validar solo letras y devuelve con la primera letra mayuscula
    public static String validarYCapitalizar(String mensaje, Scanner scanner) {
        String texto;
        while (true) {
            System.out.println(mensaje);
            texto = scanner.nextLine().trim();

            //Validación
            if (texto.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+")) {
                // Convertir a minuscula y capitalizar primera letra
                texto = texto.toLowerCase();
                texto = texto.substring(0, 1).toUpperCase() + texto.substring(1);
                // Devuelve el texto validado y formateado
                return texto;
            } else {
                System.out.println("Entrada invalida. Solo se permiten letras. Intente nuevamente.");
            }
        }
    }

    public Cliente buscarClientePorRut(String rut) {
        for (Cliente c : clientes) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

}
*/