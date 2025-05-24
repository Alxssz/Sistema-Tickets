package sistematickets;

import java.util.ArrayList;

public class C2_SistemaRolPermiso {

    private ArrayList<C2_Rol> listaRoles;
    private ArrayList<C2_Permiso> listaPermisos;

    public C2_SistemaRolPermiso() {
        listaRoles = new ArrayList<>();
        listaPermisos = new ArrayList<>();
    }

    public void agregarRol(C2_Rol rol) {
        if (listaRoles.contains(rol)) {
            System.out.println("El rol '" + rol.getNombreRol() + "' ya existe en Roles.");
        } else {
            listaRoles.add(rol);
            System.out.println("Rol '" + rol.getNombreRol() + "' agregado correctamente a Roles.");
        }
    }

    public void editarRol(String nombreActual, String nuevoNombre, String nuevaDescripcion) {
        C2_Rol rol = buscarRolPorNombre(nombreActual);
        if (rol != null) {
            rol.setNombreRol(nuevoNombre);
            rol.setDescripcion(nuevaDescripcion);
            System.out.println("Rol modificado correctamente.");
        } else {
            System.out.println("Rol no encontrado para editar.");
        }
    }

    private C2_Rol buscarRolPorNombre(String nombre) {
        for (C2_Rol rol : listaRoles) {
            if (rol.getNombreRol().equalsIgnoreCase(nombre)) {
                return rol;
            }
        }
        return null;
    }

    public void eliminarRol(String nombre) {
        C2_Rol rol = buscarRolPorNombre(nombre);
        if (rol != null) {
            listaRoles.remove(rol);
            System.out.println("Rol '" + nombre + "' eliminado correctamente de Roles.");
        } else {
            System.out.println("Rol no encontrado para eliminar.");
        }
    }

    public void mostrarRoles() {
        if (listaRoles.isEmpty()) {
            System.out.println("ROLES EXISTENTES");
            System.out.println("No hay roles registrados.");
            return;
        }

        System.out.println("INFORMACION DE ROLES");
        for (C2_Rol rol : listaRoles) {
            System.out.println("Rol: " + rol.getNombreRol() + " - " + rol.getDescripcion());

            if (rol.getPermisos().isEmpty()) {
                System.out.println("No hay permisos asignados.");
            } else {
                System.out.println("Permisos asignados:");
                for (C2_Permiso permiso : rol.getPermisos()) {
                    System.out.println("- " + permiso.getNombrePermiso());
                }
            }
            System.out.println();
        }
    }

    public void agregarPermiso(C2_Permiso permiso) {
        if (listaPermisos.contains(permiso)) {
            System.out.println("El permiso '" + permiso.getNombrePermiso() + "'ya existe en Roles.");
        } else {
            listaPermisos.add(permiso);
            System.out.println("Permiso '" + permiso.getNombrePermiso() + "' agregado correctamente a Permisos.");
        }
    }

    public void eliminarPermiso(String nombrePermiso) {
        boolean eliminado = false;
        for (int i = 0; i < listaPermisos.size(); i++) {
            if (listaPermisos.get(i).getNombrePermiso().equalsIgnoreCase(nombrePermiso)) {
                listaPermisos.remove(i);
                eliminado = true;
                break;
            }
        }
        if (eliminado) {
            System.out.println("Permiso '" + nombrePermiso + "' eliminado correctamente.");
        } else {
            System.out.println("No se encontró el permiso '" + nombrePermiso + "' para eliminar.");
        }
    }

    public void editarPermiso(String nombrePermisoActual, String nuevoNombre, String nuevaDescripcion) {
        boolean encontrado = false;
        for (C2_Permiso permiso : listaPermisos) {
            if (permiso.getNombrePermiso().equalsIgnoreCase(nombrePermisoActual)) {
                if (nuevoNombre != null && nuevoNombre.length() > 3 && nuevoNombre.length() < 50) {
                    permiso.setNombrePermiso(nuevoNombre);
                } else {
                    System.out.println("El nuevo nombre debe contener entre 3 y 50 caracteres.");
                    return;
                }
                permiso.setDescripcion(nuevaDescripcion);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            System.out.println("Permiso '" + nombrePermisoActual + "' editado correctamente.");
        } else {
            System.out.println("No se encontró el permiso '" + nombrePermisoActual + "' para editar.");
        }
    }

    public void mostrarPermisos() {
        if (listaPermisos == null || listaPermisos.isEmpty()) {
            System.out.println("No hay permisos disponibles.");
        } else {
            System.out.println("Lista de permisos:");
            for (C2_Permiso permiso : listaPermisos) {
                System.out.println("- " + permiso.getNombrePermiso() + ": " + permiso.getDescripcion());
            }
        }
    }

    public ArrayList<C2_Rol> getRoles() {
        return listaRoles;
    }

    public ArrayList<C2_Permiso> getPermisos() {
        return listaPermisos;
    }
}
