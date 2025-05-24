package sistematickets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class C2_Permiso implements Serializable {

    private String nombrePermiso;
    private String descripcion;
    private List<C2_Permiso> listaPermisos;

    // Constructor
    public C2_Permiso(String nombrePermiso, String descripcion) {
        if (nombrePermiso != null && nombrePermiso.length() > 3 && nombrePermiso.length() < 50) {
            this.nombrePermiso = nombrePermiso;
        } else {
            System.out.println("Debe contener entre 3 y 50 caracteres. No puede estar vacío.");
        }
        this.descripcion = descripcion;
    }

    // Getters y setters
    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        if (nombrePermiso != null && nombrePermiso.length() > 3 && nombrePermiso.length() < 50) {
            this.nombrePermiso = nombrePermiso;
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

    public List<C2_Permiso> getPermisos() {
        return listaPermisos;
    }

    public void setPermisos(List<C2_Permiso> permisos) {
        this.listaPermisos = permisos;
    }

    // Método toString
    @Override
    public String toString() {
        return nombrePermiso;
    }

    public void guardarPermisos(List<C2_Permiso> permisos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Archivos/roles.bin"))) {
            oos.writeObject(permisos);
            System.out.println("Permisos guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar los Roles del sistema: " + e.getMessage());
        }
    }

    public List<C2_Permiso> cargarPermisos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Archivos/roles.bin"))) {
            List<C2_Permiso> cargados = (List<C2_Permiso>) ois.readObject();
            System.out.println("Permisos cargados correctamente.");
            if (cargados.isEmpty()) {
                System.out.println("No hay permisos disponibles.");
            } else {
                System.out.println("Lista de permisos:");
                for (C2_Permiso permiso : cargados) {
                    System.out.println("- " + permiso.getNombrePermiso() + ": " + permiso.getDescripcion());
                }
            }
            return cargados;
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo de Roles: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
