package InterfazGrafica.Clase_05;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.C5_EstadoTicket;

public class estadoController implements Initializable {

    @FXML
    private TextField estadoField;
    @FXML
    private TextField descripcionField;
    @FXML
    private CheckBox chkFinal;
    @FXML
    private Button guardarBtn;

    private C5_EstadoTicket estadoAEditar;
    @FXML
    private TableView<C5_EstadoTicket> listaEstados;

    @FXML
    private javafx.scene.control.TableColumn<C5_EstadoTicket, String> colNombre;
    private ventana5Controller ventanaPrincipalController;

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colNombre.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("nombre"));
    }

    @FXML

    public void guardarEstado() {
        String nombre = estadoField.getText().trim();
        String descripcion = descripcionField.getText().trim();
        boolean esFinal = chkFinal.isSelected();

        if (!nombre.isEmpty()) {
            if (estadoAEditar != null) {
                // Edición
                estadoAEditar.setNombre(nombre);
                estadoAEditar.setDescripcion(descripcion);
                estadoAEditar.setEsEstadoFinal(esFinal);

                
                
                if (listaEstados != null) {
                    listaEstados.refresh();
                }
                String mensaje = fechaHora + " - Se edito el estado " + nombre + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                // Creación
                C5_EstadoTicket nuevoEstado = new C5_EstadoTicket(nombre, descripcion, esFinal);
                ventanaPrincipalController.agregarEstadoALista(nuevoEstado);
            }
            cerrarVentana();
        } else {
            System.out.println("Por favor, complete los campos");
        }
    }

    @FXML
    public void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) guardarBtn.getScene().getWindow();
        stage.close();
    }

    public void cargarListaEstados(java.util.List<C5_EstadoTicket> listaEst) {
        if (listaEstados != null) {
            listaEstados.getItems().setAll(listaEst);
        }
    }

    public void setEstadoAEditar(C5_EstadoTicket estado) {
        this.estadoAEditar = estado;
        estadoField.setText(estado.getNombre());
        descripcionField.setText(estado.getDescripcion());
        chkFinal.setSelected(estado.esFinal());
    }

    public void setTablaEstados(TableView<C5_EstadoTicket> tabla) {
        this.listaEstados = tabla;
    }

    public void setVentanaPrincipalController(ventana5Controller controller) {
        this.ventanaPrincipalController = controller;
    }
}
