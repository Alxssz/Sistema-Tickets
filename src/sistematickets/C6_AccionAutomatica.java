package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class C6_AccionAutomatica {

    private C5_EstadoTicket estado;
    private String descripcion;

    // Lista estática compartida por todas las instancias (puede ser no estática si lo deseas por instancia)
    private static List<C6_AccionAutomatica> listaAcciones = new ArrayList<>();

    public C6_AccionAutomatica(C5_EstadoTicket estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public C5_EstadoTicket getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void ejecutar() {
        System.out.println("Ejecutando acción automática para estado: " + estado.getNombre()
                + " - " + descripcion);
    }

    // Método para agregar una acción a la lista
    public static void agregarAccion(C6_AccionAutomatica accion) {
        if (!listaAcciones.contains(accion)) {
            listaAcciones.add(accion);
            System.out.println("Accion agregada: " + accion.getDescripcion());
        } else {
            System.out.println("La accion ya existe para ese estado.");
        }
    }

    // Método para mostrar todas las acciones
    public static void mostrarAcciones() {
        if (listaAcciones.isEmpty()) {
            System.out.println("No hay acciones registradas.");
        } else {
            System.out.println("Acciones automaticas registradas:");
            for (C6_AccionAutomatica accion : listaAcciones) {
                System.out.println("- Estado: " + accion.getEstado().getNombre() +
                        " | Accion: " + accion.getDescripcion());
            }
        }
    }

    // Método para eliminar una acción
    public static void eliminarAccion(C6_AccionAutomatica accion) {
        if (listaAcciones.remove(accion)) {
            System.out.println("Accion eliminada: " + accion.getDescripcion());
        } else {
            System.out.println("No se encontro la accion.");
        }
    }

    // Getter de la lista si necesitas acceder desde otra clase
    public static List<C6_AccionAutomatica> getListaAcciones() {
        return listaAcciones;
    }
}
