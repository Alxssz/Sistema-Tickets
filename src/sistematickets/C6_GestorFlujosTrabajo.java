package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C6_GestorFlujosTrabajo {

    private String nombreFlujo;
    private List<C5_EstadoTicket> estadosInvolucrados;
    private List<C5_TransicionEstado> transicionesInvolucradas;
    private List<C6_AccionAutomatica> acciones;  // Renombrado para evitar duplicado

    // Constructor opcional para inicializar variables si quieres mantener estado
    public C6_GestorFlujosTrabajo(String nombreFlujo) {
        this.nombreFlujo = nombreFlujo;
        this.estadosInvolucrados = new ArrayList<>();
        this.transicionesInvolucradas = new ArrayList<>();
        this.acciones = new ArrayList<>();
    }

    // Agregar estados a un flujo pasando una lista
    public void agregarEstados(C5_EstadoTicket estado) {
        if (estadosInvolucrados.contains(estado)) {
            System.out.println("El estado '" + estado.getNombre() + "' ya existe.");
        } else {
            estadosInvolucrados.add(estado);
            System.out.println("Estado '" + estado.getNombre() + "' agregado correctamente al flujo.");
        }
    }

    // Agregar transiciones pasando una lista
    public void agregarTransiciones(C5_TransicionEstado transicion) {
        if (transicionesInvolucradas.contains(transicion)) {
            System.out.println("La transicion ya existe.");
        } else {
            transicionesInvolucradas.add(transicion);
            System.out.println("Transicion agregada correctamente al flujo: " + transicion.mostrarTransicion());
        }
    }

    // Agregar acciones automáticas pasando una lista
    public void agregarAccionAutomatica(C6_AccionAutomatica accion) {
        if (acciones.contains(accion)) {
            System.out.println("La accion ya existe para este estado.");
        } else {
            acciones.add(accion);
            System.out.println("Accion agregada correctamente: " + accion.getDescripcion());
        }
    }

    public void mostrarEstados() {
        if (estadosInvolucrados.isEmpty()) {
            System.out.println("No hay estados en el flujo.");
        } else {
            System.out.println("Estados incolucrados del flujo [" + nombreFlujo + "]:");
            for (C5_EstadoTicket estado : estadosInvolucrados) {
                System.out.println("- " + estado.getNombre());
            }
        }
    }

    public void mostrarTransiciones() {
        if (transicionesInvolucradas.isEmpty()) {
            System.out.println("No hay transiciones en el flujo.");
        } else {
            System.out.println("Transiciones del flujo [" + nombreFlujo + "]:");
            for (C5_TransicionEstado transicion : transicionesInvolucradas) {
                System.out.println("- " + transicion.mostrarTransicion());
            }
        }
    }

    public void mostrarAccionesAutomaticas() {
        if (acciones.isEmpty()) {
            System.out.println("No hay acciones automaticas en el flujo.");
        } else {
            System.out.println("Acciones automaticas del flujo [" + nombreFlujo + "]:");
            for (C6_AccionAutomatica accion : acciones) {
                System.out.println("- Cuando el estado es : " + accion.getEstado().getNombre() + " | Accion: " + accion.getDescripcion());
            }
        }
    }
}
