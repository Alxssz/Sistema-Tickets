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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sistematickets.C1_ParametrosDelSistema;

public class ventana1Controller implements Initializable {

    // VARIABLES
    private C1_ParametrosDelSistema parametrosDelSistema;

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

    private String usuario = "Eddy Alexis";
    private String fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private String rutaLogoSeleccionado = null;

    //INICIALIZADOR
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        parametrosDelSistema = C1_ParametrosDelSistema.cargarParametrosSistema();

        if (parametrosDelSistema == null) {
            parametrosDelSistema = new C1_ParametrosDelSistema();  // crea uno por defecto
        }

        parametrosDelSistema.cargarPrioridades();

        // Cargar combos con opciones fijas
        comboBoxIdioma.getItems().addAll("Español", "Inglés", "Frances");
        comboBoxZonaHoraria.getItems().addAll("UTC", "GMT", "PST", "EST");

        // Prioridades iniciales por defecto
        prioridades = FXCollections.observableArrayList("Alta", "Media", "Baja");
        listViewPrioridades.setItems(prioridades);
        // Spinner de tiempo vencimiento ticket
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 365, 30);
        spinnerTiempoVencimientoTicket.setValueFactory(valueFactory);

        //cargar 
        if (parametrosDelSistema != null) {
            // Rellenar campos con datos guardados
            if (parametrosDelSistema.getNombreEmpresa() != null) {
                nombreEmpresaField.setText(parametrosDelSistema.getNombreEmpresa());
            }

            if (parametrosDelSistema.getIdioma() != null) {
                comboBoxIdioma.setValue(parametrosDelSistema.getIdioma());
            }

            if (parametrosDelSistema.getZonaHoraria() != null) {
                comboBoxZonaHoraria.setValue(parametrosDelSistema.getZonaHoraria());
            }

            Integer tiempo = parametrosDelSistema.getTiempoVencimiento();

            if (tiempo >= 1 && tiempo <= 365) {
                spinnerTiempoVencimientoTicket.getValueFactory().setValue(tiempo);
            } else {;
                spinnerTiempoVencimientoTicket.getValueFactory().setValue(30);
            }

            // Mostrar logo si existe
            if (parametrosDelSistema.getLogo() != null && !parametrosDelSistema.getLogo().isEmpty()) {
                System.out.println("Ruta logo: " + parametrosDelSistema.getLogo());
                File archivoLogo = new File(parametrosDelSistema.getLogo());
                if (archivoLogo.exists()) {
                    System.out.println("Archivo logo existe, cargando...");
                    try {
                        mostrarLogo(archivoLogo);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ventana1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("Archivo logo NO existe en la ruta: " + archivoLogo.getAbsolutePath());
                }
            }

            // Cargar prioridades si están guardadas y no vacías
            if (parametrosDelSistema.getPrioridades() != null
                    && !parametrosDelSistema.getPrioridades().isEmpty()) {
                prioridades.setAll(parametrosDelSistema.getPrioridades());
                listViewPrioridades.setItems(prioridades);
            }

            // Cargar historial de cambios
            if (parametrosDelSistema.getHistorialDeCambios() != null) {
                historialDeCambios = parametrosDelSistema.getHistorialDeCambios();
            }
        }
    }

    //METODOS 
    @FXML
    public void reestablecerConfiguracion() throws IOException, Exception {
        parametrosDelSistema = new C1_ParametrosDelSistema();

        nombreEmpresaField.setText("Mi Empresa");

        // Actualiza la ObservableList con las prioridades por defecto y setea la ListView
        prioridades.setAll("Alta", "Media", "Baja");
        listViewPrioridades.setItems(prioridades);

        // Logo
        String rutaLogo = parametrosDelSistema.getLogo();
        if (rutaLogo != null && !rutaLogo.trim().isEmpty()) {
            File archivoLogo = new File(rutaLogo);
            if (archivoLogo.exists()) {
                imageViewLogo.setImage(new Image(new FileInputStream(archivoLogo)));
            } else {
                imageViewLogo.setImage(null);
            }
        } else {
            imageViewLogo.setImage(null);
        }
        comboBoxIdioma.setValue("Español");
        comboBoxZonaHoraria.setValue("UTC");
        spinnerTiempoVencimientoTicket.getValueFactory().setValue(30);

        System.out.println("Configuracion reestablecida a valores por defecto.");
        historialDeCambios.add(fechaHora + " - Se restablecieron los valores por defecto por " + usuario);
    }

    //SUBIR LOGO
    @FXML
    private void seleccionarLogo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));

        File archivoSeleccionado = fileChooser.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            try {
                mostrarLogo(archivoSeleccionado);
                rutaLogoSeleccionado = archivoSeleccionado.getAbsolutePath(); // Guardamos la ruta
                mensajeError.setText("");
            } catch (FileNotFoundException e) {
                System.err.println("Error al cargar la imagen: " + e.getMessage());
                mensajeError.setText("Error al cargar el logo.");
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo.");
        }
    }

    private void mostrarLogo(File archivo) throws FileNotFoundException {
        Image imagen = new Image(new FileInputStream(archivo));
        imageViewLogo.setImage(imagen);
    }

    //NAVEGAR ENTRE VENTANAS
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

    //PRIORIDADES
    public void agregarPrioridadALista(String prioridad) {
        prioridades.add(0, prioridad);  // Agregar la prioridad a la lista observable
        listViewPrioridades.setItems(prioridades);  // Actualizar el ListView con las prioridades
        historialDeCambios.add(fechaHora + " - Se agrego " + prioridad + " como nueva prioridad por " + usuario);
    }

    @FXML
    public void eliminarPrioridad() {
        String prioridadSeleccionada = listViewPrioridades.getSelectionModel().getSelectedItem();
        if (prioridadSeleccionada != null) {
            eliminarPrioridad(prioridadSeleccionada);  // Llama a metodo de eliminación
        }
    }

    public void eliminarPrioridad(String prioridad) {
        if (prioridades.contains(prioridad)) {
            prioridades.remove(prioridad);  // Eliminar la prioridad de la lista observable
            listViewPrioridades.setItems(prioridades);  // Actualizar el ListView con la nueva lista
            historialDeCambios.add(fechaHora + " - Se elimino " + prioridad + "  de prioridades " + usuario);
        }
    }

    //HISTORIAL DE CAMBIO
    public List<String> getHistorialDeCambios() {
        return historialDeCambios;
    }

    @FXML
    private void guardarConfiguracion(ActionEvent event) throws IOException, Exception {
        mensajeError.setVisible(false);
        String nuevoCambio;

        // Validar que los campos no estén vacíos o con valores inválidos
        if (nombreEmpresaField.getText().trim().isEmpty()
                || comboBoxIdioma.getValue() == null
                || comboBoxZonaHoraria.getValue() == null
                || spinnerTiempoVencimientoTicket.getValue() == null) {
            mensajeError.setVisible(true);
            return;
        }

        // Actualizar historial de cambios con cada modificación detectada
        if (!nombreEmpresaField.getText().trim().isEmpty()) {
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

        if (prioridades.size() < 3) {
            System.out.println("Error: Debe definir al menos tres niveles de prioridad.");
            return;
        }

        // Guardar los parámetros actualizados en el objeto y persistirlos
        if (parametrosDelSistema != null) {
            parametrosDelSistema.setNombreEmpresa(nombreEmpresaField.getText().trim());
            parametrosDelSistema.setIdioma(comboBoxIdioma.getValue());
            parametrosDelSistema.setZonaHoraria(comboBoxZonaHoraria.getValue());
            parametrosDelSistema.setTiempoVencimiento(spinnerTiempoVencimientoTicket.getValue());
            parametrosDelSistema.setHistorialDeCambios(historialDeCambios);
            parametrosDelSistema.setLogo(rutaLogoSeleccionado);
            parametrosDelSistema.guardarParametrosSistema();

            // Guardar las prioridades si existen
            parametrosDelSistema.setPrioridades(new ArrayList<>(prioridades));
            parametrosDelSistema.guardarPrioridades();
        }
    }
}
