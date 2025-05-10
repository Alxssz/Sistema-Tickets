package InterfazGrafica.Clase_01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sistematickets.ParametrosDelSistema;

public class ventana1Controller implements Initializable {

    // VARIABLES
    private ParametrosDelSistema parametrosDelSistema;

    @FXML
    private TextField nombreEmpresaField;

    @FXML
    private ImageView imageViewLogo;

    @FXML
    private ListView<String> listViewPrioridades;
    private ObservableList<String> prioridades = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> comboBoxIdioma;

    @FXML
    private ComboBox<String> comboBoxZonaHoraria;

    @FXML
    private Spinner<Integer> spinnerTiempoVencimientoTicket;

    @FXML
    private Text mensajeError;

    private List<String> historialDeCambios = new ArrayList<>();

    String usuario = "Eddy Alexis";
    String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    //INICIALIZADOR
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            comboBoxIdioma.getItems().addAll("Español", "Inglés", "Frances");

            comboBoxZonaHoraria.getItems().addAll("UTC", "GMT", "PST", "EST");

            prioridades = FXCollections.observableArrayList("Alta", "Media", "Baja");
            listViewPrioridades.setItems(prioridades);

            // Inicializar el Spinner para el tiempo de vencimiento del ticket, solo días
            SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 1);  // Min, Max, Initial Value
            spinnerTiempoVencimientoTicket.setValueFactory(valueFactory);

        } catch (Exception e) {
            // Manejo de excepciones si la inicialización falla
        }
    }

    @FXML
    private void guardarConfiguracion(ActionEvent event) throws IOException, Exception {
        // Ocultar el mensaje de error si el usuario comienza a ingresar datos
        mensajeError.setVisible(false);

        // Guardar la configuración
        String nuevoCambio;

        // Verificar si los campos obligatorios tienen valores
        if (nombreEmpresaField.getText().trim().isEmpty()
                || comboBoxIdioma.getValue() == null
                || comboBoxZonaHoraria.getValue() == null
                || spinnerTiempoVencimientoTicket.getValue() == null
                || spinnerTiempoVencimientoTicket.getValue() == 1) {
            // Mostrar el mensaje de error si algún campo está vacío o tiene un valor inválido
            mensajeError.setVisible(true);
            return; // Detener la ejecución si hay campos vacíos o inválidos
        }

        // Si todos los campos son válidos, guardar los cambios
        if (nombreEmpresaField.getText() != null && !nombreEmpresaField.getText().trim().isEmpty()) {
            nuevoCambio = fechaHora + " - Se cambió el nombre de la empresa a " + nombreEmpresaField.getText() + " por " + usuario;
            if (!historialDeCambios.contains(nuevoCambio)) {
                historialDeCambios.add(nuevoCambio);
            }
        }

        if (comboBoxIdioma.getValue() != null && !comboBoxIdioma.getValue().trim().isEmpty()) {
            nuevoCambio = fechaHora + " - Se cambió el idioma a " + comboBoxIdioma.getValue() + " por " + usuario;
            if (!historialDeCambios.contains(nuevoCambio)) {
                historialDeCambios.add(nuevoCambio);
            }
        }

        if (comboBoxZonaHoraria.getValue() != null && !comboBoxZonaHoraria.getValue().trim().isEmpty()) {
            nuevoCambio = fechaHora + " - Se cambió la zona horaria a " + comboBoxZonaHoraria.getValue() + " por " + usuario;
            if (!historialDeCambios.contains(nuevoCambio)) {
                historialDeCambios.add(nuevoCambio);
            }
        }

        if (spinnerTiempoVencimientoTicket.getValue() != null && spinnerTiempoVencimientoTicket.getValue() != 1) {
            nuevoCambio = fechaHora + " - Se cambió el tiempo de vencimiento del ticket a " + spinnerTiempoVencimientoTicket.getValue() + " días por " + usuario;
            if (!historialDeCambios.contains(nuevoCambio)) {
                historialDeCambios.add(nuevoCambio);
            }
        }
        // Aquí puedes continuar con el código para guardar o realizar más operaciones
    }

    @FXML
    public void reestablecerConfiguracion() {
        // Restablecer valores predeterminados
        nombreEmpresaField.setText("");
        prioridades.setAll("Alta", "Media", "Baja");
        listViewPrioridades.setItems(prioridades);
        imageViewLogo.setImage(null);
        comboBoxIdioma.setValue(null);
        comboBoxZonaHoraria.setValue(null);
        spinnerTiempoVencimientoTicket.getValueFactory().setValue(1);

        parametrosDelSistema = null;
        System.out.println("Configuracion reestablecida a valores por defecto.");
        historialDeCambios.add(fechaHora + " - Se restablecieron los valores por defecto por " + usuario);
    }
    // METODOS

    @FXML
    private void seleccionarLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));

        // Obtiene el archivo seleccionado
        File archivoSeleccionado = fileChooser.showOpenDialog(new Stage());

        if (archivoSeleccionado != null) {
            try {
                // Inicializa el objeto ParametrosDelSistema si no está inicializado
                if (parametrosDelSistema == null) {
                    parametrosDelSistema = new ParametrosDelSistema("Mi Empresa", archivoSeleccionado.getAbsolutePath(), "Español", "UTC", 30); // Ajusta estos parámetros según sea necesario
                }

                // Llama al método setLogo de ParametrosDelSistema para validar y asignar el logo
                parametrosDelSistema.setLogo(archivoSeleccionado.getAbsolutePath());
                mostrarLogo(archivoSeleccionado);

                historialDeCambios.add(fechaHora + " - Se cargo nuevo logo por " + usuario);
            } catch (Exception e) {
                System.out.println("Error al seleccionar el logo: " + e.getMessage());

            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo.");
        }
    }

    public void volverMenu(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Menu_Principal/menuP.fxml"));
        Parent root = loader.load();

        // Obtener el stage actual y cambiar la escena
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Menu Principal");
        stageActual.show();
    }

    // METODOS
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

    private void mostrarLogo(File archivoSeleccionado) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(archivoSeleccionado);
        Image imagen = new Image(inputStream);
        imageViewLogo.setImage(imagen);
    }

    public void agregarPrioridad(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/Agregar_Prioridad.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la ventana de agregar prioridad
        AgregarPrioridadController agregarPrioridadController = loader.getController();
        agregarPrioridadController.setVentanaPrincipalController(this);  // Pasar el controlador de la ventana principal

        // Crear un nuevo stage para la nueva ventana
        Stage nuevaVentana = new Stage();
        Scene nuevaEscena = new Scene(root);
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("AGREGAR PRIORIDAD");
        nuevaVentana.show();
    }

    public void agregarPrioridadALista(String prioridad) {
        prioridades.add(prioridad);  // Agregar la prioridad a la lista observable
        listViewPrioridades.setItems(prioridades);  // Actualizar el ListView con las prioridades
        historialDeCambios.add(fechaHora + " - Se agrego " + prioridad + " como nueva prioridad por " + usuario);
    }

    @FXML
    public void eliminarPrioridad() {
        String prioridadSeleccionada = listViewPrioridades.getSelectionModel().getSelectedItem();
        if (prioridadSeleccionada != null) {
            eliminarPrioridad(prioridadSeleccionada);  // Llama a tu método de eliminación
        }
    }

    public void eliminarPrioridad(String prioridad) {
        if (prioridades.contains(prioridad)) {
            prioridades.remove(prioridad);  // Eliminar la prioridad de la lista observable
            listViewPrioridades.setItems(prioridades);  // Actualizar el ListView con la nueva lista
            historialDeCambios.add(fechaHora + " - Se elimino " + prioridad + "  de prioridades " + usuario);
        }
    }

    public List<String> getHistorialDeCambios() {
        return historialDeCambios;
    }

    public void cancelarParametros(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/Ventana_Cancelar.fxml"));
        Parent root = loader.load();

        cancelarController cancelar = loader.getController();
        cancelar.setVentanaPrincipalController(this);  // Pasar el controlador de la ventana principal

        Stage nuevaVentana = new Stage();
        Scene nuevaEscena = new Scene(root);
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("Cancelar");
        nuevaVentana.show();
    }

    public void reestablecerParametros(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/Ventana_reestablecer.fxml"));
        Parent root = loader.load();

        reestablecerController reestablecer = loader.getController();
        reestablecer.setVentanaPrincipalController(this);

        Stage nuevaVentana = new Stage();
        Scene nuevaEscena = new Scene(root);
        nuevaVentana.setScene(nuevaEscena);
        nuevaVentana.setTitle("reestablecer");
        nuevaVentana.show();
    }

}
