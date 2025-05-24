package InterfazGrafica.Clase_07;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

/**
 * FXML Controller class
 *
 * @author Familia Boror Ruiz
 */
public class ventana7Controller implements Initializable {

    @FXML
    private TableView<C7_Ticket> listViewTickets;
    private ObservableList<C7_Ticket> listaTickets = FXCollections.observableArrayList();
    @FXML
    private TableColumn<C7_Ticket, String> colId;
    @FXML
    private TableColumn<C7_Ticket, String> colTitulo;
    @FXML
    private TableColumn<C7_Ticket, String> colDepartamento;
    @FXML
    private TableColumn<C7_Ticket, String> colPrioridad;
    @FXML
    private TableColumn<C7_Ticket, String> colEstado;
    @FXML
    private TableColumn<C7_Ticket, String> colFecha;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colId.setCellValueFactory(data
                -> new javafx.beans.property.ReadOnlyStringWrapper(String.valueOf(data.getValue().getId()))
        );
        colTitulo.setCellValueFactory(data
                -> new javafx.beans.property.ReadOnlyStringWrapper(data.getValue().getTitulo())
        );
        colDepartamento.setCellValueFactory(data -> {
            C3_Departamento d = data.getValue().getDepartamento();
            String nombreDepartamento = (d != null) ? d.getNombre() : "";
            return new javafx.beans.property.ReadOnlyStringWrapper(nombreDepartamento);
        });
        colPrioridad.setCellValueFactory(data
                -> new javafx.beans.property.ReadOnlyStringWrapper(data.getValue().getPrioridad())
        );
        colEstado.setCellValueFactory(data -> {
            C5_EstadoTicket e = data.getValue().getEstado();
            return new javafx.beans.property.ReadOnlyStringWrapper(e != null ? e.getNombre() : "");

        });
        colFecha.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getFechaCreacion()));

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

    public void registrarTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_07/registrarTicket.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de rol
        TicketController controlador = loader.getController();
        controlador.setVentanaPrincipalController(this);

        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Crear Ticket");
        nuevaVentana.show();
    }

    public void agregarTicketALista(C7_Ticket ticket) {
        listaTickets.add(ticket);
        listViewTickets.setItems(listaTickets);
        historialDeCambios.add(fechaHora + " - Se agrego nuevo ticket " + ticket + " por " + usuario);
    }

    public void editarTicket(ActionEvent event) throws IOException {
        C7_Ticket seleccionado = listViewTickets.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_07/registrarTicket.fxml"));
            Parent root = loader.load();

            TicketController controlador = loader.getController();
            controlador.setVentanaPrincipalController(this);
            controlador.setTicketParaEditar(seleccionado);

            Scene escena = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Editar Ticket ");
            ventana.show();
        } else {
            System.out.println("Seleccione un ticket para editar.");
        }
    }

    public void eliminarTicket() {
        C7_Ticket seleccionado = listViewTickets.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaTickets.remove(seleccionado);
            listViewTickets.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el Ticket " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un Ticket primero.");
        }
    }

    public void asignarTicket(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_07/AsignarTicket.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de rol
        AsignarController controlador = loader.getController();
        controlador.setVentanaPrincipalController(this);

        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Crear Ticket");
        nuevaVentana.show();
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

    public void actualizarTabla() {
        listViewTickets.refresh();
    }
}
