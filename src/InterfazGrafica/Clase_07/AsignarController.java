package InterfazGrafica.Clase_07;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

public class AsignarController implements Initializable {

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @FXML
    private TableView<C7_Ticket> listViewTickets;
    @FXML
    private TableColumn<C7_Ticket, String> colId;
    @FXML
    private TableColumn<C7_Ticket, String> colTitulo;
    @FXML
    private TableColumn<C7_Ticket, String> colDepartamento;

    @FXML
    private ListView<String> listViewTecnicos; // Técnicos disponibles del departamento seleccionado
    @FXML
    private ListView<String> listViewTecnicoAsignado; // Técnicos asignados al ticket seleccionado

    @FXML
    private Button asignarTecnico;
    @FXML
    private Button quitarTecnico;

    private ventana7Controller ventanaPrincipalController;

    private ObservableList<C7_Ticket> listaTickets = FXCollections.observableArrayList();

    // Técnicos disponibles simulados por departamento
    private final List<String> tecnicosSoporte = List.of("Ana Lopez","Maria Garcia");
    private final List<String> tecnicosRedes = List.of("Carlos Perez");

    private ObservableList<String> tecnicosDisponibles = FXCollections.observableArrayList();
    private ObservableList<String> tecnicosAsignados = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas de la tabla tickets
        colId.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getId())));
        colTitulo.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getTitulo()));
        colDepartamento.setCellValueFactory(data -> {
            C3_Departamento d = data.getValue().getDepartamento();
            return new ReadOnlyStringWrapper(d != null ? d.getNombre() : "");
        });

        // Datos de prueba tickets
        C3_Departamento dep1 = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento dep2 = new C3_Departamento("Redes", "Área de redes");

        C5_EstadoTicket est1 = new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false);
        C5_EstadoTicket est2 = new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false);
        C5_EstadoTicket est3 = new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true);

        listaTickets.addAll(new C7_Ticket("ticket 1", "Error en login", dep1, "Alta", est1, "2025-05-21 10:30:00"),
                new C7_Ticket("ticket 2", "Solicitud de información", dep2, "Media", est2, "2025-05-20 15:00:00"),
                new C7_Ticket("ticket 3", "Reporte de bug", dep1, "Baja", est3, "2025-05-19 09:45:00")
        );

        listViewTickets.setItems(listaTickets);

        // Inicializar listas técnicos
        listViewTecnicos.setItems(tecnicosDisponibles);
        listViewTecnicoAsignado.setItems(tecnicosAsignados);

        // Listener para cuando se seleccione un ticket
        listViewTickets.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                cargarTecnicosPorDepartamento(newSel.getDepartamento());
                tecnicosAsignados.clear(); // Limpiar técnicos asignados al cambiar ticket
            }
        });

        // Acciones botones
        asignarTecnico.setOnAction(e -> asignarTecnico());
        quitarTecnico.setOnAction(e -> quitarTecnico());
    }

    private void cargarTecnicosPorDepartamento(C3_Departamento departamento) {
        tecnicosDisponibles.clear();
        if (departamento == null) {
            return;
        }

        switch (departamento.getNombre()) {
            case "Soporte Técnico" ->
                tecnicosDisponibles.addAll(tecnicosSoporte);
            case "Ventas" ->
                tecnicosDisponibles.addAll(tecnicosRedes);
            default -> {
            }
        }
        // No técnicos si departamento no reconocido
    }

    private void asignarTecnico() {
        String tecSeleccionado = listViewTecnicos.getSelectionModel().getSelectedItem();
        C7_Ticket ticketSeleccionado = listViewTickets.getSelectionModel().getSelectedItem();
        if (tecSeleccionado != null && !tecnicosAsignados.contains(tecSeleccionado)) {
            tecnicosAsignados.add(tecSeleccionado);
            tecnicosDisponibles.remove(tecSeleccionado);
            String mensaje = fechaHora + " - Se asigno el  tecnico " + tecSeleccionado + " a el ticket " + ticketSeleccionado.getId()+ " por " + usuario;
            ventanaPrincipalController.agregarHistorial(mensaje);
        }
    }

    private void quitarTecnico() {
        String tecAsignado = listViewTecnicoAsignado.getSelectionModel().getSelectedItem();
        C7_Ticket ticketSeleccionado = listViewTickets.getSelectionModel().getSelectedItem();
        
        if (tecAsignado != null) {
            tecnicosAsignados.remove(tecAsignado);
            tecnicosDisponibles.add(tecAsignado);
            String mensaje = fechaHora + " - Se quito el  tecnico " + tecAsignado + " a el ticket " + ticketSeleccionado.getId() + " por " + usuario;
            ventanaPrincipalController.agregarHistorial(mensaje);
        }
    }

    public void setVentanaPrincipalController(ventana7Controller controller) {
        this.ventanaPrincipalController = controller;
    }
}
