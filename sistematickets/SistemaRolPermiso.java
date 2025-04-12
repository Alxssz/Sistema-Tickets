package sistematickets;

import java.util.ArrayList;

public class SistemaRolPermiso {
    private ArrayList<Rol> roles;
    private ArrayList<Permiso> permisos;

    public SistemaRolPermiso() {
        roles = new ArrayList<>();
        permisos = new ArrayList<>();
    }

    public void agregarRol(Rol rol) {
        roles.add(rol);
    }

    public void eliminarRol(Rol rol) {
        roles.remove(rol);
    }

    public void agregarPermiso(Permiso permiso) {
        permisos.add(permiso);
    }

    public ArrayList<Rol> getRoles() {
        return roles;
    }

    public ArrayList<Permiso> getPermisos() {
        return permisos;
    }
}
