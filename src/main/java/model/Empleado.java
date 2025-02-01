package model;

public class Empleado {

    private int id;
    private String nombre;
    private Cargo cargo; // Cambio aquí: ahora es Cargo en lugar de String

    public Empleado(int id, String nombre, Cargo cargo) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Empleado{id=" + id + ", nombre='" + nombre + "', cargo=" + cargo + "}";
    }

}
/*
toString() sobrescrito: 
Este método devuelve una cadena que representa el objeto Empleado de forma más legible, 
incluyendo los atributos id, nombre y cargo

Resultado esperado: 
Cuando se imprima un objeto de tipo Empleado, se verá algo como:
Empleados con cargo CAJERO: [Empleado{id=1, nombre='Juan Pérez', cargo=CAJERO}, Empleado{id=2, nombre='Ana Gómez', cargo=CAJERO}]

en lugar de la referencia de memoria    
 */
