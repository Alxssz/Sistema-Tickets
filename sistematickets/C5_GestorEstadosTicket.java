package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C5_GestorEstadosTicket {

    private List<C5_EstadoTicket> estados;

    public C5_GestorEstadosTicket() {
        this.estados = new ArrayList<>();
    }

    // agregar un nuevo estado
    public boolean agregarEstado(C5_EstadoTicket nuevoEstado) {
        for (C5_EstadoTicket estado : estados) {
            if (estado.getNombre().equalsIgnoreCase(nuevoEstado.getNombre())) {
                return false;
            }
        }
        estados.add(nuevoEstado);
        return true;
    }

    // Método para modificar un estado 
    public boolean modificarEstado(String nombreActual, C5_EstadoTicket modificado) {
        C5_EstadoTicket existente = buscarEstadoPorNombre(nombreActual);
        if (existente == null) {
            return false;
        }
        if (!nombreActual.equalsIgnoreCase(modificado.getNombre()) && nombreDuplicado(modificado.getNombre())) {
            return false;
        }

        // Actualizar datos del estado existente
        existente.setNombre(modificado.getNombre());
        existente.setDescripcion(modificado.getDescripcion());
        existente.setEsEstadoFinal(modificado.esFinal());
        existente.setSiguientesEstados(modificado.getSiguientesEstados());

        return true;
    }

    // Método para eliminar un estado 
    public boolean eliminarEstado(String nombre) {
        C5_EstadoTicket estado = buscarEstadoPorNombre(nombre);
        if (estado == null || estaEnUso(estado)) {
            return false;
        }
        estados.remove(estado);
        return true;
    }

    // Busca un estado por nombre
    public C5_EstadoTicket buscarEstadoPorNombre(String nombre) {
        for (C5_EstadoTicket e : estados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    // si hay otro estado con el mismo nombre
    private boolean nombreDuplicado(String nombre) {
        return estados.stream()
                .anyMatch(e -> e.getNombre().equalsIgnoreCase(nombre));
    }

    // simulado
    private boolean estaEnUso(C5_EstadoTicket estado) {

        return false;
    }

    // lista completa de estados
    public List<C5_EstadoTicket> getEstados() {
        return estados;
    }

    // estados y sus estados siguientes
    public static void mostrarEstados(C5_GestorEstadosTicket gestor) {
        List<C5_EstadoTicket> estados = gestor.getEstados();
        System.out.println("ESTADOS ACTUALES DEL SISTEMA:");
        for (C5_EstadoTicket estado : estados) {
            System.out.println("Estado: " + estado.getNombre() + ", Final: " + estado.esFinal());
            if (!estado.getSiguientesEstados().isEmpty()) {
                System.out.println("Estados siguientes:");
            } else {
                System.out.println("Estado siguiente no establecido");
            }
            for (C5_EstadoTicket siguiente : estado.getSiguientesEstados()) {
                System.out.println("- " + siguiente.getNombre());
            }
            System.out.println();
        }
    }
}
