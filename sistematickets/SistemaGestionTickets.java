package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class SistemaGestionTickets {

    private List<Ticket> tickets;
    private List<Tecnico> tecnicos;

    public SistemaGestionTickets() {
        tickets = new ArrayList<>();
        tecnicos = new ArrayList<>();
    }

    // Crear ticket
    public Ticket crearTicket(String titulo, String descripcion, Departamento departamento, String prioridad,
            EstadoTicket estado, String comentario, String adjuntos, String fechaCreacion) {
        Ticket nuevoTicket = new Ticket(titulo, descripcion, departamento, prioridad, estado,
                new ArrayList<>(), comentario, adjuntos, fechaCreacion, fechaCreacion);
        tickets.add(nuevoTicket);
        return nuevoTicket;
    }

    // Asignar técnico a ticket
    public void asignarTecnico(Ticket ticket, Tecnico... tecnicos) {
        if (ticket != null && tecnicos != null) {
            for (Tecnico tecnico : tecnicos) {
                ticket.agregarTecnico(tecnico);  // Llamar a agregarTecnico para cada técnico
            }
            ticket.mostrarDatosTicket();  // Mostrar los datos del ticket después de asignar los técnicos
        }
    }

    // Actualizar ticket
    public void actualizarTicket(Ticket ticket, String comentario, String adjunto) {
        ticket.agregarComentario(comentario);
        ticket.agregarAdjunto(adjunto);
        System.out.println("Se actualizo " + ticket);
    }

    // Cambiar estado del ticket
    public void cambiarEstadoTicket(Ticket ticket, EstadoTicket estado) {
        ticket.setEstado(estado);
    }

    // Mostrar todos los tickets
    public void mostrarTodosTickets() {
        for (Ticket ticket : tickets) {
            ticket.mostrarDatosTicket();
        }
    }

    public void consultarSolicitudesPendientes(Usuario usuario) {
        // Mostrar solo los tickets creados por el usuario
        System.out.println("Mis tickets");
        usuario.mostrarTickets();
    }

    public void consultarSolicitudesPendientes(Tecnico tecnico) {
        // Mostrar tickets asignados al técnico de su departamento
        System.out.println("Tickets pendientes del tecnico " + tecnico.getNombre());
        tecnico.mostrarTickets();
    }

    public void consultarSolicitudesPendientes(Administrador administrador) {
        // Mostrar todos los tickets para el administrador
        System.out.println("Tickets pendientes");
        administrador.mostrarTodosTickets();
    }
}
