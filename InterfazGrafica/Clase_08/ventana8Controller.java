package InterfazGrafica.Clase_08;

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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Familia Boror Ruiz
 */
public class ventana8Controller implements Initializable {

    @FXML
    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void ventanaUsuario(ActionEvent event) throws IOException {
        // Cargar la ventana de menú
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_08/usuario.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual y cerrarla
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        // Crear nueva ventana para el menú
        Stage stageMenu = new Stage();
        stageMenu.setTitle("Usuario");
        stageMenu.setScene(new Scene(root));
        stageMenu.show();
    }

    @FXML
    private void ventanaTecnico(ActionEvent event) throws IOException {
        // Cargar la ventana de menú
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_08/tecnico.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual y cerrarla
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        // Crear nueva ventana para el menú
        Stage stageMenu = new Stage();
        stageMenu.setTitle("Tecnico");
        stageMenu.setScene(new Scene(root));
        stageMenu.show();
    }

    @FXML
    private void ventanaAdmi(ActionEvent event) throws IOException {
        // Cargar la ventana de menú
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_08/ventana8.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual y cerrarla
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.close();

        // Crear nueva ventana para el menú
        Stage stageMenu = new Stage();
        stageMenu.setTitle("Administrador");
        stageMenu.setScene(new Scene(root));
        stageMenu.show();
    }
}
