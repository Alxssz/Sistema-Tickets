package InterfazGrafica.Clase_01;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class reestablecerController {

    @FXML
    private Button aceptarBtn;

    @FXML
    private Button cancelarBtn;

    private ventana1Controller ventanaPrincipalController;  // Controlador de la ventana principal

    public void setVentanaPrincipalController(ventana1Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    @FXML
    public void aceptar() throws IOException {
        if (ventanaPrincipalController != null) {
            ventanaPrincipalController.reestablecerConfiguracion();
        }
        cerrarVentana();
    }

    @FXML
    public void cancelar() {
        // Cerrar la ventana de cancelar sin cambiar la escena
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) aceptarBtn.getScene().getWindow();
        stage.close();
    }
}
