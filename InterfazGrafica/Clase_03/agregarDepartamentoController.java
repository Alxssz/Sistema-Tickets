package InterfazGrafica.Clase_03;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistematickets.C3_Departamento;
import sistematickets.C2_Rol;
import sistematickets.C2_Tecnico;

public class agregarDepartamentoController implements Initializable {

    //VARIABLES
    @FXML
    private TextField nombreDepaField;
    @FXML
    private TextField desDepaField;
    @FXML
    private Button guardarBtn;
    @FXML
    private ListView<C2_Tecnico> listaTecnicosDisponibles;
    @FXML
    private ListView<C2_Tecnico> listaTecnicosAsignados;

    private ObservableList<C2_Tecnico> tecnicosDisponibles = FXCollections.observableArrayList();
    private ObservableList<C2_Tecnico> tecnicosAsignados = FXCollections.observableArrayList();

    private C3_Departamento departamentoEditar;
    private TableView<C3_Departamento> tablaDepas;

    private ventana3Controller ventanaPrincipalController;

    String usuario = "Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        C2_Rol rolT = new C2_Rol("Tecnico", "Tecnico sistema");

        // Crear departamentos de prueba
        C3_Departamento depaSoporte = new C3_Departamento("Soporte", "Área de soporte técnico");
        C3_Departamento depaRedes = new C3_Departamento("Redes", "Área de redes");

        tecnicosDisponibles.addAll(new C2_Tecnico("Ana López", "ana@example.com", "anaL", "1234", rolT, depaSoporte),
                new C2_Tecnico("Carlos Pérez", "carlos@example.com", "carlosP", "abcd", rolT, depaRedes),
                new C2_Tecnico("María García", "maria@example.com", "mariaG", "5678", rolT, depaSoporte)
        );

        listaTecnicosDisponibles.setItems(tecnicosDisponibles);
        listaTecnicosAsignados.setItems(tecnicosAsignados);
    }

    @FXML
    public void guardarDepa() {
        String nombre = nombreDepaField.getText().trim();
        String descripcion = desDepaField.getText().trim();

        if (!nombre.isEmpty()) {
            if (departamentoEditar != null) {
                // EDICION
                departamentoEditar.setNombre(nombre);
                departamentoEditar.setDescripcion(descripcion);
                departamentoEditar.setTecnicosAsignados(new ArrayList<>(tecnicosAsignados));
                if (tablaDepas != null) {
                    tablaDepas.refresh();
                }
                // Guardar el historial de edición
                String mensaje = fechaHora + " - Se edito el Departamento " + nombre + " por " + usuario;
                ventanaPrincipalController.agregarHistorial(mensaje);
            } else {
                //CREACION
                C3_Departamento nuevoDepa = new C3_Departamento(nombre, descripcion);
                nuevoDepa.setTecnicosAsignados(new ArrayList<>(tecnicosAsignados));
                ventanaPrincipalController.agregarDepaALista(nuevoDepa);

            }
            cerrarVentana();
        } else {
            System.out.println("Por favor, complete ambos campos.");
        }
    }

    public void setVentanaPrincipalController(ventana3Controller controller) {
        this.ventanaPrincipalController = controller;
    }

    @FXML
    private void asignarTecnico() {
        String nombreDepa = nombreDepaField.getText().trim();
        C2_Tecnico tecnico = listaTecnicosDisponibles.getSelectionModel().getSelectedItem();
        if (tecnico != null && !tecnicosAsignados.contains(tecnico)) {
            tecnicosDisponibles.remove(tecnico);
            tecnicosAsignados.add(tecnico);
            String mensaje = fechaHora + " - Se asigno el tecnico " + tecnico + " al Departamento " + nombreDepa + " por " + usuario;
            ventanaPrincipalController.agregarHistorial(mensaje);
        } else {
            // opcional: mostrar mensaje si ya está asignado
            System.out.println("El tecnico ya esta asignado.");
        }
    }

    @FXML
    private void quitarTecnico() {
        C2_Tecnico tecnico = listaTecnicosAsignados.getSelectionModel().getSelectedItem();
        if (tecnico != null && !tecnicosDisponibles.contains(tecnico)) {
            tecnicosAsignados.remove(tecnico);
            String mensaje = fechaHora + " - Se quito el tecnico " + tecnico + " del Departamento " + nombreDepaField.getText() + " por " + usuario;
            ventanaPrincipalController.agregarHistorial(mensaje);
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

    public void setDepatamentoAEditar(C3_Departamento depa) {
        this.departamentoEditar = depa;
        nombreDepaField.setText(depa.getNombre());
        desDepaField.setText(depa.getDescripcion());

        if (depa.getTecnicosAsignados() != null) {
            tecnicosAsignados.setAll(depa.getTecnicosAsignados());
            tecnicosDisponibles.removeAll(depa.getTecnicosAsignados()); // evitar duplicados
        }
    }

    public void setTablaDepas(TableView<C3_Departamento> tablaDepas) {
        this.tablaDepas = tablaDepas;
    }

}
