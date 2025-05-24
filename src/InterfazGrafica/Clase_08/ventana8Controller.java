package InterfazGrafica.Clase_08;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

/**
 * Controlador para la gestion de tickets
 */
public class ventana8Controller implements Initializable {

    @FXML
    private TableView<?> tablaTickets;
    @FXML
    private ComboBox<String> comboFiltro;
    @FXML
    private TextField campoBusqueda;
    @FXML
    private Pane panelAdmin;
    @FXML
    private Pane panelTecnico;
    @FXML
    private Pane panelGeneral;
    @FXML
    private Label misTickets;
    @FXML
    private Label asignadosDep;
    @FXML
    private Label gestionTicket;

    @FXML
    private TableView<C7_Ticket> listViewTickets;
    private ObservableList<C7_Ticket> listaTickets = FXCollections.observableArrayList();

    @FXML
    private TableColumn<C7_Ticket, String> colId;

    @FXML
    private TableColumn<C7_Ticket, String> colTitulo;
    @FXML
    private TableColumn<C7_Ticket, String> colDescripcion;
    @FXML
    private TableColumn<C7_Ticket, String> colDepartamento;
    @FXML
    private TableColumn<C7_Ticket, String> colPrioridad;
    @FXML
    private TableColumn<C7_Ticket, String> colEstado;
    @FXML
    private TableColumn<C7_Ticket, String> colFecha;

    private String tipoUsuario = "Administrador";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarVistaPorTipoUsuario();

        colId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
        colTitulo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getTitulo()));
        colDescripcion.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDescripcion()));
        colDepartamento.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDepartamento().getNombre()));
        colPrioridad.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPrioridad()));
        colEstado.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEstado().getNombre()));
        colFecha.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFechaCreacion()));

        //DATOS DE PRUBEA
        C3_Departamento dep1 = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento dep2 = new C3_Departamento("Redes", "Área de redes");

        C5_EstadoTicket est1 = new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false);
        C5_EstadoTicket est2 = new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false);
        C5_EstadoTicket est3 = new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true);;

        ObservableList<C7_Ticket> tickets = FXCollections.observableArrayList(new C7_Ticket("ticket 1", "Error en login", dep1, "Alta", est1, "2025-05-21 10:30:00"),
                new C7_Ticket("ticket 2", "Solicitud de información", dep2, "Media", est2, "2025-05-20 15:00:00"),
                new C7_Ticket("ticket 3", "Reporte de bug", dep1, "Baja", est3, "2025-05-19 09:45:00")
        );

        listaTickets = tickets;
        listViewTickets.setItems(listaTickets);
    }

    private void configurarVistaPorTipoUsuario() {
        panelAdmin.setVisible(false);
        panelTecnico.setVisible(false);
        panelGeneral.setVisible(false);
        misTickets.setVisible(false);
        asignadosDep.setVisible(false);
        gestionTicket.setVisible(false);

        switch (tipoUsuario) {

            case "Usuario" -> {
                comboFiltro.setItems(FXCollections.observableArrayList("Abierto", "En Proceso", "Cerrado"));
                panelGeneral.setVisible(true);
                misTickets.setVisible(true);
            }
            case "Tecnico" -> {
                comboFiltro.setItems(FXCollections.observableArrayList("Alta", "Media", "Baja", "Abierto", "En Proceso", "Cerrado"));
                panelTecnico.setVisible(true);
                asignadosDep.setVisible(true);
            }
            case "Administrador" -> {
                comboFiltro.setItems(FXCollections.observableArrayList("Todos", "Abierto", "En Proceso", "Cerrado", "Alta", "Media", "Baja"));
                panelAdmin.setVisible(true);
                gestionTicket.setVisible(true);
            }
        }
    }

    @FXML
    private void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageActual.setScene(new Scene(root));
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    @FXML
    private void consultarDetalles(ActionEvent event) {
        // Mostrar detalles del ticket seleccionado
        mostrarAlerta("Detalles del ticket", "Aquí se mostrarían los detalles del ticket seleccionado.");
    }

    @FXML
    private void agregarNota(ActionEvent event) {
        // Lógica para agregar una nota al ticket seleccionado
        mostrarAlerta("Agregar Nota", "Se ha agregado una nota al ticket.");
    }

    @FXML
    private void tomarTicket(ActionEvent event) {
        if (!tipoUsuario.equals("Tecnico")) {
            return;
        }
        // Cambiar estado del ticket a "En proceso" 
        mostrarAlerta("Ticket tomado", "El ticket ha sido tomado y su estado se ha actualizado a 'En Proceso'.");
    }

    @FXML
    private void modificarEstado(ActionEvent event) {
        if (!tipoUsuario.equals("Administrador")) {
            return;
        }
        // Permitir modificar el estado de cualquier ticket
        mostrarAlerta("Modificar Estado", "Estado del ticket actualizado.");
    }

    @FXML
    private void reasignarDepartamento(ActionEvent event) {
        if (!tipoUsuario.equals("Administrador")) {
            return;
        }
        // Lógica para reasignar el ticket a otro departamento
        mostrarAlerta("Reasignar Ticket", "El ticket fue reasignado a otro departamento.");
    }

    @FXML
    private void buscar() {
        String texto = campoBusqueda.getText().trim().toLowerCase();

        if (texto.isEmpty()) {
            // Si el campo está vacío, mostramos todos los tickets
            listViewTickets.setItems(listaTickets);
        } else {
            // Filtramos la lista por el título que contenga el texto
            ObservableList<C7_Ticket> listaFiltrada = listaTickets.filtered(ticket
                    -> ticket.getTitulo().toLowerCase().contains(texto)
            );
            listViewTickets.setItems(listaFiltrada);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
