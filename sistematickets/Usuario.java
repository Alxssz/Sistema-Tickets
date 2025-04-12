package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends UsuarioSistema {
    private List<Ticket> ticketsCreados;
    
    public Usuario(String nombre, String correo, String nombreUsuario, String contrasena, String rol, Departamento departamento) {
        super(nombre, correo, nombreUsuario, contrasena, "Usuario", null);
    }

    public Ticket crearTicket(String titulo, String descripcion, Departamento departamento, String prioridad,
            EstadoTicket estado, List<Tecnico> tecnicosAsignados, String comentario, String adjunto,
            String fechaCreacion, String fechaActualizacion) {

        Ticket ticket = new Ticket(titulo, descripcion, departamento, prioridad, estado,
                tecnicosAsignados, comentario, adjunto, fechaCreacion, fechaActualizacion);

        System.out.println("Ticket creado con ID: " + ticket.getId());
        return ticket;
    }
    
    
        public List<Ticket> filtrarTickets(String estado, String prioridad) {
        List<Ticket> ticketsFiltrados = new ArrayList<>();
        
        for (Ticket ticket : ticketsCreados) {
            if (ticket.getEstado().equals(estado) && ticket.getPrioridad().equals(prioridad)) {
                ticketsFiltrados.add(ticket);
            }else {System.out.println("No hay tickets creados");}
        }
        
        return ticketsFiltrados;
    }

    public void mostrarTickets() {
        for (Ticket ticket : ticketsCreados) {
            ticket.mostrarDatosTicket();
        }
    }
    
    public void agregarNotaATicket(Ticket ticket, String contenido, String archivoAdjunto) {
    ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
    // Aquí podrías agregar una simulación de envío de notificación
    System.out.println("Se notifico a los involucrados del ticket. (clase usuario)");
}


    @Override
    public String getRol() {
        return "Usuario";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Usuario");
    }
}
