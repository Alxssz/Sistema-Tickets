package InterfazGrafica.Clase_01;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AgregarPrioridadController {

    @FXML
    private TextField prioridadField;

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    private ventana1Controller ventanaPrincipalController;  // Controlador de la ventana principal

    // Método para setear el controlador de la ventana principal
    public void setVentanaPrincipalController(ventana1Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    @FXML
    public void guardarPrioridad() {
        String prioridad = prioridadField.getText().trim();  // Obtener el texto de la prioridad

        if (!prioridad.isEmpty()) {
            ventanaPrincipalController.agregarPrioridadALista(prioridad);

            cerrarVentana();
        } else {
            System.out.println("Por favor, ingrese una prioridad válida.");
        }
    }

    @FXML
    public void cancelar() {
        cerrarVentana();  // Cerrar la ventana sin guardar
    }

    // Método para cerrar la ventana
    private void cerrarVentana() {
        Stage stage = (Stage) guardarBtn.getScene().getWindow();
        stage.close();
    }
}
