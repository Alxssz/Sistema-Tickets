package InterfazGrafica.Clase_04;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.C3_Departamento;
import sistematickets.C2_Rol;
import sistematickets.C2_UsuarioSistema;

public class ventana4Controller implements Initializable {

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Correo;
    @FXML
    private TextField Usuario;
    @FXML
    private TextField Contrasena;
    @FXML
    private ComboBox<C2_Rol> comboRol;
    @FXML
    private ComboBox<C3_Departamento> comboDepartamento;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnDesactivar;
    @FXML
    private TableView<C2_UsuarioSistema> listViewUsuarios;
    @FXML
    private TableColumn<C2_UsuarioSistema, String> colUsuario;
    @FXML
    private TableColumn<C2_UsuarioSistema, String> colEstado;

    private ObservableList<C2_UsuarioSistema> listaUsuarios = FXCollections.observableArrayList();
    private List<String> historialDeCambios = new ArrayList<>();

    private C2_UsuarioSistema usuarioEnEdicion = null;
    String usuarioS = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        colEstado.setCellValueFactory(cellData -> {
            boolean activo = cellData.getValue().getActivo();
            String estadoTexto = activo ? "Activo" : "Inactivo";
            return new ReadOnlyStringWrapper(estadoTexto);
        });

        comboDepartamento.getItems().addAll(new C3_Departamento("Soporte", "Área de soporte técnico"),
                new C3_Departamento("Redes", "Área de redes")
        );
        comboRol.getItems().addAll(new C2_Rol("Admin", "Administrador"),
                new C2_Rol("User", "Usuario regular"), 
                new C2_Rol("Tecnico", "Tecnico Sistema")
        );
    }

    @FXML
    public void guardarUsuario() {
        String nombre = Nombre.getText().trim();
        String correo = Correo.getText().trim();
        String nombreUsuario = Usuario.getText().trim();
        String contrasena = Contrasena.getText().trim();
        C2_Rol rol = comboRol.getValue();
        C3_Departamento departamento = comboDepartamento.getValue();

        if (nombre.isEmpty() || correo.isEmpty() || nombreUsuario.isEmpty() || contrasena.isEmpty()) {
            System.out.println("No pueden haber campos vacios");
            return;
        }

        if (usuarioEnEdicion == null) {
            C2_UsuarioSistema nuevoUsuario = new C2_UsuarioSistema(nombre, correo, nombreUsuario, contrasena, rol, departamento);
            if (nuevoUsuario.esValido()) {
                agregarUsuarioALista(nuevoUsuario);
                limpiarCampos();
            } else {
                System.out.println("Datos no validos para crear usuario");
            }
        } else {

            usuarioEnEdicion.setNombre(nombre);
            usuarioEnEdicion.setCorreo(correo);
            usuarioEnEdicion.setNombreUsuario(nombreUsuario);
            usuarioEnEdicion.setContrasena(contrasena);
            usuarioEnEdicion.setRolUsuario(rol);
            usuarioEnEdicion.setDepartamento(departamento);

            if (usuarioEnEdicion.esValido()) {
                listViewUsuarios.refresh();
                historialDeCambios.add(fechaHora + " - Se edito el usuario " + nombreUsuario + " por " + usuarioS);
                limpiarCampos();
                btnDesactivar.setVisible(false);
                usuarioEnEdicion = null;
            } else {
                System.out.println("Datos no validos para modificar usuario");
            }
        }
    }

    public void agregarUsuarioALista(C2_UsuarioSistema usuario) {
        listaUsuarios.add(0, usuario);
        listViewUsuarios.setItems(listaUsuarios);
        historialDeCambios.add(fechaHora + " - Se agrego a " + usuario + " como nuevo usuario por " + usuarioS);
    }

    @FXML
    private void editarUsuario() {
        C2_UsuarioSistema seleccionado = listViewUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Cargar datos en los campos
            Nombre.setText(seleccionado.getNombre());
            Correo.setText(seleccionado.getCorreo());
            Usuario.setText(seleccionado.getNombreUsuario());
            Contrasena.setText(seleccionado.getContrasena());
            comboRol.setValue(seleccionado.getRol());
            comboDepartamento.setValue(seleccionado.getDepartamento());

            // Guardar referencia para edición
            usuarioEnEdicion = seleccionado;
            // Mostrar el botón y actualizar su texto según el estado del usuario
            btnDesactivar.setVisible(true);
            btnDesactivar.setText(seleccionado.getActivo() ? "Desactivar Usuario" : "Activar Usuario");

            historialDeCambios.add(fechaHora + " - Se edito el usuario " + seleccionado + " por " + usuarioS);
        } else {
            System.out.println("Selecciona un usuario para editar.");
        }
    }

    @FXML
    private void desactivarUsuario() {
        if (usuarioEnEdicion != null) {
            boolean nuevoEstado = !usuarioEnEdicion.getActivo();
            usuarioEnEdicion.setActivo(nuevoEstado);
            listViewUsuarios.refresh();

            String estado = nuevoEstado ? "activado" : "desactivado";
            historialDeCambios.add(fechaHora + " - Se " + estado + " el usuario " + usuarioEnEdicion + " por " + usuarioS);

            // Actualiza el texto del botón
            btnDesactivar.setText(nuevoEstado ? "Desactivar Usuario" : "Activar Usuario");
        }
    }

    @FXML
    public void eliminarUsuario() {
        C2_UsuarioSistema seleccionado = listViewUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaUsuarios.remove(seleccionado);
            listViewUsuarios.refresh();
            historialDeCambios.add(fechaHora + " - Se elimino el usuario " + seleccionado + " por " + usuarioS);
        } else {
            System.out.println("Seleccione un usuario primero.");
        }
    }

    // HISTORIAL
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

    @FXML
    public void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    private void limpiarCampos() {
        Nombre.clear();
        Correo.clear();
        Usuario.clear();
        Contrasena.clear();
        comboRol.getSelectionModel().clearSelection();
        comboDepartamento.getSelectionModel().clearSelection();
    }
}
