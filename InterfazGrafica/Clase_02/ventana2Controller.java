package InterfazGrafica.Clase_02;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.Permiso;
import sistematickets.Rol;

public class ventana2Controller {

    @FXML
    private ListView<Permiso> listViewPermisos;
    private ObservableList<Permiso> listaPermisos = FXCollections.observableArrayList();

    @FXML
    private TableView<Rol> listViewRoles;
    private ObservableList<Rol> listaRoles = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Rol, String> nombreCol;
    @FXML
    private TableColumn<Rol, String> descripcionCol;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Eddy Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // Método para inicializar la ventana
    @FXML
    public void initialize() {
        // Inicializa lista de roles con nombre y descripcion
        listaRoles = FXCollections.observableArrayList(
                new Rol("Administrador", "Tiene acceso total al sistema."),
                new Rol("Técnico", "Puede gestionar tickets, asignarlos y resolverlos."),
                new Rol("Usuario", "Puede crear y consultar tickets.")
        );

        listViewRoles.setItems(listaRoles);
        // Vincula las columnas con las propiedades de Rol usando PropertyValueFactory
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().nombreRolProperty());
        descripcionCol.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());

        // Inicializar la lista de permisos
        listaPermisos = FXCollections.observableArrayList(
                new Permiso("Crear tickets", "Permite crear nuevos tickets"),
                new Permiso("Ver tickets", "Permite visualizar los tickets existentes"),
                new Permiso("Editar tickets", "Permite modificar los detalles de un ticket"),
                new Permiso("Eliminar tickets", "Permite eliminar tickets del sistema"),
                new Permiso("Asignar tickets", "Permite asignar tickets a usuarios"),
                new Permiso("Cambiar estado de tickets", "Permite actualizar el estado de los tickets"),
                new Permiso("Agregar notas a tickets", "Permite añadir comentarios o notas a los tickets"),
                new Permiso("Gestionar usuarios", "Permite administrar cuentas de usuarios"),
                new Permiso("Gestionar departamentos", "Permite administrar los departamentos de la empresa"),
                new Permiso("Gestionar flujos de trabajo", "Permite configurar el flujo de los tickets"),
                new Permiso("Configurar parámetros del sistema", "Permite cambiar configuraciones generales del sistema")
        );
        listViewPermisos.setItems(listaPermisos);

    }

    // Volver al menú principal
    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    // Método para agregar un nuevo rol
    public void agregarRol(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_02/rol.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de rol
        rolController agregarRol = loader.getController();
        agregarRol.setVentanaPrincipalController(this); // Establecer el controlador de la ventana principal para actualizar la lista de roles

        // Mostrar la nueva ventana para agregar un rol
        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Rol");
        nuevaVentana.show();
    }

    // Método para agregar un rol a la lista
    public void agregarRolALista(Rol rol) {
        listaRoles.add(rol); // Agregar el rol a la lista observable
        listViewRoles.setItems(listaRoles); // Actualizar el TableView con los nuevos roles
        historialDeCambios.add(fechaHora + " se agrego " + rol + " como rol por " + usuario);
    }

    // Método para editar un rol existente
    public void editarRol(ActionEvent event) throws IOException {
        Rol rolSeleccionado = listViewRoles.getSelectionModel().getSelectedItem();

        if (rolSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_02/rol.fxml"));
            Parent root = loader.load();

            rolController controlador = loader.getController();
            controlador.setVentanaPrincipalController(this); // Referencia al controlador principal
            controlador.setRolParaEditar(rolSeleccionado);   // Pasar el rol a editar

            Scene escena = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Editar Rol");
            ventana.show();
        } else {
            System.out.println("Seleccione un rol para editar.");
        }
    }

    @FXML
    public void eliminarRol() {
        Rol seleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaRoles.remove(seleccionado);
            listViewRoles.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el rol " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un rol primero.");
        }
    }

    public void agregarPermiso(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_02/permiso.fxml"));
        Parent root = loader.load();

        PermisoController agregarPermiso = loader.getController();
        agregarPermiso.setVentanaPrincipalController(this); // Establecer el controlador de la ventana principal para actualizar la lista de roles

        // Mostrar la nueva ventana para agregar un rol
        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Rol");
        nuevaVentana.show();
    }

    @FXML
    public void editarPermiso() throws IOException {
        Permiso permisoSeleccionado = listViewPermisos.getSelectionModel().getSelectedItem();
        if (permisoSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_02/permiso.fxml"));
            Parent root = loader.load();

            PermisoController controlador = loader.getController();
            controlador.setPermisoAEditar(permisoSeleccionado);
            controlador.setListViewPermisos(listViewPermisos);
            controlador.setVentanaPrincipalController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    void agregarPermisoALista(Permiso permiso) {
        listaPermisos.add(permiso);  // Agregar el permiso a la lista observable
        listViewPermisos.setItems(listaPermisos);  // Actualizar el ListView con la lista
        historialDeCambios.add(fechaHora + " - Se agrego " + permiso + " como nuevo permiso por " + usuario);
    }

    public void eliminarPermiso() {
        Permiso seleccionado = listViewPermisos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaPermisos.remove(seleccionado);
            listViewPermisos.refresh();
            historialDeCambios.add(fechaHora + " - Se elimino el permiso " + seleccionado + " por " + usuario);

        } else {
            System.out.println("Seleccione un Permiso primero.");
        }
    }

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

}
