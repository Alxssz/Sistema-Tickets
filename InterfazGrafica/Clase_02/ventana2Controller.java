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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.C2_Permiso;
import sistematickets.C2_Rol;

public class ventana2Controller {

    @FXML
    private ListView<C2_Permiso> listViewPermisos;
    private ObservableList<C2_Permiso> listaPermisos = FXCollections.observableArrayList();

    @FXML
    private TableView<C2_Rol> listViewRoles;
    private ObservableList<C2_Rol> listaRoles = FXCollections.observableArrayList();

    @FXML
    private TableColumn<C2_Rol, String> nombreCol;
    @FXML
    private TableColumn<C2_Rol, String> descripcionCol;

    @FXML
    private Button permisosDeRol;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    // INICIALIZADOR
    @FXML
    public void initialize() {

        // LISTA ROLES
        // Cargar los roles desde el archivo
        listaRoles = FXCollections.observableArrayList(new C2_Rol("Administrador", "Tiene acceso total al sistema."),
                new C2_Rol("Tecnico", "Puede gestionar tickets, asignarlos y resolverlos."),
                new C2_Rol("Usuario", "Puede crear y consultar tickets.")
        );
        listViewRoles.setItems(listaRoles);

        nombreCol.setCellValueFactory(cellData -> cellData.getValue().nombreRolProperty());
        descripcionCol.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());

        //LISTA PERMISOS
        listaPermisos = FXCollections.observableArrayList(new C2_Permiso("Crear tickets", "Permite crear nuevos tickets"),
                new C2_Permiso("Ver tickets", "Permite visualizar los tickets existentes"),
                new C2_Permiso("Editar tickets", "Permite modificar los detalles de un ticket"),
                new C2_Permiso("Eliminar tickets", "Permite eliminar tickets del sistema"),
                new C2_Permiso("Asignar tickets", "Permite asignar tickets a usuarios"),
                new C2_Permiso("Cambiar estado de tickets", "Permite actualizar el estado de los tickets"),
                new C2_Permiso("Agregar notas a tickets", "Permite añadir comentarios o notas a los tickets"),
                new C2_Permiso("Gestionar usuarios", "Permite administrar cuentas de usuarios"),
                new C2_Permiso("Gestionar departamentos", "Permite administrar los departamentos de la empresa"),
                new C2_Permiso("Gestionar flujos de trabajo", "Permite configurar el flujo de los tickets"),
                new C2_Permiso("Configurar parámetros del sistema", "Permite cambiar configuraciones generales del sistema")
        );
        listViewPermisos.setItems(listaPermisos);

