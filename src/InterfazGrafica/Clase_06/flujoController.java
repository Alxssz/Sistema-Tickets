package InterfazGrafica.Clase_06;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.C5_EstadoTicket;
import sistematickets.C6_FlujoTickets;

/**
 * FXML Controller class
 *
 * @author Familia Boror Ruiz
 */
public class flujoController implements Initializable {

    @FXML
    private TextField nombreFlujo;

    @FXML
    private ListView<C5_EstadoTicket> estadosList;

    @FXML
    private ListView<C5_EstadoTicket> estadosInvolucrados;

    @FXML
    private ListView<String> listaTransiciones;

    @FXML
    private ListView<String> reglasTransicion;

    @FXML
    private CheckBox checkNotificacion;

    @FXML
    private Button btnGuardar;

    private TableView<C6_FlujoTickets> tablaFlujos;
    private ventana6Controller ventanaPrincipalController;

    private C6_FlujoTickets flujoAEditar;

    private ObservableList<C5_EstadoTicket> listaEstados = FXCollections.observableArrayList(new C5_EstadoTicket("Pendiente", "Estado inicial del ticket", false),
            new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false),
            new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false),
            new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true)
    );

    private ObservableList<C5_EstadoTicket> involucrados = FXCollections.observableArrayList();

    String usuario = "Eddy Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    //INICIALIZADO
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estadosList.setItems(listaEstados);
        estadosInvolucrados.setItems(involucrados);
    }

    @FXML
    private void agregarEstadoAFlujo() {
        C5_EstadoTicket estadoSeleccionado = estadosList.getSelectionModel().getSelectedItem();
        if (estadoSeleccionado != null && !involucrados.contains(estadoSeleccionado)) {
            involucrados.add(estadoSeleccionado);
        }
    }

    @FXML
    private void eliminarEstadoDelFlujo() {
        C5_EstadoTicket estadoSeleccionado = estadosInvolucrados.getSelectionModel().getSelectedItem();
        if (estadoSeleccionado != null) {
            involucrados.remove(estadoSeleccionado);
        }
    }

    @FXML
    private void guardarFlujoTrabajo() {
        String nombre = nombreFlujo.getText().trim();
        boolean notificar = checkNotificacion.isSelected();

        if (!nombre.isEmpty()) {
            if (flujoAEditar != null) {
                flujoAEditar.setNombre(nombre);
                flujoAEditar.setEstados(new ArrayList<>(involucrados));
                flujoAEditar.setEnviarNotificacion(notificar);
                if (tablaFlujos != null) {
                    tablaFlujos.refresh();
                }
                // Guardar el historial de edición
                String mensaje = fechaHora + " - Se edito el flujo " + nombre + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                C6_FlujoTickets nuevoFlujo = new C6_FlujoTickets(nombre, involucrados, notificar);
                ventanaPrincipalController.agregarFlujoALista(nuevoFlujo);
            }
            cerrarVentana();
        } else {
            System.out.println("Por favor, complete el campo nombre.");
        }
    }

    @FXML
    public void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    public void setVentanaPrincipalController(ventana6Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    public void setFlujoParaEditar(C6_FlujoTickets flujo) {
        this.flujoAEditar = flujo;
        // Cargo los datos del flujo en los controles
        nombreFlujo.setText(flujo.getNombre());
        checkNotificacion.setSelected(flujo.isEnviarNotificacion());
        involucrados.clear();
        involucrados.addAll(flujo.getEstados()); // actualizar lista observable ligada a estadosInvolucrados
        estadosInvolucrados.refresh();
    }
}
