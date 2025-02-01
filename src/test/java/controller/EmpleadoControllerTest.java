package controller;

// Importaciones necesarias
import model.Cargo; // Enum que representa los distintos cargos de los empleados
import model.Empleado; // Clase que define las propiedades de un empleado
import model.EmpleadoDAO; // Clase DAO para acceder y manipular los datos de empleados
import view.EmpleadoView; // Vista para mostrar información sobre empleados
import org.junit.Before; // Anotación para ejecutar configuraciones previas a las pruebas
import org.junit.Test; // Anotación para definir métodos de prueba
import static org.junit.Assert.*; // Métodos para realizar aserciones en las pruebas

import java.util.List; // Para manejar listas de empleados

public class EmpleadoControllerTest {

    private EmpleadoDAO empleadoDAO; // DAO para manejar operaciones con empleados
    private EmpleadoView empleadoView; // Vista para la interfaz de usuario
    private EmpleadoController empleadoController; // Controlador para conectar modelo y vista

    // Método de configuración que se ejecuta antes de cada prueba
    @Before  //Anotación para ejecutar configuraciones previas a las pruebas
    public void setUp() {
        // Se inicializan el modelo (EmpleadoDAO), la vista y el controlador
        empleadoDAO = new EmpleadoDAO();
        empleadoView = new EmpleadoView();
        empleadoController = new EmpleadoController(empleadoDAO, empleadoView);

        // Mensaje indicando que se completó la configuración inicial
        System.out.println("Configuración inicial del controlador de empleados.");
    }

    // Prueba para agregar un empleado
    @Test   //Anotación para definir métodos de prueba
    public void testAgregarEmpleado() {
        System.out.println("Iniciando prueba: Agregar Empleado");

        // Se crea un nuevo empleado y se agrega a la base de datos
        Empleado empleado = new Empleado(123, "Pedro Gómez", Cargo.CAJERO);
        empleadoDAO.agregarEmpleado(empleado);
        System.out.println("Empleado agregado: " + empleado);

        // Se obtiene la lista de empleados para verificar si el nuevo empleado fue agregado
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
        boolean encontrado = empleados.stream().anyMatch(e -> e.getId() == 123); // Busca al empleado por ID

        // Aserción para confirmar que el empleado fue agregado correctamente
        assertTrue("El empleado debería haber sido agregado", encontrado);
        System.out.println("Prueba de agregar empleado finalizada con éxito.");
    }

    // Prueba para eliminar un empleado
    @Test
    public void testEliminarEmpleado() {
        System.out.println("Iniciando prueba: Eliminar Empleado");

        // Se elimina un empleado por su ID
        empleadoDAO.eliminarEmpleado(23);
        System.out.println("Empleado con ID 23 eliminado.");

        // Se obtiene la lista de empleados para verificar que el empleado fue eliminado
        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
        boolean encontrado = empleados.stream().anyMatch(e -> e.getId() == 23); // Verifica si el ID 23 aún existe

        // Aserción para confirmar que el empleado fue eliminado correctamente
        assertFalse("El empleado debería haber sido eliminado", encontrado);
        System.out.println("Prueba de eliminar empleado finalizada con éxito.");
    }

    @Test // Prueba para consultar empleados por su cargo
    public void testConsultarEmpleadosPorCargo() {
        System.out.println("Iniciando prueba: Consultar Empleados por Cargo");

        // Se consulta la lista de empleados con el cargo de "CAJERO"
        List<Empleado> empleados = empleadoDAO.obtenerEmpleadosPorCargo("CAJERO");
        System.out.println("Empleados con cargo CAJERO: " + empleados);

        // Verifica que todos los empleados obtenidos tienen el cargo correcto
        boolean todosCajeros = empleados.stream().allMatch(e -> e.getCargo() == Cargo.CAJERO);

        // Aserción para confirmar que todos los empleados son cajeros
        assertTrue("Búsqueda de todos los empleados cajeros", todosCajeros);
        System.out.println("Prueba de consultar empleados por cargo finalizada con éxito.");
    }

    @Test // Prueba para modificar un empleado
    public void testModificarEmpleado() {
        System.out.println("Iniciando prueba: Modificar Empleado");

        // Se intenta modificar el cargo de un empleado por su ID
        boolean modificado = empleadoDAO.modificarEmpleado(123, Cargo.SUPERVISOR);
        System.out.println("Empleado con ID 1 modificado a cargo: SUPERVISOR");

        // Se obtiene el empleado modificado para verificar el cambio
        Empleado empleado = empleadoDAO.obtenerTodosLosEmpleados().stream()
                .filter(e -> e.getId() == 123) // Busca al empleado con ID 1
                .findFirst() // Obtiene el primer empleado que coincida
                .orElse(null); // Devuelve null si no encuentra el empleado

        // Aserciones para verificar que el empleado existe y que el cargo fue modificado correctamente
        assertNotNull("El empleado debería existir", empleado);
        assertEquals("El cargo debería haber sido modificado a SUPERVISOR", Cargo.SUPERVISOR, empleado.getCargo());
        System.out.println("Prueba de modificar empleado finalizada con éxito.");
    }
}
