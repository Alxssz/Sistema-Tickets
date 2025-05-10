package InterfazGrafica.Clase_07;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
public class ventana7Controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
        public void volverMenu(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }
    
       public void registrarTicket (ActionEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_07/registrarTicket.fxml"));
        Parent root = loader.load();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);

        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Agregar Nuevo Ticket");
        nuevaVentana.show();

    }

}