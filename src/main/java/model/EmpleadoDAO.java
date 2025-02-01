package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    // URL, usuario y contraseña de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/empleados_db"; // URL de la base de datos
    private static final String USER = "root"; // Nombre de usuario de MySQL
    private static final String PASSWORD = ""; // Contraseña de MySQL

    // Método privado para obtener la conexión con la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Devuelve una conexión a la base de datos
    }

    // Método para obtener empleados según su cargo
    public List<Empleado> obtenerEmpleadosPorCargo(String cargo) {
        List<Empleado> resultado = new ArrayList<>();
        String query = "SELECT * FROM empleados WHERE cargo = ?"; // Consulta SQL para obtener empleados por cargo

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cargo.toUpperCase()); // Se asegura que el cargo se pase en mayúsculas

            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            while (rs.next()) {
                // Obtiene los resultados de la base de datos
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Cargo cargoEnum = Cargo.valueOf(rs.getString("cargo")); // Convierte el cargo a un valor del enum Cargo
                resultado.add(new Empleado(id, nombre, cargoEnum)); // Agrega el empleado a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
        return resultado; // Devuelve la lista de empleados
    }

    // Método para agregar un nuevo empleado a la base de datos
    public void agregarEmpleado(Empleado empleado) {
        String query = "INSERT INTO empleados (id, nombre, cargo) VALUES (?, ?, ?)"; // Consulta SQL para insertar un empleado

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            // Establece los valores del empleado a insertar
            stmt.setInt(1, empleado.getId());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getCargo().name()); // Se usa el nombre del enum para almacenar el cargo

            stmt.executeUpdate(); // Ejecuta la consulta de inserción
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
    }

    // Método para verificar si un empleado con un ID específico existe en la base de datos
    public boolean existeId(int id) {
        String query = "SELECT COUNT(*) FROM empleados WHERE id = ?"; // Consulta SQL para contar empleados con el ID dado

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID a buscar

            ResultSet rs = stmt.executeQuery(); // Ejecuta la consulta
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, el ID existe
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
        return false; // Si no se encuentra el ID, devuelve false
    }

    // Método para eliminar un empleado de la base de datos
    public boolean eliminarEmpleado(int id) {
        String query = "DELETE FROM empleados WHERE id = ?"; // Consulta SQL para eliminar un empleado

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id); // Establece el ID del empleado a eliminar

            int rowsAffected = stmt.executeUpdate(); // Ejecuta la consulta de eliminación
            return rowsAffected > 0; // Devuelve true si se eliminó al menos un empleado
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
        return false; // Devuelve false si no se eliminó ningún empleado
    }

    // Método para modificar el cargo de un empleado
    public boolean modificarEmpleado(int id, Cargo nuevoCargo) {
        String query = "UPDATE empleados SET cargo = ? WHERE id = ?"; // Consulta SQL para actualizar el cargo de un empleado

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevoCargo.name()); // Establece el nuevo cargo
            stmt.setInt(2, id); // Establece el ID del empleado a modificar

            int rowsAffected = stmt.executeUpdate(); // Ejecuta la consulta de actualización
            return rowsAffected > 0; // Devuelve true si se actualizó al menos un empleado
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
        return false; // Devuelve false si no se actualizó ningún empleado
    }

    // Método para obtener todos los empleados de la base de datos
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM empleados"; // Consulta SQL para obtener todos los empleados

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Obtiene los resultados de la base de datos
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Cargo cargo = Cargo.valueOf(rs.getString("cargo")); // Convierte el cargo a un valor del enum Cargo
                empleados.add(new Empleado(id, nombre, cargo)); // Agrega el empleado a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Captura y muestra los errores de SQL
        }
        return empleados; // Devuelve la lista de todos los empleados
    }
}
