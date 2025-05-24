package InterfazGrafica.Clase_02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.C2_Permiso;

public class PermisoController {

//VARIABLES
    private C2_Permiso permiso;
    private List<C2_Permiso> listaPermisos = new ArrayList<>();

    @FXML
    private TextField permisoField;

    @FXML
    private TextField descripcionField;

    @FXML
    private Button guardarBtn;

    private C2_Permiso permisoAEditar;
    private ListView<C2_Permiso> listViewPermisos;

    private ventana2Controller ventanaPrincipalController;  // Controlador de la ventana principal

     String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    //METODOS
    @FXML
    public void guardarPermiso() {
        String nombre = permisoField.getText().trim();
        String descripcion = descripcionField.getText().trim();

        if (!nombre.isEmpty()) {
            //EDICION
            if (permisoAEditar != null) {
                permisoAEditar.setNombrePermiso(nombre);
                permisoAEditar.setDescripcion(descripcion);
                if (listViewPermisos != null) {
                    listViewPermisos.refresh();
                }
                String mensaje = fechaHora + " - Se edito el permiso " + nombre + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                //CREACION
                C2_Permiso nuevoPermiso = new C2_Permiso(nombre, descripcion);
                ventanaPrincipalController.agregarPermisoALista(nuevoPermiso);
            }

            if (permiso != null) {
                permiso.setNombrePermiso(permisoField.getText().trim());
                permiso.setDescripcion(descripcionField.getText().trim());
                permiso.setPermisos(new ArrayList<>(listaPermisos));
                permiso.guardarPermisos(listaPermisos);
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

    //SETTER
    public void setVentanaPrincipalController(ventana2Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    public void setPermisoAEditar(C2_Permiso permiso) {
        this.permisoAEditar = permiso;
        permisoField.setText(permiso.getNombrePermiso());
        descripcionField.setText(permiso.getDescripcion());
    }

    public void setListViewPermisos(ListView<C2_Permiso> listView) {
        this.listViewPermisos = listView;
    }
}
