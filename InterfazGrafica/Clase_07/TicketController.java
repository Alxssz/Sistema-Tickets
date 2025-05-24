package InterfazGrafica.Clase_07;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.C3_Departamento;
import sistematickets.C5_EstadoTicket;
import sistematickets.C7_Ticket;

public class TicketController implements Initializable {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ComboBox<C3_Departamento> cbDepartamento;

    @FXML
    private ComboBox<String> cbPrioridad;

    @FXML
    private ComboBox<C5_EstadoTicket> cbEstado;

    @FXML
    private Button btnCancelar;

    private C7_Ticket ticketAEditar;

    private ventana7Controller ventanaPrincipalController;

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Cargar departamentos
        cbDepartamento.getItems().addAll(new C3_Departamento("Soporte", "Área de soporte técnico"),
                new C3_Departamento("Redes", "Área de redes"),
                new C3_Departamento("Administración", "Area de administracion")
        );

        // Cargar prioridades
        cbPrioridad.getItems().addAll("Alta", "Media", "Baja");

        // Cargar estados
        cbEstado.getItems().addAll(new C5_EstadoTicket("Abierto", "El ticket ha sido creado", false),
                new C5_EstadoTicket("En proceso ", "El ticket esta en proceso", false),
                new C5_EstadoTicket("Cerrado","El ticket se resolvio", true)
        );
    }

    @FXML
    private void crearTicket() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        C3_Departamento departamento = cbDepartamento.getValue();
        String prioridad = cbPrioridad.getValue();
        C5_EstadoTicket estado = cbEstado.getValue();

        if (!titulo.isEmpty()) {
            if (ticketAEditar != null) {
                // Edición
                int id = ticketAEditar.getId();
                ticketAEditar.setTitulo(titulo);
                ticketAEditar.setDescripcion(descripcion);
                ticketAEditar.setDepartamento(departamento);
                ticketAEditar.setPrioridad(prioridad);
                ticketAEditar.setEstado(estado);
                // Asumamos que el ticket ya tiene fecha de creación, no la modificamos
                // Actualizar la tabla desde el controlador principal
                if (ventanaPrincipalController != null) {
                    ventanaPrincipalController.actualizarTabla();
                }
                String mensaje = fechaHora + " - Se edito el ticket con ID: " + id + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);

            } else {
                // Creación
                String fechaCreacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                C7_Ticket nuevoTicket = new C7_Ticket(titulo, descripcion, departamento, prioridad, estado, fechaCreacion);
                ventanaPrincipalController.agregarTicketALista(nuevoTicket);
            }
            cerrarVentana();
        }

    }

    @FXML
    public void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void setVentanaPrincipalController(ventana7Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    public void setTicketParaEditar(C7_Ticket ticket) {
        this.ticketAEditar = ticket;
        txtTitulo.setText(ticket.getTitulo());
        txtDescripcion.setText(ticket.getDescripcion());
        cbDepartamento.setValue(ticket.getDepartamento());
        cbPrioridad.setValue(ticket.getPrioridad());
        cbEstado.setValue(ticket.getEstado());
    }

}
