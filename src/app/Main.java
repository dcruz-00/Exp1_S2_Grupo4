package app;

import datos.Cliente;
import datos.CuentaAhorro;
import datos.CuentaBancaria;
import datos.CuentaCorriente;
import datos.CuentaCredito;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern; //añadí regex pattern

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
        int telefono; // * Eliminé numeroCuenta porque era innecesario

        Pattern PATRON_RUT = Pattern.compile("^[0-9]{1,2}\\.[0-9]{3}\\.[0-9]{3}-[0-9Kk]$");

        do {
            System.out.println("\nPor favor ingrese su RUT considerando puntos y guion (xX.XXX.XXX-X)): ");
            rut = scanner.nextLine();
            if (rut.length() < 11 || rut.length() > 12) {
                System.out.println("RUT invalido. Debe tener entre 11 y 12 caracteres (xX.XXX.XXX-X).");
            }

            if (!PATRON_RUT.matcher(rut).matches()) {
                System.out.println("Formato inválido. Por favor intente otra vez.");
            }

            if (verificarRut(rut)) {
                System.out.println("El rut que ingresó ya se encuentra registrado. Por favor intente otra vez.");
            }

        } while ((rut.length() < 11 || rut.length() > 12) || (!PATRON_RUT.matcher(rut).matches()) || (verificarRut(rut)));

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

        //moví CuentaBancaria cuenta = null; a línea 136 por orden | :3
        //borré el int opcionMenublablabla no iba acá | :D        
        int numeroCuentaCorriente = 0;
        int numeroCuentaAhorro = 0;
        int numeroCuentaCredito = 0;

        // * Las inicialicé como null
        CuentaCorriente cuentaCorriente = null;
        CuentaAhorro cuentaAhorro = null;
        CuentaCredito cuentaCredito = null;

        // * Creamos cliente sin cuentas inicialmente
        Cliente nuevoCliente = new Cliente(rut, nombre, apellidoMaterno, apellidoPaterno, domicilio, comuna, telefono, null);

        // * Variable para conitnuar el regitro
        boolean continuarRegistro = true;

        while (continuarRegistro) {
            System.out.println("\nPor favor, elija el tipo de cuenta que desea: ");
            System.out.println("1. Cuenta Corriente");
            System.out.println("2. Cuenta Ahorro");
            System.out.println("3. Cuenta Credito");
            System.out.println("4. Salir");

            int opcionMenuCuenta = scanner.nextInt();
            scanner.nextLine();

            while (opcionMenuCuenta < 1 || opcionMenuCuenta > 4) {
                System.out.println("Opción Inválida. Ingrese un número entre 1 y 4:");

                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada no válida. Ingrese un número entre 1 y 4:");
                    scanner.next();
                }
                opcionMenuCuenta = scanner.nextInt();
                scanner.nextLine();
            }

            String opcion;
            CuentaBancaria cuenta = null;

            switch (opcionMenuCuenta) {

                case 1 -> {
                    do {
                        System.out.println("\nIngrese su numero de Cuenta de Corriente. (9 digitos): ");
                        opcion = scanner.nextLine();
                    } while (opcion.length() != 9 || !opcion.chars().allMatch(Character::isDigit));
                    numeroCuentaCorriente = Integer.parseInt(opcion);
                    cuentaCorriente = new CuentaCorriente(numeroCuentaCorriente, 0);
                    nuevoCliente.setCuentaCorriente(cuentaCorriente); // * Agregamos la cuenta a cliente
                }
                case 2 -> {
                    do {
                        System.out.println("\nIngrese su numero de Cuenta de Ahorro. (9 digitos): ");
                        opcion = scanner.nextLine();
                    } while (opcion.length() != 9 || !opcion.chars().allMatch(Character::isDigit));
                    numeroCuentaAhorro = Integer.parseInt(opcion);
                    cuentaAhorro = new CuentaAhorro(numeroCuentaAhorro, 0);
                    nuevoCliente.setCuentaAhorro(cuentaAhorro); // * Agregado
                }
                case 3 -> {
                    do {
                        System.out.println("\nIngrese su numero de Cuenta de Credito. (9 digitos): ");
                        opcion = scanner.nextLine();
                    } while (opcion.length() != 9 || !opcion.chars().allMatch(Character::isDigit));
                    numeroCuentaCredito = Integer.parseInt(opcion);
                    cuentaCredito = new CuentaCredito(numeroCuentaCredito, 0);
                    nuevoCliente.setCuentaCredito(cuentaCredito); // * Agregadoo
                }
                case 4 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default ->
                    System.out.println("Opcion invalida. Intente nuevamente.");
            }

            // * Pregunta para continuar registrando cuentas
            String continuar;
            do {
            System.out.println("\n¿Desea registrar otro tipo de cuenta? (s/n)");
            continuar = scanner.nextLine().trim().toLowerCase();
            
            if (!continuar.equals("s") && !continuar.equals("n")) {
                System.out.println("Entrada inválida. Por favor ingrese 's' para SÍ o 'n' para NO");   
            }
            } while (!continuar.equals("s") && !continuar.equals("n"));
            
            if (continuar.equals("n")) {
                continuarRegistro = false;
            }
        }

        // * Agregar cliente a la lista solo después de registrar al menos una cuenta
        if (nuevoCliente.getCuentaCorriente() != null || nuevoCliente.getCuentaAhorro() != null || nuevoCliente.getCuentaCredito() != null) {
            clientes.add(nuevoCliente);
            System.out.println("\nCliente registrado exitosamente con sus cuentas.");
        } else {
            System.out.println("No se registró ninguna cuenta. Cliente no agregado.");
        }

        clientes.add(nuevoCliente);

        System.out.println("\nCliente ha sido registrado exitosamente!");

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
            System.out.println("3) Cuenta Credito");
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
                        System.out.println("El cliente no tiene una cuenta corriente registrada.");
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
                        System.out.println("El cliente no tiene una cuenta de ahorro registrada.");
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
                            System.out.println("Deposito exitoso. Nuevo saldo: $" + cliente.getCuentaCredito().getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    } else {
                        System.out.println("El cliente no tiene una cuenta de credito registrada.");
                    }
                    break;

                default:
                    System.out.println("Opcion invalida.");
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
                        System.out.println("El cliente no tiene una cuenta de credito.");
                    }
                    break;
                default:
                    System.out.println("Opcion invalida.");
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
            System.out.println("3) Cuenta Credito");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    if (cliente.getCuentaCorriente() != null) {
                        System.out.println("Saldo de Cuenta Corriente: $" + cliente.getCuentaCorriente().getSaldo());
                    } else {
                        System.out.println("El cliente no tiene una cuenta corriente registrada.");
                    }
                    break;

                case "2":
                    if (cliente.getCuentaAhorro() != null) {
                        System.out.println("Saldo de Cuenta de Ahorro: $" + cliente.getCuentaAhorro().getSaldo());
                    } else {
                        System.out.println("El cliente no tiene una cuenta de ahorro registrada.");
                    }
                    break;

                case "3":
                    if (cliente.getCuentaCredito() != null) {
                        System.out.println("Saldo de Cuenta de Credito: $" + cliente.getCuentaCredito().getSaldo());
                    } else {
                        System.out.println("El cliente no tiene una cuenta de credito registrada.");
                    }
                    break;

                default:
                    System.out.println("Opcion Invalida.");
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

    private boolean verificarRut(String rut) {
        for (Cliente cliente : clientes) {
            if (cliente.getRut().equalsIgnoreCase(rut)) {
                return true;
            }
        }
        return false;
    }
}
