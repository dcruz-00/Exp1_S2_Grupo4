package app;

import datos.Cliente;
import datos.CuentaAhorro;
import datos.CuentaBancaria;
import datos.CuentaCorriente;
import datos.CuentaCredito;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final List<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Main app = new Main();
        app.ejecutarMenu();
    }

    private void ejecutarMenu() {
        int opcionMenu = 0;
        System.out.println("..:: Bienvenido al Banco: BANK BOSTON ::..");

        do {
            mostrarMenu();

            if (scanner.hasNextInt()) {
                opcionMenu = scanner.nextInt();
                scanner.nextLine();

                switch (opcionMenu) {
                    case 1 ->
                        registrarCliente();
                    case 2 ->
                        mostrarDatosCliente();
                    case 3 ->
                        realizarDeposito();
                    case 4 ->
                        realizarGiro();
                    case 5 ->
                        consultarSaldo();
                    case 6 ->
                        System.out.println(">> Saliendo del sistema...");
                    default ->
                        System.out.println("Opcion no valida. Intente nuevamente.");
                }
            } else {
                System.out.println("!! Entrada invalida. Ingrese un numero.");
                scanner.nextLine();
            }

        } while (opcionMenu != 6);
    }

    private void mostrarMenu() {
        System.out.println("\n === MENU PRINCIPAL ===");
        System.out.println("--------------------------------");
        System.out.println("1) Registrar cliente");
        System.out.println("2) Ver datos de cliente");
        System.out.println("3) Depositar");
        System.out.println("4) Girar");
        System.out.println("5) Consultar saldo");
        System.out.println("6) Salir");
        System.out.println("--------------------------------");
        System.out.println("> Seleccione una opcion: ");
    }

    private void registrarCliente() {
        System.out.println("--------------------------------");
        System.out.println("== REGISTRAR CLIENTE ==");

        String rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna;
        int telefono, numeroCuenta = 0;

        do {
            System.out.println("\nPor favor ingrese su RUT considerando puntos y guión (X.XXX.XXX-X)): ");
            rut = scanner.nextLine();
            if (rut.length() < 11 || rut.length() > 12) {
                System.out.println("RUT invalido. Debe tener entre 11 y 12 caracteres (X.XXX.XXX-X).");
            }
        } while (rut.length() < 11 || rut.length() > 12);

        nombre = validarYCapitalizar("\nIngrese su nombre:");
        apellidoPaterno = validarYCapitalizar("\nIngrese su apellido paterno:");
        apellidoMaterno = validarYCapitalizar("\nIngrese su apellido materno:");

        System.out.println("\nIngrese su domicilio:");
        domicilio = scanner.nextLine();
        if (!domicilio.isEmpty()) {
            domicilio = domicilio.substring(0, 1).toUpperCase() + domicilio.substring(1).toLowerCase();
        }

        comuna = validarYCapitalizar("\nIngrese su comuna:");

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
        System.out.println("\nIngrese su número de cuenta para su CUENTA CORRIENTE (9 dígitos):");
        int numeroCuentaCorriente = Integer.parseInt(scanner.nextLine());

        System.out.println("\nIngrese su número de cuenta para su CUENTA DE AHORRO (9 dígitos):");
        int numeroCuentaAhorro = Integer.parseInt(scanner.nextLine());

        System.out.println("\nIngrese su número de cuenta para su CUENTA DE CRÉDITO (9 dígitos)");
        int numeroCuentaCredito = Integer.parseInt(scanner.nextLine());

        Cliente nuevoCliente = new Cliente(rut, nombre, apellidoMaterno, apellidoPaterno, domicilio, comuna, telefono, cuenta);

        // * Asignar las cuentas bancarias
        nuevoCliente.setCuentaCorriente(new CuentaCorriente(numeroCuentaCorriente, 0));
        nuevoCliente.setCuentaAhorro(new CuentaAhorro(numeroCuentaAhorro, 0));
        nuevoCliente.setCuentaCredito(new CuentaCredito(numeroCuentaCredito, 0));

        // * Agregar cliente a la lista
        clientes.add(nuevoCliente);

        System.out.println("\nCliente registrado exitosamente!");
    }

    private void mostrarDatosCliente() {
        System.out.println("Por favor ingrese su RUT:");
        String rut = scanner.nextLine();
        Cliente cliente = buscarClientePorRut(rut);

        if (cliente != null) {
            cliente.mostrarDatos();
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void realizarDeposito() {
        int montoDCuentaCorriente;
        int montoDCuentaAhorro;
        int montoDCuentaCredito;

        System.out.println("Por favor ingrese su RUT:");
        String rut = scanner.nextLine();
        Cliente cliente = buscarClientePorRut(rut);

        if (cliente != null) {
            System.out.println("--------------------------------");
            System.out.println("== DEPOSITO ==");
            System.out.println("\nSeleccione la cuenta a la que desea depositar:");
            System.out.println("1) Cuenta Corriente");
            System.out.println("2) Cuenta Ahorro");
            System.out.println("3) Cuenta Crédito");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    if (cliente.getCuentaCorriente() != null) {
                        System.out.println("\nSaldo actual: $" + cliente.getCuentaCorriente().getSaldo());
                        System.out.println("Ingrese el monto a depositar:");
                        montoDCuentaCorriente = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaCorriente().depositar(montoDCuentaCorriente);
                            System.out.println("Déposito existoso. Nuevo saldo: $" + cliente.getCuentaCorriente().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una CUENTA CORRIENTE registrada.");
                    }
                    break;

                case "2":
                    if (cliente.getCuentaAhorro() != null) {
                        System.out.println("\nSaldo actual: $" + cliente.getCuentaAhorro().getSaldo());
                        System.out.println("Ingrese el monto a depositar:");
                        montoDCuentaAhorro = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaAhorro().depositar(montoDCuentaAhorro);
                            System.out.println("Déposito exitoso. Nuevo saldo: $" + cliente.getCuentaAhorro().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una CUENTA DE AHORRO registrada.");
                    }
                    break;

                case "3":
                    if (cliente.getCuentaCredito() != null) {
                        System.out.println("Saldo actual: $" + cliente.getCuentaCredito().getSaldo());
                        System.out.println("Ingrese el monto a depositar:");
                        montoDCuentaCredito = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaCredito().depositar(montoDCuentaCredito);
                            System.out.println("Depósito exitoso. Nuevo saldo: $" + cliente.getCuentaCredito().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una CUENTA DE CRÉDITO registrada.");
                    }
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void realizarGiro() {
        int montoGCuentaCorriente;
        int montoGCuentaAhorro;
        int montoGCuentaCredito;

        System.out.println("Por favor ingrese su RUT:");
        String rut = scanner.nextLine();
        Cliente cliente = buscarClientePorRut(rut);

        if (cliente != null) {
            System.out.println("--------------------------------");
            System.out.println("== GIRO ==");
            System.out.println("Seleccione la cuenta desde la que desea girar dinero:");
            System.out.println("1) Cuenta Corriente");
            System.out.println("2) Cuenta Ahorro");
            System.out.println("3) Cuenta Crédito");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    if (cliente.getCuentaCorriente() != null) {
                        System.out.println("Saldo actual: $" + cliente.getCuentaCorriente().getSaldo());
                        System.out.println("Ingrese el monto a girar:");
                        montoGCuentaCorriente = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaCorriente().girar(montoGCuentaCorriente);
                            System.out.println("Giro exitoso. Nuevo saldo: $" + cliente.getCuentaCorriente().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una cuenta corriente.");
                    }
                    break;

                case "2":
                    if (cliente.getCuentaAhorro() != null) {
                        System.out.println("Saldo actual: $" + cliente.getCuentaAhorro().getSaldo());
                        System.out.println("Ingrese el monto a girar:");
                        montoGCuentaAhorro = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaAhorro().girar(montoGCuentaAhorro);
                            System.out.println("Giro exitoso. Nuevo saldo: $" + cliente.getCuentaAhorro().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una cuenta de ahorro.");
                    }
                    break;

                case "3":
                    if (cliente.getCuentaCredito() != null) {
                        System.out.println("Saldo actual: $" + cliente.getCuentaCredito().getSaldo());
                        System.out.println("Ingrese el monto a girar:");
                        montoGCuentaCredito = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            cliente.getCuentaCredito().girar(montoGCuentaCredito);
                            System.out.println("Giro exitoso. Nuevo saldo: $" + cliente.getCuentaCredito().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una cuenta de crédito.");
                    }
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void consultarSaldo() {
        System.out.println("Por favor ingrese su RUT:");
        String rut = scanner.nextLine();
        Cliente cliente = buscarClientePorRut(rut);

        if (cliente != null) {
            System.out.println("--------------------------------");
            System.out.println("== CONSULTAR SALDO ==");
            System.out.println("Seleccione la cuenta que desea consultar:");
            System.out.println("1) Cuenta Corriente");
            System.out.println("2) Cuenta Ahorro");
            System.out.println("3) Cuenta Crédito");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    if (cliente.getCuentaCorriente() != null) {
                        System.out.println("Saldo de CUENTA CORRIENTE: $" + cliente.getCuentaCorriente().getSaldo());
                    } else {
                        System.out.println("El cliente no tiene una CUENTA CORRIENTE registrada.");
                    }
                    break;
                    
                case "2":
                if (cliente.getCuentaAhorro() != null) {
                    System.out.println("Saldo de CUENTA DE AHORRO: $" + cliente.getCuentaAhorro().getSaldo());
                } else {
                    System.out.println("El cliente no tiene una CUENTA DE AHORRO registrada.");
                }
                break;
                
                case "3":
                    if (cliente.getCuentaCredito() != null) {
                        System.out.println("Saldo de CUENTA DE CRÉDITO: $" + cliente.getCuentaCredito().getSaldo());
                    } else {
                        System.out.println("El cliente no tiene una CUENTA DE CRÉDITO registrada.");
                    }
                    break;
                    
                default:
                    System.out.println("Opción Inválida.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private Cliente buscarClientePorRut(String rut) {
        for (Cliente c : clientes) {
            if (c.getRut().equalsIgnoreCase(rut)) {
                return c;
            }
        }
        return null;
    }

    private String validarYCapitalizar(String mensaje) {
        String texto;
        while (true) {
            System.out.println(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ ]+")) {
                texto = texto.toLowerCase();
                return texto.substring(0, 1).toUpperCase() + texto.substring(1);
            } else {
                System.out.println("Entrada invalida. Solo se permiten letras. Intente nuevamente.");
            }
        }
    }
}
