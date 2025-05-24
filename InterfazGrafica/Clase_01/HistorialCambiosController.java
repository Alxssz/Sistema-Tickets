package InterfazGrafica.Clase_01;

import java.util.Collections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class HistorialCambiosController {

    @FXML
    private ListView<String> listaHistorial;

    // METODO MOSTRAR HISTORIAL
    public void cargarHistorial(List<String> historial) {
        Collections.reverse(historial);
        listaHistorial.getItems().setAll(historial); // Mostrar todos los cambios en el ListView
    }
}
