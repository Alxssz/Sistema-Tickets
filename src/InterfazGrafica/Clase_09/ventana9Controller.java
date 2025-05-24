package InterfazGrafica.Clase_09;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

public class ventana9Controller implements Initializable {

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @FXML
    private TableView<C7_Ticket> tablaViewTickets;
    private ObservableList<C7_Ticket> listaTickets;

    @FXML
    private TableColumn<C7_Ticket, String> colNumero;

    @FXML
    private TableColumn<C7_Ticket, String> colEstado;

    @FXML
    private TextField txtNumeroTicket;

    @FXML
    private ComboBox<C5_EstadoTicket> comboNuevoEstado;

    @FXML
    private TextArea txtComentario;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextField txtEstadoActual;

    private ObservableList<C5_EstadoTicket> listaEstados;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar columnas de la tabla
        colNumero.setCellValueFactory(cellData
                -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))
        );

        colEstado.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEstado().getNombre())
        );

        // Crear departamentos de ejemplo
      C3_Departamento dep1 = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento dep2 = new C3_Departamento("Redes", "Área de redes");

        C5_EstadoTicket est1 = new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false);
        C5_EstadoTicket est2 = new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false);
        C5_EstadoTicket est3 = new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true);;
        
        listaEstados = FXCollections.observableArrayList(est1, est2, est3);

        comboNuevoEstado.setItems(listaEstados);

        // Crear lista de tickets de ejemplo
        listaTickets = FXCollections.observableArrayList(new C7_Ticket("ticket 1", "Error en login", dep1, "Alta", est1, "2025-05-21 10:30:00"),
                new C7_Ticket("ticket 2", "Solicitud de información", dep2, "Media", est2, "2025-05-20 15:00:00"),
                new C7_Ticket("ticket 3", "Reporte de bug", dep1, "Baja", est3, "2025-05-19 09:45:00")
        );

        tablaViewTickets.setItems(listaTickets);

// Listener para detectar cambios manuales en el campo txtNumeroTicket
        txtNumeroTicket.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                try {
                    int numero = Integer.parseInt(newValue.trim());
                    for (C7_Ticket t : listaTickets) {
                        if (t.getId() == numero) {
                            txtEstadoActual.setText(t.getEstado().getNombre());
                            return;
                        }
                    }
                    txtEstadoActual.clear();
                } catch (NumberFormatException e) {
                    txtEstadoActual.clear();
                }
            } else {
                txtEstadoActual.clear();
            }
        });
        // Configurar botones
        btnCancelar.setOnMouseClicked(event -> limpiarCampos());
        btnConfirmar.setOnMouseClicked(event -> confirmarCambioEstado());
    }

    // Método que confirma el cambio de estado del ticket
    private void confirmarCambioEstado() {
        String numeroStr = txtNumeroTicket.getText().trim();
        C5_EstadoTicket nuevoEstado = comboNuevoEstado.getValue();

        if (numeroStr.isEmpty() || nuevoEstado == null) {
            System.out.println("Debe ingresar el número de ticket y seleccionar un nuevo estado.");
            return;
        }

        int numero;
        try {
            numero = Integer.parseInt(numeroStr);
        } catch (NumberFormatException e) {
            System.out.println("Número de ticket inválido.");
            return;
        }

        // Buscar el ticket por ID
        C7_Ticket ticketSeleccionado = null;
        for (C7_Ticket t : listaTickets) {
            if (t.getId() == numero) {
                ticketSeleccionado = t;
                break;
            }
        }

        if (ticketSeleccionado == null) {
            System.out.println("Ticket no encontrado.");
            return;
        }

        // Actualizar estado y otros datos
        String estadoAnterior = ticketSeleccionado.getEstado().getNombre();
        ticketSeleccionado.setEstado(nuevoEstado);
        String comentario = txtComentario.getText().trim();

        // Agregar al historial
        historialDeCambios.add(fechaHora + " - Se cambio el estado de " + estadoAnterior + " a " + nuevoEstado.getNombre() + " del ticket " + ticketSeleccionado.getId() + " por " + usuario);

        // Refrescar tabla para mostrar el nuevo estado
        tablaViewTickets.refresh();

        // Limpiar campos luego del cambio
        limpiarCampos();

        System.out.println("Estado del ticket actualizado correctamente.");
    }

    // Cambiar de escena al menú principal
    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    // Mostrar ventana historial
    public void verHistorial(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/Historial_Cambios.fxml"));
        Parent root = loader.load();

        HistorialCambiosController historialController = loader.getController();
        historialController.cargarHistorial(historialDeCambios);

        Stage nuevaVentana = new Stage();
        Scene nuevaEscena = new Scene(root);
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Historial De Cambios");
        nuevaVentana.show();
    }

    // Agrega un mensaje al historial
    public void agregarHistorial(String mensaje) {
        historialDeCambios.add(mensaje);
    }

    private void limpiarCampos() {
        txtNumeroTicket.clear();
        txtEstadoActual.clear();
        comboNuevoEstado.getSelectionModel().clearSelection();
        txtComentario.clear();
        tablaViewTickets.getSelectionModel().clearSelection();
    }

}
