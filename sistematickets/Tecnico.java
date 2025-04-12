package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class Tecnico extends UsuarioSistema {

    private String rol;
    private Departamento departamento;
    private List<Ticket> ticketsAsignados;

    public Tecnico(String nombre, String correo, String nombreUsuario, String contrasena, String rol, Departamento departamento) {
        super(nombre, correo, nombreUsuario, contrasena, "Tecnico", departamento);
        this.rol = rol;
        this.ticketsAsignados = new ArrayList<>();
    }

    // Método específico para el Técnico
    public void gestionarTickets(Ticket ticket) {
        System.out.println("Gestionando ticket con ID: " + ticket.getId());
    }

    /* public void actualizarTicket(Ticket ticket, String comentario, String adjunto) {
        ticket.actualizarTicket(comentario, adjunto);
        System.out.println("Ticket actualizado con nuevo comentario y adjunto.");
    }*/
    public void cambiarEstadoTicket(Ticket ticket, EstadoTicket nuevoEstado, String comentario) {
        ticket.cambiarEstado(nuevoEstado);

        if (comentario != null && !comentario.isEmpty()) {
            ticket.setComentario(ticket.getComentario() + "\n[Comentario]: " + comentario);
        }

        System.out.println("Estado del " + ticket + " cambiado a: " + nuevoEstado.getNombre());
        if (comentario != null && !comentario.isEmpty()) {
            System.out.println("Comentario: " + comentario + "\n");
        }
    }

    public List<Ticket> filtrarTickets(String estado, String prioridad) {
        List<Ticket> ticketsFiltrados = new ArrayList<>();

        for (Ticket ticket : ticketsAsignados) {
            if (ticket.getEstado().equals(estado) && ticket.getPrioridad().equals(prioridad)) {
                ticketsFiltrados.add(ticket);
            }
        }

        return ticketsFiltrados;
    }

    public void mostrarTickets() {
        if (ticketsAsignados.isEmpty()) {
            System.out.println("No hay tickets asignados.");
        } else {
            for (Ticket ticket : ticketsAsignados) {
                ticket.mostrarTicketPendientes();
            }
        }
    }

    // Puedes tener un método para agregar tickets también
    public void asignarTicket(Ticket ticket) {
        ticketsAsignados.add(ticket);
    }

    public void agregarNotaATicket(Ticket ticket, String contenido, String archivoAdjunto) {
        ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
        System.out.println("Se notifico a los involucrados del ticket. (clae tecnico) ");
        System.out.println("");
    }

    @Override
    public String getRol() {
        return "Tecnico";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Tecnico");
    }
}
