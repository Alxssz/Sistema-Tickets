package InterfazGrafica.Clase_01;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class reestablecerController {

    //VARIBLEs
    @FXML
    private Button aceptarBtn;
    @FXML
    private Button cancelarBtn;
    private ventana1Controller ventanaPrincipalController;

    //CONTROLADOR
    public void setVentanaPrincipalController(ventana1Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    //METODOS
    @FXML
    public void aceptar() throws IOException {
        if (ventanaPrincipalController != null) {
            try {
                ventanaPrincipalController.reestablecerConfiguracion();
            } catch (Exception ex) {
                Logger.getLogger(reestablecerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cerrarVentana();
    }

    @FXML
    public void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) aceptarBtn.getScene().getWindow();
        stage.close();
    }
}
