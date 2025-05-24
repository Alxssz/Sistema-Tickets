package InterfazGrafica.Clase_05;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.C5_EstadoTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Familia Boror Ruiz
 */
public class ventana5Controller implements Initializable {

    @FXML
    private TableView<C5_EstadoTicket> listaViewEstados;
    private ObservableList<C5_EstadoTicket> listaEstados = FXCollections.observableArrayList();

    @FXML
    private TableColumn<C5_EstadoTicket, String> colNombre;

    @FXML
    private TableColumn<C5_EstadoTicket, String> colDescripcion;
    @FXML
    private TableColumn<C5_EstadoTicket, String> colEsFinal;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEsFinal.setCellValueFactory(cellData -> {
            boolean esFinal = cellData.getValue().esFinal();
            String texto = esFinal ? "Si es final" : "No es final";
            return new ReadOnlyStringWrapper(texto);
        });

        listaEstados.add(new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false));
        listaEstados.add(new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false));
        listaEstados.add(new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true));
        listaViewEstados.setItems(listaEstados);
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

    public void NuevoEstadoTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_05/Estado.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de rol
        estadoController controlador = loader.getController();
        controlador.cargarListaEstados(listaEstados);
        controlador.setVentanaPrincipalController(this);

        // Mostrar la nueva ventana para agregar un rol
        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Agregar Estado");
        nuevaVentana.show();
    }

    public void editarEstado(ActionEvent event) throws IOException {
        C5_EstadoTicket estadoSeleccionado = listaViewEstados.getSelectionModel().getSelectedItem();

        if (estadoSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_05/Estado.fxml"));
            Parent root = loader.load();

            estadoController controlador = loader.getController();
            controlador.setVentanaPrincipalController(this);
            controlador.setEstadoAEditar(estadoSeleccionado);
            controlador.cargarListaEstados(listaEstados);
            controlador.setTablaEstados(listaViewEstados);

            Scene escena = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Editar Estado");
            ventana.show();
        } else {
            System.out.println("Seleccione un estado para editar.");
        }
    }

    public void agregarEstadoALista(C5_EstadoTicket estado) {
        listaEstados.add(0, estado); // Agregar el rol a la lista observable
        listaViewEstados.setItems(listaEstados); // Actualizar el TableView con los nuevos roles
        historialDeCambios.add(fechaHora + " - Se agrego " + estado + " como nuevo estado por " + usuario);
    }

    public void eliminarEstado() {
        C5_EstadoTicket seleccionado = listaViewEstados.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaEstados.remove(seleccionado);
            listaViewEstados.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el estado " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un estado primero.");
        }
    }

    public ObservableList<C5_EstadoTicket> getListaEstados() {
        return listaEstados;
    }

    //HISTORIAL
    public void verHistorial(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/Historial_Cambios.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de historial de cambios
        HistorialCambiosController historialController = loader.getController();

        // Pasar el historial al controlador
        historialController.cargarHistorial(historialDeCambios);

        // Cambiar la escena sin cerrar la ventana
        Stage nuevaVentana = new Stage();
        Scene nuevaEscena = new Scene(root);
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Historial De Cambios");
        nuevaVentana.show();
    }

    public void agregarHistorial(String mensaje) {
        historialDeCambios.add(mensaje);
    }

}
