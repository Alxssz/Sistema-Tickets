package Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización (si es necesaria)
    }

    @FXML
    private void iniciarSesion (ActionEvent event) throws IOException {
        // Cargar la ventana de menú
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual y cerrarla
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        // Crear nueva ventana para el menú
        Stage stageMenu = new Stage();
        stageMenu.setTitle("Menu Principal");
        stageMenu.setScene(new Scene(root));
        stageMenu.show();
    }
}
 