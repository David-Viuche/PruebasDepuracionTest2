package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class EmpleadoDAOTest {

    private EmpleadoDAO empleadoDAO;

    @Before
    public void setUp() {
        empleadoDAO = new EmpleadoDAO();
        System.out.println("Configuración inicial completada.");
    }

    @Test
    public void testAgregarEmpleado() {
        System.out.println("Iniciando prueba: Agregar Empleado");
        Empleado empleado = new Empleado(1, "Juan Perez", Cargo.CAJERO);
        empleadoDAO.agregarEmpleado(empleado);

        List<Empleado> empleados = empleadoDAO.obtenerTodosLosEmpleados();
        boolean encontrado = empleados.stream().anyMatch(e -> e.getId() == 1);

        assertTrue("El empleado debería haber sido agregado", encontrado);
        System.out.println("Prueba de agregar empleado finalizada con éxito.");
    }

    @Test
    public void testEliminarEmpleado() {
        System.out.println("Iniciando prueba: Eliminar Empleado");
        Empleado empleado = new Empleado(2, "Maria Lopez", Cargo.GERENTE);
        empleadoDAO.agregarEmpleado(empleado);
        boolean eliminado = empleadoDAO.eliminarEmpleado(2);

        assertTrue("El empleado debería haber sido eliminado", eliminado);
        System.out.println("Prueba de eliminar empleado finalizada con éxito.");
    }

    @Test
    public void testConsultarEmpleadosPorCargo() {
        System.out.println("Iniciando prueba: Consultar Empleados por Cargo");
        Empleado empleado1 = new Empleado(3, "Luis Gómez", Cargo.CAJERO);
        Empleado empleado2 = new Empleado(4, "Ana Torres", Cargo.CAJERO);
        empleadoDAO.agregarEmpleado(empleado1);
        empleadoDAO.agregarEmpleado(empleado2);

        List<Empleado> empleados = empleadoDAO.obtenerEmpleadosPorCargo("CAJERO");
        boolean todosCajeros = empleados.stream().allMatch(e -> e.getCargo() == Cargo.CAJERO);

        assertTrue("Todos los empleados deben ser cajeros", todosCajeros);
        System.out.println("Prueba de consultar empleados por cargo finalizada con éxito.");
    }

    @Test
    public void testModificarEmpleado() {
        System.out.println("Iniciando prueba: Modificar Empleado");
        Empleado empleado = new Empleado(5, "Carlos Ramírez", Cargo.CAJERO);
        empleadoDAO.agregarEmpleado(empleado);

        boolean modificado = empleadoDAO.modificarEmpleado(5, Cargo.SUPERVISOR);
        Empleado empleadoModificado = empleadoDAO.obtenerTodosLosEmpleados().stream()
                .filter(e -> e.getId() == 5)
                .findFirst()
                .orElse(null);

        assertNotNull("El empleado debería existir", empleadoModificado);
        assertEquals("El cargo debería haber sido modificado a SUPERVISOR", Cargo.SUPERVISOR, empleadoModificado.getCargo());
        System.out.println("Prueba de modificar empleado finalizada con éxito.");
    }

    @Test
    public void testExisteId() {
        System.out.println("Iniciando prueba: Verificar existencia de ID");
        Empleado empleado = new Empleado(6, "Sofía Herrera", Cargo.CAJERO);
        empleadoDAO.agregarEmpleado(empleado);

        boolean existe = empleadoDAO.existeId(6);
        assertTrue("El ID debería existir", existe);
        System.out.println("Prueba de existencia de ID finalizada con éxito.");
    }
}
