package view;

import model.Cargo;
import model.Empleado;
import java.util.List;
import java.util.Scanner;

public class EmpleadoView {
    private final Scanner scanner;

    public EmpleadoView() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n--- Menú de Empleados ---");
        System.out.println("1. Consultar empleados por cargo");
        System.out.println("2. Agregar nuevo empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Modificar empleado");
        System.out.println("5. Mostrar todos los empleados");
        System.out.println("6. Salir");
    }

    public String obtenerEntradaTexto(String mensaje) {
        String entrada;
        while (true) {
            System.out.print(mensaje);
            entrada = scanner.nextLine().trim();  // Usamos trim para eliminar espacios al inicio y al final
            if (!entrada.isEmpty()) {
                return entrada;
            } else {
                mostrarMensaje("¡La entrada no puede estar vacía! Por favor, ingrese un valor válido.");
            }
        }
    }

    public int obtenerEntradaEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int numero = Integer.parseInt(scanner.nextLine().trim());  // Eliminamos espacios
                return numero;
            } catch (NumberFormatException e) {
                mostrarMensaje("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

public void mostrarEmpleados(List<Empleado> empleados) {
    if (empleados.isEmpty()) {
        mostrarMensaje("No se encontraron empleados con ese cargo.");
    } else {
        mostrarMensaje("Empleados encontrados:");
        empleados.forEach(e -> System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Cargo: " + e.getCargo()));
    }
}


    public void mostrarTodosLosEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            mostrarMensaje("No hay empleados registrados.");
        } else {
            mostrarMensaje("Lista de empleados:");
            empleados.forEach(e -> System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Cargo: " + e.getCargo()));
        }
    }

    // Nueva función para ingresar un cargo válido
    public Cargo obtenerCargoValido() {
        while (true) {
            String cargoStr = obtenerEntradaTexto("Ingrese el cargo (Cajero, Gerente, Supervisor): ");
            if (Cargo.isValidCargo(cargoStr)) {
                return Cargo.valueOf(cargoStr.toUpperCase());
            } else {
                mostrarMensaje("Cargo inválido. Por favor ingrese un cargo válido.");
            }
        }
    }
}
