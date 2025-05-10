package InterfazGrafica.Login;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenaField;

    @FXML
    private Text mensajeError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesaria
    }

    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = usuarioField.getText().trim();
        String contrasena = contrasenaField.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mensajeError.setVisible(true);
            return;
        }

        // Cargar el menú principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        Stage stageMenu = new Stage();
        stageMenu.setTitle("Menu Principal");
        stageMenu.setScene(new Scene(root));
        stageMenu.show();
    }
}
