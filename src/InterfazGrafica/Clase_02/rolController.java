package InterfazGrafica.Clase_02;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import sistematickets.C2_Rol;

public class rolController {

//VARIABLES
    private C2_Rol roles;

    @FXML
    private TextField rolField;
    @FXML
    private TextField descripcionField;
    @FXML
    private Button guardarBtn;

    private C2_Rol rolAEditar;

    private TableView<C2_Rol> tablaRoles;
    private List<C2_Rol> listaRoles = new ArrayList<>();

    private ventana2Controller ventanaPrincipalController;

     String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @FXML
    public void guardarRol() {
        String nombre = rolField.getText().trim();
        String descripcion = descripcionField.getText().trim();

        if (!nombre.isEmpty()) {
            if (rolAEditar != null) {
                // Modo edición
                rolAEditar.setNombreRol(nombre);
                rolAEditar.setDescripcion(descripcion);

                if (tablaRoles != null) {
                    tablaRoles.refresh();
                }

                // Agregar historial de edición
                String mensaje = fechaHora + " - Se editó el rol " + nombre + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                // Modo creación
                C2_Rol nuevoRol = new C2_Rol(nombre, descripcion);
                ventanaPrincipalController.agregarRolALista(nuevoRol);
            }
            // Cerrar la ventana
            cerrarVentana();
        } else {
            System.out.println("Por favor, complete el campo nombre.");
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

    //SETTERS
    public void setTablaRoles(TableView<C2_Rol> tablaRoles) {
        this.tablaRoles = tablaRoles;
    }

    public void setVentanaPrincipalController(ventana2Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    public void setRolParaEditar(C2_Rol rol) {
        this.rolAEditar = rol;
        rolField.setText(rol.getNombreRol());
        descripcionField.setText(rol.getDescripcion());
    }

    public TextField getRolField() {
        return rolField;
    }

    public TextField getDescripcionField() {
        return descripcionField;
    }

    public Button getGuardarBtn() {
        return guardarBtn;
    }

    public C2_Rol getRolAEditar() {
        return rolAEditar;
    }

    public TableView<C2_Rol> getTablaRoles() {
        return tablaRoles;
    }

    public String getFechaHora() {
        return fechaHora;
    }
}
