package sistematickets;

import java.util.HashMap;
import java.util.Map;

public class C3_GestorColas {

    private static Map<String, C3_ColaAtencion> colasPorDepartamento = new HashMap<>();

    public static void registrarDepartamento(C3_Departamento departamento) {
        String nombre = departamento.getNombre();
        if (!colasPorDepartamento.containsKey(nombre)) {
            C3_ColaAtencion cola = new C3_ColaAtencion(departamento);
            colasPorDepartamento.put(nombre, cola);
        }
    }

    public static C3_ColaAtencion obtenerCola(String nombreDepartamento) {
        return colasPorDepartamento.get(nombreDepartamento);
    }

    public static void agregarTicketACola(C7_Ticket ticket, C3_Departamento departamento) {
        String nombre = departamento.getNombre();
        C3_ColaAtencion cola = colasPorDepartamento.get(nombre);
        if (cola != null) {
            cola.agregarTicket(ticket);
        } else {
            System.out.println("Departamento no registrado: " + nombre);
        }
    }
}
