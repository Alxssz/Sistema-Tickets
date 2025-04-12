package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class Rol {

    private String nombreRol;
    private String descripcion;
    private List<Permiso> permisos;

    public Rol(String nombreRol, String descripcion) {
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.permisos = new ArrayList<>();
    }
    // Métodos getter y setter
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        if (nombreRol.length() > 3 && nombreRol.length() < 50) {
            this.nombreRol = nombreRol;
        } else {
            System.out.println("Debe contener entre 3 y 50 caracteres. No puede estar vacío.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public void mostrarInformacionRol () {
        System.out.println("Nombre rol: "+ nombreRol + "Descripcion" + descripcion);
    }
}
