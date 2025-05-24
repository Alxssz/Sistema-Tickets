package sistematickets;

import java.util.ArrayList;

public class C5_TransicionEstado {

    private ArrayList<String> listaTransiciones = new ArrayList<>();
    private C5_EstadoTicket origen;
    private C5_EstadoTicket destino;
    private String regla;

    public C5_TransicionEstado(C5_EstadoTicket origen, C5_EstadoTicket destino, String regla) {
        this.origen = origen;
        this.destino = destino;
        this.regla = regla;
    }

    public C5_EstadoTicket getOrigen() {
        return origen;
    }

    public C5_EstadoTicket getDestino() {
        return destino;
    }

    public String getRegla() {
        return regla;
    }

    public String mostrarTransicion() {
        return "De '" + origen.getNombre() + "' a '" + destino.getNombre() + "' con regla: " + regla;
    }

    // MÉTODOS PARA AGREGAR Y ELIMINAR TRANSICIONES EN TEXTO

    public void agregarTransicionTexto(String transicionTexto) {
        if (!listaTransiciones.contains(transicionTexto)) {
            listaTransiciones.add(transicionTexto);
            System.out.println("Transicion agregada: " + transicionTexto);
        } else {
            System.out.println("La transicion ya existe.");
        }
    }

    public void eliminarTransicionTexto(String transicionTexto) {
        if (listaTransiciones.remove(transicionTexto)) {
            System.out.println("Transicion eliminada: " + transicionTexto);
        } else {
            System.out.println("La transicion no existe.");
        }
    }

    public void mostrarTodasLasTransiciones() {
        if (listaTransiciones.isEmpty()) {
            System.out.println("No hay transiciones guardadas.");
        } else {
            System.out.println("Transiciones guardadas:");
            for (String t : listaTransiciones) {
                System.out.println("- " + t);
            }
        }
    }
}
