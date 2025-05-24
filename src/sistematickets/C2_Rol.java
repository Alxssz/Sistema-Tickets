package sistematickets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class C2_Rol implements Serializable {

    static C2_Rol valueOf(String toUpperCase) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Campos serializables
    private String nombreRol;
    private String descripcion;

    // Propiedades JavaFX (transient porque no se serializan)
    private transient StringProperty nombreRolProp;
    private transient StringProperty descripcionProp;

    // Lista de permisos asignados a este rol
    private List<C2_Permiso> listadoPermisos;

    // Lista de roles (si se requiere)
    private List<C2_Rol> roles;

    // Constructor
    public C2_Rol(String nombreRol, String descripcion) {
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.listadoPermisos = new ArrayList<>();
        // Inicializar propiedades JavaFX
        this.nombreRolProp = new SimpleStringProperty(nombreRol);
        this.descripcionProp = new SimpleStringProperty(descripcion);
    }

    // Getters y setters para campos serializables
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
        if (this.nombreRolProp != null) {
            this.nombreRolProp.set(nombreRol);
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        if (this.descripcionProp != null) {
            this.descripcionProp.set(descripcion);
        }
    }

    // Getters para propiedades JavaFX (UI)
    public StringProperty nombreRolProperty() {
        if (nombreRolProp == null) {
            nombreRolProp = new SimpleStringProperty(nombreRol);
        }
        return nombreRolProp;
    }

    public StringProperty descripcionProperty() {
        if (descripcionProp == null) {
            descripcionProp = new SimpleStringProperty(descripcion);
        }
        return descripcionProp;
    }

    // Métodos para manejar permisos
    public void agregarPermiso(C2_Permiso permiso) {
        if (!listadoPermisos.contains(permiso)) {
            listadoPermisos.add(permiso);
            System.out.println("Permiso agregado correctamente al rol " + nombreRol);
        } else {
            System.out.println("El permiso ya existe en la lista.");
        }
    }

    public void quitarPermiso(C2_Permiso permiso) {
        if (listadoPermisos.contains(permiso)) {
            listadoPermisos.remove(permiso);
            System.out.println("Permiso eliminado correctamente del rol "+ nombreRol);
        } else {
            System.out.println("El permiso no existe en la lista.");
        }
    }

    // Getters y setters para la lista de permisos
    public List<C2_Permiso> getPermisos() {
        return listadoPermisos;
    }

    public void setListadoPermisos(List<C2_Permiso> listadoPermisos) {
        this.listadoPermisos = listadoPermisos;
    }

    // Getters y setters para la lista de roles
    public List<C2_Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<C2_Rol> roles) {
        this.roles = roles;
    }

    // Mostrar permisos asignados
    public void mostrarPermisosAsignados() {
        if (listadoPermisos.isEmpty()) {
            System.out.println("El listado está vacío.");
        } else {
            System.out.println("Permisos asignados al rol " +nombreRol +" : ");
            for (C2_Permiso permiso : listadoPermisos) {
                System.out.println(permiso);
            }
        }
    }

    @Override
    public String toString() {
        return getNombreRol();
    }

    // Método equalsIgnoreCase implementado correctamente
    public boolean equalsIgnoreCase(String otroNombre) {
        if (otroNombre == null) return false;
        return this.nombreRol.equalsIgnoreCase(otroNombre);
    }
}
