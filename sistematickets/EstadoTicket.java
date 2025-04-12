package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class EstadoTicket {
    private String nombre;
    private String descripcion;
    private boolean esEstadoFinal;
    private List<EstadoTicket> siguientesEstados;

    public EstadoTicket(String nombre, String descripcion, boolean esEstadoFinal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esEstadoFinal = esEstadoFinal;
        this.siguientesEstados = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public boolean esFinal() { return esEstadoFinal; }
    public List<EstadoTicket> getSiguientesEstados() { return siguientesEstados; }

    public void agregarSiguienteEstado(EstadoTicket estado) {
        if (!siguientesEstados.contains(estado)) {
            siguientesEstados.add(estado);
        }
    }

    public void eliminarSiguienteEstado(EstadoTicket estado) {
        siguientesEstados.remove(estado);
    }

    @Override
    public String toString() {
        return  nombre;
    }
    
    
}
