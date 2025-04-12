package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class Administrador extends UsuarioSistema {

    private List<Ticket> todosTickets;

    private SistemaRolPermiso sistema;

    // Constructor corregido
    public Administrador(String nombre, String correo, String nombreUsuario, String contrasena, Departamento departamento, SistemaRolPermiso sistema) {
        super(nombre, correo, nombreUsuario, contrasena, "Administrador", departamento);
        this.sistema = sistema;
    }

    // Método para gestionar roles
    public void gestionarRoles() {
        System.out.println("Gestionando roles y permisos.");
    }

    // Método para agregar un rol al sistema
    public void agregarRol(Rol rol) {
        sistema.agregarRol(rol);
        System.out.println("Rol '" + rol.getNombreRol() + "' agregado al sistema.");
    }

    // Método para eliminar un rol del sistema
    public void eliminarRol(Rol rol) {
        sistema.eliminarRol(rol);
        System.out.println("Rol '" + rol.getNombreRol() + "' eliminado del sistema.");
    }

    // Método para agregar un permiso a un rol
    public void asignarPermisoARol(Rol rol, Permiso permiso) {
        rol.agregarPermiso(permiso);
        System.out.println("Permiso '" + permiso.getNombrePermiso() + "' asignado al rol '" + rol.getNombreRol() + "'.");
    }

    // Métodos para gestionar estados
    public void crearEstado(GestorEstadosTicket gestor, EstadoTicket nuevo) {
        if (gestor.agregarEstado(nuevo)) {
            System.out.println("Estado '" + nuevo.getNombre() + "' creado existosamente.");
        } else {
            System.out.println("Error al crear estado.");
        }
    }

    public void modificarEstado(GestorEstadosTicket gestor, String nombreActual, EstadoTicket nuevo) {
        if (gestor.modificarEstado(nombreActual, nuevo)) {
            System.out.println("Estado " + nombreActual + " modificado.");
        } else {
            System.out.println("Error al modificar estado.");
        }
    }

    public void eliminarEstado(GestorEstadosTicket gestor, String nombre) {
        if (gestor.eliminarEstado(nombre)) {
            System.out.println("Estado " + nombre + " eliminado.");
        } else {
            System.out.println("No se puede eliminar. Puede estar en uso.");
        }
    }

    public List<Ticket> filtrarTickets(String estado, String prioridad, Departamento departamento) {
        List<Ticket> ticketsFiltrados = new ArrayList<>();

        for (Ticket ticket : todosTickets) {
            if (ticket.getEstado().equals(estado) && ticket.getPrioridad().equals(prioridad) && ticket.getDepartamento().equals(departamento)) {
                ticketsFiltrados.add(ticket);
            }
        }

        return ticketsFiltrados;
    }

    public void mostrarTodosTickets() {
        for (Ticket ticket : todosTickets) {
            ticket.mostrarDatosTicket();
        }
    }

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

    public void agregarNotaATicket(Ticket ticket, String contenido, String archivoAdjunto) {
        ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
        // Aquí podrías agregar una simulación de envío de notificación
        System.out.println("Se notificó a los involucrados del ticket. (clase admi)" );
        System.out.println("");
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Administrador");
    }

    // Guarda el administrador en el archivo
    public void registrarUsuario() {
        RegistroUsuarios registro = new RegistroUsuarios();
        registro.guardarUsuario(this); // Lo guarda en usuarios.txt
    }
}
