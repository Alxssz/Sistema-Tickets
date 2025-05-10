package InterfazGrafica.Menu_Principal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización si es necesario
    }

        public void volverLogin(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Login/Login.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Login");
        stageActual.show();
    }

    @FXML
    public void abrirParametros(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_01/ventana1.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Configuración Parámetros del Sistema");
        stageActual.show();
    }

    public void abrirGestionRoles(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_02/ventana2.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Registrar Roles y Permisos");
        stageActual.show();
    }

    public void abrirGestionDepartamento(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_03/ventana3.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Registrar Departamento o Area");
        stageActual.show();
    }

    public void abrirUsuario(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_04/ventana4.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Registrar Usuarios Iniciales");
        stageActual.show();
    }

    public void abrirEstado(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_05/ventana5.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("DEFINIR ESTADO TICKET");
        stageActual.show();
    }

    public void abrirFlujo(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_06/ventana6.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Configurar Flujo De Trabajo de Ticket");
        stageActual.show();
    }

    public void abrirGestionTicket(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_07/ventana7.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Gestionar Ticket");
        stageActual.show();
    }

    public void abrirLista(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_08/ventana8.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Consultar Lista de Solicitudes Pendientes");
        stageActual.show();
    }

    public void abrirCambiarEstado(MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_09/ventana9.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Cambiar Estado de Ticket");
        stageActual.show();
    }

    public void abrirNotaTicket (MouseEvent event) throws IOException {
        // Cargar la nueva ventana
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfazGrafica/Clase_10/ventana10.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stageActual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena sin cerrar la ventana
        Scene nuevaEscena = new Scene(root);
        stageActual.setScene(nuevaEscena);
        stageActual.setTitle("Agregar Nota a Ticket");
        stageActual.show();
    }
}
