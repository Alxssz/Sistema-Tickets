package InterfazGrafica.Clase_01;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class cancelarController {

//VARIABLES
    @FXML
    private Button aceptarBtn;

    @FXML
    private Button cancelarBtn;

    private ventana1Controller ventanaPrincipalController;

    // CONTROLADOR PRINCIPAL
    public void setVentanaPrincipalController(ventana1Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    //METODOS
    @FXML
    public void aceptarCancelo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
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
