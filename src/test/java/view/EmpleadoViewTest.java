package view;

import model.Cargo;
import model.Empleado;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class EmpleadoViewTest {

    private EmpleadoView empleadoView;

    @Before
    public void setUp() {
        empleadoView = new EmpleadoView();
    }

    @Test
    public void testMostrarMensaje() {
        System.out.println("Probando mostrarMensaje...");
        empleadoView.mostrarMensaje("Este es un mensaje de prueba");
    }

    @Test
    public void testMostrarEmpleados() {
        System.out.println("Probando mostrarEmpleados...");
        List<Empleado> empleados = Arrays.asList(
            new Empleado(1, "Juan Pérez", Cargo.CAJERO),
            new Empleado(2, "María López", Cargo.GERENTE)
        );
        empleadoView.mostrarEmpleados(empleados);
    }

    @Test
    public void testMostrarTodosLosEmpleados() {
        System.out.println("Probando mostrarTodosLosEmpleados...");
        List<Empleado> empleados = Arrays.asList(
            new Empleado(1, "Carlos Díaz", Cargo.SUPERVISOR),
            new Empleado(2, "Ana García", Cargo.CAJERO)
        );
        empleadoView.mostrarTodosLosEmpleados(empleados);
    }
}
