package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C2_Usuario extends C2_UsuarioSistema {

    private List<C7_Ticket> ticketsCreados;

    public C2_Usuario(String nombre, String correo, String nombreUsuario, String contrasena, C2_Rol rol, C3_Departamento departamento) {
        super(nombre, correo, nombreUsuario, contrasena, rol, null);
        this.ticketsCreados = new ArrayList<>();
    }

    public C7_Ticket crearTicket(String titulo, String descripcion, C3_Departamento departamento, String prioridad,
            C5_EstadoTicket estado, String fechaCreacion) {

        C7_Ticket ticket = new C7_Ticket(titulo, descripcion, departamento, prioridad, estado, fechaCreacion);

        ticketsCreados.add(ticket); // 👈 Agregado

        System.out.println("Ticket creado con ID: " + ticket.getId());
        return ticket;
    }

    public List<C7_Ticket> filtrarTickets(String estado, String prioridad) {
        List<C7_Ticket> ticketsFiltrados = new ArrayList<>();

        for (C7_Ticket ticket : ticketsCreados) {
            if (ticket.getEstado().equals(estado) && ticket.getPrioridad().equals(prioridad)) {
                ticketsFiltrados.add(ticket);
            }
        }

        if (ticketsFiltrados.isEmpty()) {
            System.out.println("No hay tickets que coincidan con los criterios.");
        }

        return ticketsFiltrados;
    }

    public void mostrarTickets() {
        if (ticketsCreados.isEmpty()) {
            System.out.println("No hay tickets creados.");
        } else {
            for (C7_Ticket ticket : ticketsCreados) {
                ticket.mostrarDatosTicket();
            }
        }
    }

    public void agregarNotaATicket(C7_Ticket ticket, String contenido, String archivoAdjunto) {
        ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
        System.out.println("Se notifico a los involucrados del ticket. (clase usuario)");
    }

    @Override
    public C2_Rol getRol() {
        return rol;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Usuario");
    }
}
