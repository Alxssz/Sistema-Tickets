package InterfazGrafica.Clase_02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import sistematickets.Rol;

public class rolController {

    @FXML
    private TextField rolField;
    @FXML
    private TextField descripcionField;
    @FXML
    private Button guardarBtn;
    @FXML
    private Button cancelarBtn;

    private Rol rolAEditar;

    private TableView<Rol> tablaRoles; // referencia directa a la tabla

    private ventana2Controller ventanaPrincipalController;  // Controlador de la ventana principal

    String usuario = "Eddy Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    
    // Método para setear el controlador de la ventana principal
    public void setVentanaPrincipalController(ventana2Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    // Método para establecer el rol que se editará
    public void setRolParaEditar(Rol rol) {
        this.rolAEditar = rol;
        rolField.setText(rol.getNombreRol());
        descripcionField.setText(rol.getDescripcion());
    }

    @FXML
    public void guardarRol() {
        String nombre = rolField.getText().trim();
        String descripcion = descripcionField.getText().trim();

        if (!nombre.isEmpty() && !descripcion.isEmpty()) {
            if (rolAEditar != null) {
                // Modo edición: modificar el rol existente
                rolAEditar.setNombreRol(nombre);
                rolAEditar.setDescripcion(descripcion);
                if (tablaRoles != null) {
                    tablaRoles.refresh();
                }
            // Guardar el historial de edición
            String mensaje = fechaHora + "- se editó el rol " + nombre + " por " + usuario;
            ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                // Modo creación
                Rol nuevoRol = new Rol(nombre, descripcion);
                ventanaPrincipalController.agregarRolALista(nuevoRol);
            }

            cerrarVentana();
        } else {
            System.out.println("Por favor, complete ambos campos.");
        }
    }

    @FXML
    public void cancelar() {
        cerrarVentana();  // Cerrar la ventana sin guardar
    }

    // Método para cerrar la ventana
    private void cerrarVentana() {
        Stage stage = (Stage) guardarBtn.getScene().getWindow();
        stage.close();
    }

    public void setTablaRoles(TableView<Rol> tablaRoles) {
        this.tablaRoles = tablaRoles;
    }
}
