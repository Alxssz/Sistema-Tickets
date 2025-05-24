package InterfazGrafica.Clase_03;

import InterfazGrafica.Clase_01.HistorialCambiosController;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistematickets.C3_Departamento;
import javafx.scene.control.ListView;
import sistematickets.C2_Rol;
import sistematickets.C2_Tecnico;


public class ventana3Controller implements Initializable {

    @FXML
    private TableView<C3_Departamento> listViewDepas;
    private ObservableList<C3_Departamento> listaDepas = FXCollections.observableArrayList();

    @FXML
    private TableColumn<C3_Departamento, String> nombreCol;
    @FXML
    private TableColumn<C3_Departamento, String> descripcionCol;

    @FXML
    private ListView<String> listViewTecnicos;
    private ObservableList<String> listaTecnicos = FXCollections.observableArrayList();

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar las columnas de la tabla
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        descripcionCol.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());

        // Crear departamentos de prueba
        C3_Departamento depaSoporte = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento depaRedes = new C3_Departamento("Redes", "Área de redes");

        // Agregar los departamentos a la lista observable
        listaDepas.add(depaSoporte);
        listaDepas.add(depaRedes);

        // Asignar la lista observable al TableView
        listViewDepas.setItems(listaDepas);

        // Crear un rol para los técnicos
        C2_Rol rolT = new C2_Rol("Tecnico", "Tecnico sistema");

        // Crear técnicos de prueba
        List<C2_Tecnico> tecnicosIniciales = List.of(
                new C2_Tecnico("Ana López", "ana@example.com", "anaL", "1234", rolT, depaSoporte),
                new C2_Tecnico("Carlos Pérez", "carlos@example.com", "carlosP", "abcd", rolT, depaRedes),
                new C2_Tecnico("María García", "maria@example.com", "mariaG", "5678", rolT, depaSoporte)
        );

        // Agregar técnicos a la lista del ListView
        for (C2_Tecnico tecnico : tecnicosIniciales) {
            String texto = tecnico.getNombre() + " - " + tecnico.getDepartamento().getNombre();
            listaTecnicos.add(texto);
        }

        // Asignar la lista observable al ListView
        listViewTecnicos.setItems(listaTecnicos);
    }

    public void volverMenu(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    public void agregarDepartamento(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_03/AgregarDepartamento.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana 
        agregarDepartamentoController agregarDepa = loader.getController();
        agregarDepa.setVentanaPrincipalController(this); // Establecer el controlador de la ventana principal para actualizar la lista de roles

        // Mostrar la nueva ventana para agregar 
        Scene nuevaEscena = new Scene(root);
        Stage nuevaVentana = new Stage();
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Datos Departamento");
        nuevaVentana.show();
    }

    public void agregarDepaALista(C3_Departamento depa) {
        listaDepas.add(0, depa); // Agregar a la lista observable
        listViewDepas.setItems(listaDepas); // Actualizar el TableView con los nuevos 
        historialDeCambios.add(fechaHora + " - Se agrego el departamento " + depa + " por " + usuario);
    }

    public void editarDepartamento() throws IOException {
        C3_Departamento depaSeleccionado = listViewDepas.getSelectionModel().getSelectedItem();
        if (depaSeleccionado != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/clase_03/AgregarDepartamento.fxml"));
            Parent root = loader.load();

            agregarDepartamentoController controlador = loader.getController();
            controlador.setDepatamentoAEditar(depaSeleccionado);
            controlador.setTablaDepas(listViewDepas);
            controlador.setVentanaPrincipalController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void eliminarDepa() {
        C3_Departamento seleccionado = listViewDepas.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaDepas.remove(seleccionado);
            listViewDepas.refresh();  // Esto no es obligatorio, pero ayuda
            historialDeCambios.add(fechaHora + " - Se elimino el Departamento " + seleccionado + " por " + usuario);
        } else {
            System.out.println("Seleccione un Departamneto primero.");
        }
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

    public void guardar() {
        System.out.println("Guardar");
    }
}
