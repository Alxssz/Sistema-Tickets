package InterfazGrafica.Clase_01;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class HistorialCambiosController {

    @FXML
    private ListView<String> listaHistorial;

    // Método para cargar el historial en el ListView
    public void cargarHistorial(List<String> historial) {
        listaHistorial.getItems().setAll(historial);  // Mostrar todos los cambios en el ListView
    }
}