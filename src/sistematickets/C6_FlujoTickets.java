package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C6_FlujoTickets {

    private String nombre;
    private List<C5_EstadoTicket> estados;
    private List<C5_TransicionEstado> transiciones;
    private List<C6_AccionAutomatica> acciones;
    private boolean enviarNotificacion;

    // Constructor sin notificacion especifica, se inicializa en false
    public C6_FlujoTickets(String nombre) {
        this.nombre = nombre;
        this.estados = new ArrayList<>();
        this.transiciones = new ArrayList<>();
        this.acciones = new ArrayList<>();
        this.enviarNotificacion = false;  // CORREGIDO
    }

    // Constructor con lista de estados y flag de notificacion
    public C6_FlujoTickets(String nombre, List<C5_EstadoTicket> estados, boolean notificar) {
        this.nombre = nombre;
        this.estados = new ArrayList<>(estados);
        this.transiciones = new ArrayList<>();
        this.acciones = new ArrayList<>();
        setEnviarNotificacion(notificar);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public List<C5_EstadoTicket> getEstados() {
        return estados;
    }

    public List<C5_TransicionEstado> getTransiciones() {
        return transiciones;
    }

    public List<C6_AccionAutomatica> getAcciones() {
        return acciones;
    }

    // Métodos para agregar elementos
    public void agregarEstado(C5_EstadoTicket estado) {
        if (!estados.contains(estado)) {
            estados.add(estado);
        }
    }

    public void agregarTransicion(C5_TransicionEstado transicion) {
        transiciones.add(transicion);
        transicion.getOrigen().agregarSiguienteEstado(transicion.getDestino());
    }

    public void agregarAccionAutomatica(C6_AccionAutomatica accion) {
        acciones.add(accion);
    }

    // Validación: cada estado no final debe tener al menos una transición saliente
    public boolean esValido() {
        for (C5_EstadoTicket estado : estados) {
            if (!estado.esFinal() && estado.getSiguientesEstados().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstados(List<C5_EstadoTicket> estados) {
        this.estados = estados;
    }

    public void setTransiciones(List<C5_TransicionEstado> transiciones) {
        this.transiciones = transiciones;
    }

    public void setAcciones(List<C6_AccionAutomatica> acciones) {
        this.acciones = acciones;
    }

    // Getter y setter de notificación
    public boolean isEnviarNotificacion() {
        return enviarNotificacion;
    }

    public void setEnviarNotificacion(boolean enviarNotificacion) {
        this.enviarNotificacion = enviarNotificacion;
        if (enviarNotificacion) {
            System.out.println("Notificacion Enviada");
        } else {
            System.out.println("Notificacion No Enviada");
        }
    }

    @Override
    public String toString() {
        return nombre;
    }
}
