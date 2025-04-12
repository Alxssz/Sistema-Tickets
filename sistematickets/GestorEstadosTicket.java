package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class GestorEstadosTicket {

    private List<EstadoTicket> estados;

    public GestorEstadosTicket() {
        this.estados = new ArrayList<>();
    }

public boolean agregarEstado(EstadoTicket nuevoEstado) {
    for (EstadoTicket estado : estados) {
        if (estado.getNombre().equalsIgnoreCase(nuevoEstado.getNombre())) {
            return false; // ya existe un estado con ese nombre
        }
    }
    estados.add(nuevoEstado);
    return true;
}

    public boolean modificarEstado(String nombreActual, EstadoTicket modificado) {
        EstadoTicket existente = buscarEstadoPorNombre(nombreActual);
        if (existente == null) {
            return false;
        }
        if (!nombreActual.equals(modificado.getNombre()) && nombreDuplicado(modificado.getNombre())) {
            return false;
        }

        // Se actualiza todo el objeto
        existente = modificado;
        return true;
    }

    public boolean eliminarEstado(String nombre) {
        EstadoTicket estado = buscarEstadoPorNombre(nombre);
        if (estado == null || estaEnUso(estado)) {
            return false;
        }
        estados.remove(estado);
        return true;
    }

    public EstadoTicket buscarEstadoPorNombre(String nombre) {
        for (EstadoTicket e : estados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null;
    }

    private boolean nombreDuplicado(String nombre) {
        return estados.stream().anyMatch(e -> e.getNombre().equalsIgnoreCase(nombre));
    }

    private boolean estaEnUso(EstadoTicket estado) {
        // Simulación de verificación (debería consultarse la base de tickets activos)
        return false;
    }

    public List<EstadoTicket> getEstados() {
        return estados;
    }

    public static void mostrarEstados(GestorEstadosTicket gestor) {
        List<EstadoTicket> estados = gestor.getEstados();
        System.out.println("");
        System.out.println("ESTADOS ACTUALES DEL SISTEMA:");
        for (EstadoTicket estado : estados) {
            System.out.println("Estado: " + estado.getNombre() + ", Final: " + estado.esFinal());
            if (!estado.getSiguientesEstados().isEmpty()) {
                System.out.println("Estados siguientes: ");
                for (EstadoTicket siguiente : estado.getSiguientesEstados()) {
                    System.out.println("- " + siguiente.getNombre());
                    System.out.println("");
                }
            }
        }
    }
}
