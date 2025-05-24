package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C7_SistemaGestionTickets {

    private List<C7_Ticket> tickets;
    private List<C2_Tecnico> tecnicos;

    public C7_SistemaGestionTickets() {
        tickets = new ArrayList<>();
        tecnicos = new ArrayList<>();
    }

    // Crear ticket
    public C7_Ticket crearTicket(String titulo, String descripcion, C3_Departamento departamento, String prioridad,
            C5_EstadoTicket estado, String fechaCreacion) {
        C7_Ticket nuevoTicket = new C7_Ticket(titulo, descripcion, departamento, prioridad, estado, fechaCreacion);
        tickets.add(nuevoTicket);
        return nuevoTicket;
    }

    // Asignar técnico a ticket
    public void asignarTecnico(C7_Ticket ticket, C2_Tecnico... tecnicos) {
        if (ticket != null && tecnicos != null) {
            for (C2_Tecnico tecnico : tecnicos) {
                ticket.agregarTecnico(tecnico);  // Llamar a agregarTecnico para cada técnico
            }
            ticket.mostrarDatosTicket();  // Mostrar los datos del ticket después de asignar los técnicos
        }
    }

    // Actualizar ticket
    public void actualizarTicket(C7_Ticket ticket, String comentario, String adjunto) {
        ticket.agregarComentario(comentario);
        ticket.agregarAdjunto(adjunto);
        System.out.println("Se actualizo " + ticket);
    }

    // Cambiar estado del ticket
    public void cambiarEstadoTicket(C7_Ticket ticket, C5_EstadoTicket estado) {
        ticket.setEstado(estado);
    }

    // Mostrar todos los tickets
    public void mostrarTodosTickets() {
        for (C7_Ticket ticket : tickets) {
            ticket.mostrarDatosTicket();
        }
    }

    public void consultarSolicitudesPendientes(C2_Usuario usuario) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Mis tickets");
        System.out.println("");
        usuario.mostrarTickets();
    }

    public void consultarSolicitudesPendientes(C2_Tecnico tecnico) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Tickets pendientes del tecnico " + tecnico.getNombre());
        System.out.println("");
        tecnico.mostrarTicketsAsignados();
    }

    public void consultarSolicitudesPendientes(C2_Administrador administrador) {
        System.out.println("-----------------------------------------------------");
        System.out.println("Tickets pendientes");
        System.out.println("");
        administrador.mostrarTodosTickets();
    }
}
