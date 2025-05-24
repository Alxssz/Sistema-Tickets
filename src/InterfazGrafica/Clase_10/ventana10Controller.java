package InterfazGrafica.Clase_10;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.File;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

public class ventana10Controller implements Initializable {

    @FXML
    private TableView<C7_Ticket> tablaViewTickets;
    private ObservableList<C7_Ticket> listaTickets;

    @FXML
    private TableColumn<C7_Ticket, String> colNumero;

    @FXML
    private TableColumn<C7_Ticket, String> colTitulo;

    @FXML
    private TextField txtNumeroTicket;

    @FXML
    private TextArea txtNota;

    @FXML
    private Button btnAdjuntar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCancelar;

    private List<String> historialDeCambios = new ArrayList<>();
    private File archivoAdjunto = null;

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar columnas de la tabla
        colNumero.setCellValueFactory(cellData
                -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId()))
        );

        colTitulo.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getTitulo())
        );

        // Crear departamentos de ejemplo
        C3_Departamento dep1 = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento dep2 = new C3_Departamento("Redes", "Área de redes");

        C5_EstadoTicket est1 = new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false);
        C5_EstadoTicket est2 = new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false);
        C5_EstadoTicket est3 = new C5_EstadoTicket("Cerrado", "El ticket se resolvió", true);;

        // Crear lista de tickets de ejemplo
        listaTickets = FXCollections.observableArrayList(new C7_Ticket("Ticket 1", "Error en login", dep1, "Alta", est1, "2025-05-21 10:30:00"),
                new C7_Ticket("ticket 2", "Solicitud de informacion", dep2, "Media", est2, "2025-05-20 15:00:00"),
                new C7_Ticket("ticket 3", "Reporte de bug", dep1, "Baja", est3, "2025-05-19 09:45:00")
        );

        tablaViewTickets.setItems(listaTickets);

        btnCancelar.setOnMouseClicked(event -> onCancelar());
        btnAgregar.setOnMouseClicked(event -> onAgregarNota());
        btnAdjuntar.setOnMouseClicked(event -> onAdjuntar());

    }

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

    public void agregarHistorial(String mensaje) {
        historialDeCambios.add(mensaje);
    }

    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    @FXML
    public void onAgregarNota() {
        String numTicket = txtNumeroTicket.getText().trim();
        String nota = txtNota.getText().trim();

        if (numTicket.isEmpty() || nota.isEmpty()) {
            System.out.println("Error Debe ingresar el número del ticket y una nota.");
            return;
        }

        int numero;
        try {
            numero = Integer.parseInt(numTicket);
        } catch (NumberFormatException e) {
            System.out.println("Número de ticket inválido.");
            return;
        }

        // Buscar el ticket por ID
        // Buscar el ticket por ID
        C7_Ticket ticketSeleccionado = null;
        for (C7_Ticket t : listaTickets) {
            if (t.getId() == numero) {
                ticketSeleccionado = t;
                break;
            }
        }

        if (ticketSeleccionado == null) {
            System.out.println("Ticket no encontrado No se encontró ningún ticket con el número proporcionado.");
            return;
        }

        String mensajeHistorial = fechaHora + "- Nota agregada al ticket #" + numero + " por " + usuario + "- [ Contenido: " + nota + " ]";

        if (archivoAdjunto != null) {
            mensajeHistorial += "[Archivo adjunto: " + archivoAdjunto.getName() + "]";
        }

        agregarHistorial(mensajeHistorial);
        System.out.println("Nota Agregada La nota se ha agregado exitosamente al ticket.");
        txtNota.clear();
        archivoAdjunto = null;
    }

    @FXML
    public void onCancelar() {
        txtNumeroTicket.clear();
        txtNota.clear();
        archivoAdjunto = null;
    }

    @FXML
    public void onAdjuntar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");
        archivoAdjunto = fileChooser.showOpenDialog(null);

        if (archivoAdjunto != null) {
            System.out.println("Archivo Adjuntado Archivo adjunto: " + archivoAdjunto.getName());
        }
    }
}
