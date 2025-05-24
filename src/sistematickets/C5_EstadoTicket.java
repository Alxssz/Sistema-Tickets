package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C5_EstadoTicket {

    // Atributos
    private String nombre;
    private String descripcion;
    private boolean esEstadoFinal;
    private List<C5_EstadoTicket> siguientesEstados;

    // Constructor
    public C5_EstadoTicket(String nombre, String descripcion, boolean esEstadoFinal) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.esEstadoFinal = esEstadoFinal;
        this.siguientesEstados = new ArrayList<>();
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean esFinal() {
        return esEstadoFinal;
    }

    public List<C5_EstadoTicket> getSiguientesEstados() {
        return siguientesEstados;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEsEstadoFinal(boolean esEstadoFinal) {
        this.esEstadoFinal = esEstadoFinal;
    }

    public void setSiguientesEstados(List<C5_EstadoTicket> siguientesEstados) {
        this.siguientesEstados = siguientesEstados;
    }

    // Métodos para manejar siguientes estados
    public void agregarSiguienteEstado(C5_EstadoTicket estado) {
        if (!siguientesEstados.contains(estado)) {
            siguientesEstados.add(estado);
        }
    }

    public void eliminarSiguienteEstado(C5_EstadoTicket estado) {
        siguientesEstados.remove(estado);
    }

    // toString para mostrar el nombre del estado
    @Override
    public String toString() {
        return nombre;
    }
}
