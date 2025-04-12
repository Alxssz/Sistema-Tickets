package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class FlujoTrabajoTicket {
    private String nombre;
    private List<EstadoTicket> estados;
    private List<TransicionEstado> transiciones;
    private List<AccionAutomatica> acciones;

    public FlujoTrabajoTicket(String nombre) {
        this.nombre = nombre;
        this.estados = new ArrayList<>();
        this.transiciones = new ArrayList<>();
        this.acciones = new ArrayList<>();
    }

    public String getNombre() { return nombre; }

    public List<EstadoTicket> getEstados() { return estados; }

    public List<TransicionEstado> getTransiciones() { return transiciones; }

    public List<AccionAutomatica> getAcciones() { return acciones; }

    public void agregarEstado(EstadoTicket estado) {
        if (!estados.contains(estado)) {
            estados.add(estado);
        }
    }

    public void agregarTransicion(TransicionEstado transicion) {
        transiciones.add(transicion);
        transicion.getOrigen().agregarSiguienteEstado(transicion.getDestino());
    }

    public void agregarAccionAutomatica(AccionAutomatica accion) {
        acciones.add(accion);
    }

    public boolean esValido() {
        for (EstadoTicket estado : estados) {
            if (!estado.esFinal() && estado.getSiguientesEstados().isEmpty()) {
                return false; // debe tener al menos una transición de salida
            }
        }
        return true;
    }
}
