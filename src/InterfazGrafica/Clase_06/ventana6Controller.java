package InterfazGrafica.Clase_06;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.C5_EstadoTicket;
import sistematickets.C6_FlujoTickets;

/**
 * FXML Controller class
 *
 * @author Familia Boror Ruiz
 */
public class ventana6Controller implements Initializable {

    @FXML
    private TableView<C6_FlujoTickets> listViewFlujos;
    private ObservableList<C6_FlujoTickets> listaFlujos = FXCollections.observableArrayList();

    @FXML
    private TableColumn<C6_FlujoTickets, String> colNombre;

    @FXML
    private TableColumn<C6_FlujoTickets, String> colEstados;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar columnas
        colNombre.setCellValueFactory(data
                -> new ReadOnlyStringWrapper(data.getValue().getNombre())
        );

        colEstados.setCellValueFactory(data -> {
            // Convertir la lista de estados a una cadena con nombres separados por coma
            String estadosConcatenados = data.getValue().getEstados().stream()
                    .map(estado -> estado.getNombre())
                    .collect(Collectors.joining(", "));
            return new ReadOnlyStringWrapper(estadosConcatenados);
        });

        // Asignar la lista al TableView
        listViewFlujos.setItems(listaFlujos);

        // Datos de prueba
        List<C5_EstadoTicket> estados = new ArrayList<>();
        estados.add(new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false));
        estados.add(new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false));
        estados.add(new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true));

        listaFlujos.add(new C6_FlujoTickets("Flujo 1", estados, false));
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

    //ROL
    public void agregarFlujo(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_06/agregar_flujo.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de rol
        flujoController controlador = loader.getController();
        controlador.setVentanaPrincipalController(this);

        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Agregar Flujo");
        nuevaVentana.show();
    }

    public void editarFlujo(ActionEvent event) throws IOException {
        C6_FlujoTickets seleccionado = listViewFlujos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_06/agregar_flujo.fxml"));
            Parent root = loader.load();

            flujoController controlador = loader.getController();
            controlador.setVentanaPrincipalController(this);
            controlador.setFlujoParaEditar(seleccionado);

            Scene escena = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Editar Rol");
            ventana.show();
        } else {
            System.out.println("Seleccione un rol para editar.");
        }
    }

    public void agregarFlujoALista(C6_FlujoTickets flujo) {
        listaFlujos.add(0, flujo); // Agregar el rol a la lista observable
        listViewFlujos.setItems(listaFlujos); // Actualizar el TableView con los nuevos roles
        historialDeCambios.add(fechaHora + " - Se agrego " + flujo + " como nuevo flujo por " + usuario);
    }

    public void eliminarFlujo() {
        C6_FlujoTickets seleccionado = listViewFlujos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaFlujos.remove(seleccionado);
            listViewFlujos.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el flujo " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un rol primero.");
        }
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
