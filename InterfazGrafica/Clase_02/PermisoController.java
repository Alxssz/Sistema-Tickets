package InterfazGrafica.Clase_02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.Permiso;

public class PermisoController {

    @FXML
    private TextField permisoField;

    @FXML
    private TextField descripcionField;

    @FXML
    private Button guardarBtn;

    @FXML
    private Button cancelarBtn;

    private Permiso permisoAEditar;
    private ListView<Permiso> listViewPermisos;

    private ventana2Controller ventanaPrincipalController;  // Controlador de la ventana principal

    String usuario = "Eddy Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // Método para setear el controlador de la ventana principal
    public void setVentanaPrincipalController(ventana2Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    public void setPermisoAEditar(Permiso permiso) {
        this.permisoAEditar = permiso;
        permisoField.setText(permiso.getNombrePermiso());
        descripcionField.setText(permiso.getDescripcion());
    }

    public void setListViewPermisos(ListView<Permiso> listView) {
        this.listViewPermisos = listView;
    }

    @FXML
    public void guardarPermiso() {
        String nombre = permisoField.getText().trim();
        String descripcion = descripcionField.getText().trim();

        if (!nombre.isEmpty()) {
            if (permisoAEditar != null) {
                // Modo edición
                permisoAEditar.setNombrePermiso(nombre);
                permisoAEditar.setDescripcion(descripcion);
                if (listViewPermisos != null) {
                    listViewPermisos.refresh();
                }
                String mensaje = fechaHora + " - Se edito el permiso "+ nombre+ " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                // Modo creación
                Permiso nuevoPermiso = new Permiso(nombre, descripcion);
                ventanaPrincipalController.agregarPermisoALista(nuevoPermiso);
            }
            cerrarVentana();
        } else {
            System.out.println("El nombre del permiso no puede estar vacío.");
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
}
