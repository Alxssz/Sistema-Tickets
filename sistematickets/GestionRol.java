package sistematickets;

import java.util.ArrayList;

public class GestionRol {

    private ArrayList<Rol> listaRoles;

    public GestionRol(UsuarioSistema administrador) {
        this.listaRoles = new ArrayList<>();
    }

    // Agregar rol
    public void agregarRol(Rol rol) {
        listaRoles.add(rol);
        System.out.println("Rol '" + rol.getNombreRol() + "' agregado correctamente a Roles.");
    }

    // Mostrar todos los roles
public void mostrarRoles() {
    if (listaRoles.isEmpty()) {
        System.out.println("No hay roles registrados.");
        return;
    }

    System.out.println("INFORMACION DE ROLES");
    for (Rol rol : listaRoles) {
        System.out.println("Rol: " + rol.getNombreRol() + " - " + rol.getDescripcion());
        if (rol.getPermisos().isEmpty()) {
            System.out.println("No hay permisos asignados.");
        } else {
            System.out.println("Permisos asignados:");
            for (Permiso permiso : rol.getPermisos()) {
                System.out.println("- " + permiso.getNombrePermiso());
            }
        }
        System.out.println();
    }
}


    // Buscar un rol por nombre
    private Rol buscarRolPorNombre(String nombre) {
        for (Rol rol : listaRoles) {
            if (rol.getNombreRol().equalsIgnoreCase(nombre)) {
                return rol;
            }
        }
        return null;
    }

    // Editar rol
    public void editarRol(String nombreActual, String nuevoNombre, String nuevaDescripcion) {
        Rol rol = buscarRolPorNombre(nombreActual);
        if (rol != null) {
            rol.setNombreRol(nuevoNombre);
            rol.setDescripcion(nuevaDescripcion);
            System.out.println("Rol modificado correctamente.");
            System.out.println("");
        } else {
            System.out.println("Rol no encontrado para editar.");
        }
    }

    // Eliminar rol
    public void eliminarRol(String nombre) {
        Rol rol = buscarRolPorNombre(nombre);
        if (rol != null) {
            listaRoles.remove(rol);
            System.out.println("Rol '" + nombre + "' eliminado correctamente de Roles.");
        } else {
            System.out.println("Rol no encontrado para eliminar.");
        }
    }
}
