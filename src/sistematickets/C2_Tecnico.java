package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C2_Tecnico extends C2_UsuarioSistema {

    private C2_Rol rol;
    private C3_Departamento departamento;
    private List<C7_Ticket> ticketsAsignados;

    // Constructor
    public C2_Tecnico(String nombre, String correo, String nombreUsuario, String contrasena, C2_Rol rol, C3_Departamento departamento) {
        super(nombre, correo, nombreUsuario, contrasena, rol, departamento);
        this.ticketsAsignados = new ArrayList<>();
    }

    // Getters
    @Override
    public C2_Rol getRol() {
        return rol;
    }

    public void cambiarEstadoTicket(C7_Ticket ticket, C5_EstadoTicket nuevoEstado, String comentario) {
        C5_EstadoTicket estadoAnterior = ticket.getEstado(); // Guardar estado antes del cambio
        ticket.cambiarEstado(nuevoEstado); // Cambiar estado

        // Agregar comentario si no es nulo ni vacío
        if (comentario != null && !comentario.isEmpty()) {
            ticket.setComentario(ticket.getComentario() + "\n[Comentario]: " + comentario);
        }

        System.out.println("Se cambio el estado del ticket " + ticket.getId()
                + " de [" + estadoAnterior.getNombre() + "] a [" + nuevoEstado.getNombre() + "]");

        if (comentario != null && !comentario.isEmpty()) {
            System.out.println("Comentario: " + comentario + "\n");
        }
    }

    public void asignarTicket(C7_Ticket ticket) {
        ticketsAsignados.add(ticket);
    }

    public void agregarNotaATicket(C7_Ticket ticket, String contenido, String archivoAdjunto) {
        ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
        System.out.println("Se notifico a los involucrados del ticket. (clase tecnico)");
        System.out.println("");
    }

    public List<C7_Ticket> filtrarTickets(String estado, String prioridad) {
        List<C7_Ticket> ticketsFiltrados = new ArrayList<>();

        for (C7_Ticket ticket : ticketsAsignados) {
            if (ticket.getEstado().equals(estado) && ticket.getPrioridad().equals(prioridad)) {
                ticketsFiltrados.add(ticket);
            }
        }

        return ticketsFiltrados;
    }

    public void mostrarTicketsAsignados() {
        if (ticketsAsignados.isEmpty()) {
            System.out.println("No hay tickets asignados.");
        } else {
            for (C7_Ticket ticket : ticketsAsignados) {
                ticket.mostrarTicketPendientes();
            }
        }
    }

    // Métodos heredados
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Tecnico");
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
