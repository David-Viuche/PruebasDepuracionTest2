// Importación de las clases necesarias para la implementación del patrón MVC
import controller.EmpleadoController; // Importa el controlador que gestiona la lógica de la aplicación
import model.EmpleadoDAO; // Importa el modelo que maneja la gestión de datos (base de datos o almacenamiento)
import view.EmpleadoView; // Importa la vista encargada de la interacción con el usuario

// Clase principal que ejecuta la aplicación
public class Main {
    public static void main(String[] args) {
        // Creación de una instancia del modelo (M) que maneja la lógica de datos
        EmpleadoDAO modelo = new EmpleadoDAO(); 

        // Creación de una instancia de la vista (V) que muestra la interfaz al usuario
        EmpleadoView vista = new EmpleadoView(); 

        // Creación de una instancia del controlador (C) que gestiona la interacción entre el modelo y la vista
        EmpleadoController controlador = new EmpleadoController(modelo, vista);

        // Inicia la aplicación, permitiendo la interacción del usuario con la vista
        controlador.iniciar();
    }
}