        listViewRoles.getSelectionModel().selectedItemProperty().addListener((obs, oldRol, nuevoRol) -> {
            if (nuevoRol != null) {
                permisosDeRol.setVisible(true);
                mostrarPermisosIniciales();
            } else {
                // Si no hay ningún rol seleccionado, también ocultamos el botón
                permisosDeRol.setVisible(false);
            }
        });
    }// NAVEGAR ENTRE VENTANAS

    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    //ROL
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

    public void editarRol(ActionEvent event) throws IOException {
        C2_Rol rolSeleccionado = listViewRoles.getSelectionModel().getSelectedItem();

        if (rolSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_02/rol.fxml"));
            Parent root = loader.load();

            rolController controlador = loader.getController();
            controlador.setVentanaPrincipalController(this);
            controlador.setRolParaEditar(rolSeleccionado);

            Scene escena = new Scene(root);
            Stage ventana = new Stage();
            ventana.setScene(escena);
            ventana.setTitle("Editar Rol");
            ventana.show();
        } else {
            System.out.println("Seleccione un rol para editar.");
        }
    }

    public void agregarRolALista(C2_Rol rol) {
        listaRoles.add(0, rol); // Agregar el rol a la lista observable
        listViewRoles.setItems(listaRoles); // Actualizar el TableView con los nuevos roles;
        historialDeCambios.add(fechaHora + " - Se agrego " + rol + " como rol por " + usuario);
    }

    public void eliminarRol() {
        C2_Rol seleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaRoles.remove(seleccionado);
            listViewRoles.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el rol " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un rol primero.");
        }
    }

    //PERMISOS
    public void editarPermiso() throws IOException {
        C2_Permiso permisoSeleccionado = listViewPermisos.getSelectionModel().getSelectedItem();
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

    void agregarPermisoALista(C2_Permiso permiso) {
        listaPermisos.add(0, permiso);  // Agregar el permiso a la lista observable
        listViewPermisos.setItems(listaPermisos);  // Actualizar el ListView con la lista
        historialDeCambios.add(fechaHora + " - Se agrego " + permiso + " como nuevo permiso por " + usuario);
    }

    public void eliminarPermiso() {
        C2_Permiso seleccionado = listViewPermisos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaPermisos.remove(seleccionado);
            listViewPermisos.refresh();
            historialDeCambios.add(fechaHora + " - Se elimino el permiso " + seleccionado + " por " + usuario);

        } else {
            System.out.println("Seleccione un Permiso primero.");
        }
    }

    //ASIGNAR PERMISOS
    @FXML
    public void asignarPermiso(ActionEvent event) {
        C2_Rol rolSeleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        C2_Permiso permisoSeleccionado = listViewPermisos.getSelectionModel().getSelectedItem();

        if (rolSeleccionado != null && permisoSeleccionado != null) {
            if (!rolSeleccionado.getPermisos().contains(permisoSeleccionado)) {
                asignarPermisoARol(rolSeleccionado, permisoSeleccionado);
                historialDeCambios.add(fechaHora + " - Se asigno el permiso " + permisoSeleccionado + " al rol  " + rolSeleccionado + " por " + usuario);
                System.out.println("Permiso asignado correctamente.");
            } else {
                System.out.println("El rol ya tiene este permiso.");
            }
        } else {
            System.out.println("Seleccione un rol y un permiso.");
        }
    }

    @FXML
    public void quitarPermiso(ActionEvent event) {
        C2_Rol rolSeleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        C2_Permiso permisoSeleccionado = listViewPermisos.getSelectionModel().getSelectedItem();

        if (rolSeleccionado != null && permisoSeleccionado != null) {
            if (rolSeleccionado.getPermisos().contains(permisoSeleccionado)) {
                quitarPermisoARol(rolSeleccionado, permisoSeleccionado);
                historialDeCambios.add(fechaHora + " - Se quito el permiso " + permisoSeleccionado + " al rol  " + rolSeleccionado + " por " + usuario);
                listaPermisos.setAll(rolSeleccionado.getPermisos());
                System.out.println("Permiso removido correctamente.");
            } else {
                System.out.println("El rol no tiene este permiso.");
            }
        } else {
            System.out.println("Seleccione un rol y un permiso.");
        }
    }

    public void asignarPermisoARol(C2_Rol rol, C2_Permiso permiso) {
        rol.agregarPermiso(permiso);
    }

    public void quitarPermisoARol(C2_Rol rol, C2_Permiso permiso) {
        rol.quitarPermiso(permiso);
    }

    public void mostrarPermisosDelRol(ActionEvent event) {
        C2_Rol rolSeleccionado = listViewRoles.getSelectionModel().getSelectedItem();
        if (rolSeleccionado != null) {
            permisosDeRol.setVisible(true);
            listaPermisos.setAll(rolSeleccionado.getPermisos());
            listViewPermisos.setItems(listaPermisos);
            System.out.println("Permisos cargados para el rol: " + rolSeleccionado.getNombreRol());
        } else {
            System.out.println("Seleccione un rol para ver sus permisos.");
        }
    }

    private void mostrarPermisosIniciales() {
        listaPermisos.setAll(new C2_Permiso("Crear tickets", "Permite crear nuevos tickets"),
                new C2_Permiso("Ver tickets", "Permite visualizar los tickets existentes"),
                new C2_Permiso("Editar tickets", "Permite modificar los detalles de un ticket"),
                new C2_Permiso("Eliminar tickets", "Permite eliminar tickets del sistema"),
                new C2_Permiso("Asignar tickets", "Permite asignar tickets a usuarios"),
                new C2_Permiso("Cambiar estado de tickets", "Permite actualizar el estado de los tickets"),
                new C2_Permiso("Agregar notas a tickets", "Permite añadir comentarios o notas a los tickets"),
                new C2_Permiso("Gestionar usuarios", "Permite administrar cuentas de usuarios"),
                new C2_Permiso("Gestionar departamentos", "Permite administrar los departamentos de la empresa"),
                new C2_Permiso("Gestionar flujos de trabajo", "Permite configurar el flujo de los tickets"),
                new C2_Permiso("Configurar parámetros del sistema", "Permite cambiar configuraciones generales del sistema")
        );
        listViewPermisos.setItems(listaPermisos);
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

    public List<C2_Rol> getListaRoles() {
        return listaRoles;
    }

    public void guardarGestion() {
        System.out.println("Se guardo la gestion roles y permisos");
    }
}
