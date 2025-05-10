package sistematickets;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rol {

    private StringProperty nombreRol;
    private StringProperty descripcion;
    private List<Permiso> permisos;

    public Rol(String nombreRol, String descripcion) {
        this.nombreRol = new SimpleStringProperty(nombreRol);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.permisos = new ArrayList<>();
    }

    // Métodos getter y setter
    public StringProperty nombreRolProperty() {
        return nombreRol;
    }

    public String getNombreRol() {
        return nombreRol.get();
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol.set(nombreRol);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public void mostrarInformacionRol() {
        System.out.println("Nombre rol: " + nombreRol + "Descripcion" + descripcion);
    }

    @Override
    public String toString() {
        return nombreRol.get(); // Retorna solo el nombre del rol
    }
}
