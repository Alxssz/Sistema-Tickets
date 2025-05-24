package sistematickets;

import java.util.ArrayList;

public class C3_GestionDepartamento {

    private ArrayList<C3_Departamento> departamentos;
    private C2_UsuarioSistema administrador;

    public C3_GestionDepartamento(C2_UsuarioSistema administrador) {
        this.departamentos = new ArrayList<>();
        this.administrador = administrador;
    }

    public void registrarDepartamento(String nombre, String descripcion, C2_UsuarioSistema... tecnicos) {
        if (!"Administrador".equals(administrador.getRol().getNombreRol())) {
            System.out.println("Accion no permitida: El usuario no tiene rol de administrador.");
            return;
        }

        if (nombre.length() < 3 || nombre.length() > 50) {
            System.out.println("El nombre del departamento debe tener entre 3 y 50 caracteres.");
            return;
        }

        // Verificar si ya existe un departamento con el mismo nombre
        for (C3_Departamento dep : departamentos) {
            if (dep.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ya existe un departamento con ese nombre.");
                return;
            }
        }

        C3_Departamento nuevoDepartamento = new C3_Departamento(nombre, descripcion);

        // Agregar los tecnicos al departamento, solo si son instancia de C2_Tecnico
        for (C2_UsuarioSistema tecnico : tecnicos) {
            if (tecnico != null) {
                if (tecnico instanceof C2_Tecnico c2_Tecnico) {
                    nuevoDepartamento.asignarTecnico(c2_Tecnico);
                } else {
                    System.out.println("El usuario " + tecnico.getNombreUsuario() + " no es un tecnico valido.");
                }
            } else {
                System.out.println("El usuario tecnico es null, no es un tecnico valido.");
            }
        }
        departamentos.add(nuevoDepartamento);
        System.out.println("Departamento '" + nombre + "' registrado correctamente.");

    }
// Metodo para eliminar un departamento de la lista

    public void eliminarDepartamento(String nombre) {
        for (int i = 0; i < departamentos.size(); i++) {
            C3_Departamento dep = departamentos.get(i);
            if (dep.getNombre().equalsIgnoreCase(nombre)) {
                departamentos.remove(i);
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

        System.out.println("INFORMACION DE DEPARTAMENTOS EXISTENTES");
        for (C3_Departamento dep : departamentos) {
            System.out.println("Departamento: " + dep.getNombre() + " - " + dep.getDescripcion());
            System.out.println("Tecnicos asignados:");
            for (C2_Tecnico tecnico : dep.getTecnicos()) {
                System.out.println("- " + tecnico.getNombre() + " (" + tecnico.getCorreo() + ")");
            }
            System.out.println();
        }
    }
}
