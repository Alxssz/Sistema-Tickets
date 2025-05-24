package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C2_Administrador extends C2_UsuarioSistema {

    private List<C7_Ticket> todosLosTickets;
    private C2_SistemaRolPermiso gestionRol;

    public C2_Administrador(String nombre, String correo, String nombreUsuario, String contrasena,
            C2_Rol rol, C3_Departamento departamento, C2_SistemaRolPermiso sistema) {
        super(nombre, correo, nombreUsuario, contrasena, rol, departamento);
        this.gestionRol = sistema;
    }

    //Roles y permisos
    public void agregarRol(C2_Rol rol) {
        gestionRol.agregarRol(rol);
    }

    public void eliminarRol(String nombreRol) {
        gestionRol.eliminarRol(nombreRol);
    }

    public void mostrarRoles() {
        gestionRol.mostrarRoles();
    }

    public void editarRol(String nombreActual, String nuevoNombre, String nuevaDescripcion) {
        gestionRol.editarRol(nombreActual, nuevoNombre, nuevaDescripcion);
    }

        //Roles y permisos
    public void agregarPermiso(C2_Permiso permiso) {
        gestionRol.agregarPermiso(permiso);
    }

    public void eliminarPermiso(String nombrePermiso) {
        gestionRol.eliminarPermiso(nombrePermiso);
    }

    public void mostrarPermisos() {
        gestionRol.mostrarPermisos();
    }

    public void editarPermiso (String nombreActual, String nuevoNombre, String nuevaDescripcion) {
        gestionRol.editarPermiso(nombreActual, nuevoNombre, nuevaDescripcion);
    }
 
    
    public void asignarPermisoARol(C2_Rol rol, C2_Permiso permiso) {
        rol.agregarPermiso(permiso);
        System.out.println("Permiso '" + permiso.getNombrePermiso() + "' asignado al rol '" + rol.getNombreRol() + "'.");
    }

    public void quitarPermisoARol(C2_Rol rol, C2_Permiso permiso) {
        rol.quitarPermiso(permiso);
        System.out.println("Permiso '" + permiso.getNombrePermiso() + "' quitado del rol '" + rol.getNombreRol() + "'.");
    }

    //Estados
    public void crearEstado(C5_GestorEstadosTicket gestor, C5_EstadoTicket nuevo) {
        if (gestor.agregarEstado(nuevo)) {
            System.out.println("Estado '" + nuevo.getNombre() + "' creado existosamente.");
        } else {
            System.out.println("Error al crear estado.");
        }
    }

    public void modificarEstado(C5_GestorEstadosTicket gestor, String nombreActual, C5_EstadoTicket nuevo) {
        if (gestor.modificarEstado(nombreActual, nuevo)) {
            System.out.println("Estado " + nombreActual + " modificado.");
        } else {
            System.out.println("Error al modificar estado.");
        }
    }

    public void eliminarEstado(C5_GestorEstadosTicket gestor, String nombre) {
        if (gestor.eliminarEstado(nombre)) {
            System.out.println("Estado " + nombre + " eliminado.");
        } else {
            System.out.println("No se puede eliminar. Puede estar en uso.");
        }
    }

// Tickets
    public List<C7_Ticket> filtrarTickets(String estado, String prioridad, C3_Departamento departamento) {
        List<C7_Ticket> ticketsFiltrados = new ArrayList<>();
        for (C7_Ticket ticket : todosLosTickets) {
            if (ticket.getEstado().equals(estado)
                    && ticket.getPrioridad().equals(prioridad)
                    && ticket.getDepartamento().equals(departamento)) {
                ticketsFiltrados.add(ticket);
            }
        }
        return ticketsFiltrados;
    }

    public void mostrarTodosTickets() {
        for (C7_Ticket ticket : todosLosTickets) {
            ticket.mostrarDatosTicket();
        }
    }

    public void cambiarEstadoTicket(C7_Ticket ticket, C5_EstadoTicket nuevoEstado, String comentario) {
        ticket.cambiarEstado(nuevoEstado);
        if (comentario != null && !comentario.isEmpty()) {
            ticket.setComentario(ticket.getComentario() + "\n[Comentario]: " + comentario);
        }
        System.out.println("Estado del " + ticket + " cambiado a: " + nuevoEstado.getNombre());
        if (comentario != null && !comentario.isEmpty()) {
            System.out.println("Comentario: " + comentario + "\n");
        }
    }

    public void agregarNotaATicket(C7_Ticket ticket, String contenido, String archivoAdjunto) {
        ticket.agregarNota(contenido, archivoAdjunto, this.getNombre());
        System.out.println("Se notificó a los involucrados del ticket. (clase admi)");
        System.out.println("");
    }

    //Información y Registro
    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Rol: Administrador");
    }

    @Override
    public C2_Rol getRol() {
        return rol;
    }

    public void registrarUsuario() {
        C4_RegistroUsuarios registro = new C4_RegistroUsuarios();
        registro.agregarUsuario(this);
    }
}
