package sistematickets;

import java.util.ArrayList;

public class GestionDepartamento {

    private ArrayList<Departamento> departamentos;
    private UsuarioSistema administrador;

    public GestionDepartamento(UsuarioSistema administrador) {
        this.departamentos = new ArrayList<>();
        this.administrador = administrador;
    }

    public void registrarDepartamento(String nombre, String descripcion, UsuarioSistema... tecnicos) {
        if (!administrador.getRol().equals("Administrador")) {
            System.out.println("Acción no permitida: El usuario no tiene rol de administrador.");
            return;
        }

        if (nombre.length() < 3 || nombre.length() > 50) {
            System.out.println("El nombre del departamento debe tener entre 3 y 50 caracteres.");
            return;
        }

        // Verificar si ya existe un departamento con el mismo nombre
        for (Departamento dep : departamentos) {
            if (dep.getNombre().equals(nombre)) {
                System.out.println("Ya existe un departamento con ese nombre.");
                return;
            }
        }

        Departamento nuevoDepartamento = new Departamento(nombre, descripcion);

        // Agregar los técnicos al departamento
        for (UsuarioSistema tecnico : tecnicos) {
            nuevoDepartamento.agregarTecnico(tecnico);
        }

        departamentos.add(nuevoDepartamento);
        System.out.println("Departamento '" + nombre + "' registrado correctamente.");
    }

    // Método para eliminar un departamento de la lista
    public void eliminarDepartamento(String nombre) {
        for (int i = 0; i < departamentos.size(); i++) {
            Departamento dep = departamentos.get(i);
            if (dep.getNombre().equals(nombre)) {
                departamentos.remove(i); // Eliminar el departamento de la lista
                System.out.println("Departamento '" + nombre + "' eliminado correctamente.");
                return;
            }
        }
        System.out.println("Departamento no encontrado.");
    }

    public void mostrarDepartamentosConTecnicos() {
        if (departamentos.isEmpty()) {
            System.out.println("No hay departamentos registrados.");
            return;
        }

        for (Departamento dep : departamentos) {
            System.out.println("");
            System.out.println("INFORMACION DE DEPARTAMENTOS EXISTENTES");
            System.out.println("Departamento: " + dep.getNombre() + " - " + dep.getDescripcion());
            System.out.println("Tecnicos asignados:");
            for (UsuarioSistema tecnico : dep.getTecnicos()) {
                System.out.println("- " + tecnico.getNombre() + " (" + tecnico.getCorreo() + ")");
            }
            System.out.println();
        }
    }
}