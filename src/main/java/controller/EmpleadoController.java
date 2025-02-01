// Paquete que agrupa las clases del controlador
package controller;

// Importación de clases necesarias del modelo y la vista
import model.Cargo;
import model.Empleado;
import model.EmpleadoDAO;
import view.EmpleadoView;

import java.util.List;
import java.util.Scanner;

/**
 * Controlador de empleados en el patrón MVC.
 * Se encarga de manejar la lógica de interacción entre el modelo (EmpleadoDAO) y la vista (EmpleadoView).
 */
public class EmpleadoController {
    // Atributos privados para la gestión de empleados y la interacción con el usuario
    private final EmpleadoDAO empleadoDAO; // Modelo: Acceso a datos de empleados
    private final EmpleadoView empleadoView; // Vista: Interfaz con el usuario
    private final Scanner scanner; // Scanner para capturar la entrada del usuario

    /**
     * Constructor que inicializa el controlador con el modelo y la vista.
     * @param empleadoDAO Objeto de acceso a datos de empleados.
     * @param empleadoView Objeto de la vista para la interacción con el usuario.
     */
    public EmpleadoController(EmpleadoDAO empleadoDAO, EmpleadoView empleadoView) {
        this.empleadoDAO = empleadoDAO;
        this.empleadoView = empleadoView;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Método principal que inicia el flujo de la aplicación, mostrando el menú e interactuando con el usuario.
     */
    public void iniciar() {
        int opcion;
        do {
            empleadoView.mostrarMenu(); // Muestra el menú al usuario
            opcion = obtenerOpcionUsuario(); // Obtiene la opción elegida
            procesarOpcion(opcion); // Ejecuta la opción seleccionada
        } while (opcion != 6); // Repite hasta que el usuario decida salir

        scanner.close(); // Cierra el scanner al finalizar
    }

    /**
     * Obtiene la opción ingresada por el usuario y valida que esté dentro del rango permitido.
     * @return La opción seleccionada por el usuario.
     */
    private int obtenerOpcionUsuario() {
        int opcion;
        do {
            opcion = empleadoView.obtenerEntradaEntero("Seleccione una opción: ");
            if (opcion < 1 || opcion > 6) {
                empleadoView.mostrarMensaje("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion < 1 || opcion > 6); // Repite hasta obtener una opción válida
        return opcion;
    }

    /**
     * Procesa la opción seleccionada por el usuario y ejecuta la acción correspondiente.
     * @param opcion Opción seleccionada del menú.
     */
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> consultarEmpleadosPorCargo();
            case 2 -> agregarNuevoEmpleado();
            case 3 -> eliminarEmpleado();
            case 4 -> modificarEmpleado();
            case 5 -> mostrarTodosLosEmpleados();
            case 6 -> empleadoView.mostrarMensaje("Saliendo...");
            default -> empleadoView.mostrarMensaje("Opción no válida. Por favor, intente de nuevo.");
        }
    }

    /**
     * Consulta empleados filtrados por cargo e imprime los resultados en la vista.
     */
    private void consultarEmpleadosPorCargo() {
        String cargo = empleadoView.obtenerEntradaTexto("Ingrese el cargo de los empleados a consultar: ");
        List<Empleado> empleados = empleadoDAO.obtenerEmpleadosPorCargo(cargo);
        empleadoView.mostrarEmpleados(empleados);
    }

    /**
     * Agrega un nuevo empleado al sistema con validaciones de ID y datos.
     */
    private void agregarNuevoEmpleado() {
        int id;

        // Solicitar un ID válido que no esté repetido
        do {
            id = empleadoView.obtenerEntradaEntero("Ingrese el ID del nuevo empleado: ");
            if (empleadoDAO.existeId(id)) {
                empleadoView.mostrarMensaje("El ID ingresado ya existe. Por favor, intente con un ID diferente.");
            } else {
                break;
            }
        } while (true); // Repetir hasta obtener un ID único

        // Solicitar y validar el nombre
        String nombre = empleadoView.obtenerEntradaTexto("Ingrese el nombre del nuevo empleado: ");

        // Solicitar y validar el cargo
        Cargo cargo = empleadoView.obtenerCargoValido();

        // Si el nombre no está vacío, agregar el empleado
        if (!nombre.isEmpty()) {
            empleadoDAO.agregarEmpleado(new Empleado(id, nombre, cargo));
            empleadoView.mostrarMensaje("Empleado agregado correctamente.");
        } else {
            empleadoView.mostrarMensaje("Error: El nombre no puede estar vacío.");
        }
    }

    /**
     * Elimina un empleado del sistema si el ID existe.
     */
    private void eliminarEmpleado() {
        int id = empleadoView.obtenerEntradaEntero("Ingrese el ID del empleado a eliminar: ");
        if (empleadoDAO.eliminarEmpleado(id)) {
            empleadoView.mostrarMensaje("Empleado eliminado correctamente.");
        } else {
            empleadoView.mostrarMensaje("No se encontró un empleado con ese ID.");
        }
    }

    /**
     * Modifica el cargo de un empleado existente si el ID es válido.
     */
    private void modificarEmpleado() {
        mostrarTodosLosEmpleados(); // Muestra la lista actual de empleados

        int id = empleadoView.obtenerEntradaEntero("Ingrese el ID del empleado a modificar: ");
        if (empleadoDAO.existeId(id)) { // Verifica si el ID existe
            Cargo nuevoCargo = empleadoView.obtenerCargoValido(); // Obtiene el nuevo cargo
            if (empleadoDAO.modificarEmpleado(id, nuevoCargo)) {
                empleadoView.mostrarMensaje("Empleado modificado correctamente.");
            } else {
                empleadoView.mostrarMensaje("No se pudo modificar el empleado.");
            }
        } else {
            empleadoView.mostrarMensaje("No se encontró un empleado con ese ID.");
        }
    }

    /**
     * Muestra todos los empleados registrados en el sistema.
     */
    private void mostrarTodosLosEmpleados() {
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
        empleadoView.mostrarTodosLosEmpleados(empleados);
    }
}
