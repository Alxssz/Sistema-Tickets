package sistematickets;

import java.util.*;

public class GestorFlujosTrabajo {
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.print("Ingrese el nombre del flujo de trabajo: ");
        String nombreFlujo = scanner.nextLine();

        FlujoTrabajoTicket flujo = new FlujoTrabajoTicket(nombreFlujo);

        agregarEstados(flujo);
        agregarTransiciones(flujo);
        agregarAcciones(flujo);

        if (flujo.esValido()) {
            System.out.println("Flujo configurado correctamente.");
        } else {
            System.out.println("Error: hay estados sin transiciones de salida.");
        }
    }

private void agregarEstados(FlujoTrabajoTicket flujo) {
    System.out.println("Ingrese los estados (escriba 'fin' para terminar):");
    while (true) {
        System.out.print("Nombre del estado: ");
        String nombre = scanner.nextLine();
        if (nombre.equalsIgnoreCase("fin")) break;

        System.out.print("Descripcion del estado: ");
        String descripcion = scanner.nextLine();

        System.out.print("¿Es estado final? (si/no): ");
        String esFinalStr = scanner.nextLine();
        boolean esFinal = esFinalStr.equalsIgnoreCase("si");

        EstadoTicket estado = new EstadoTicket(nombre, descripcion, esFinal);
        flujo.agregarEstado(estado);
    }
}


    private void agregarTransiciones(FlujoTrabajoTicket flujo) {
        System.out.println("Defina las transiciones entre estados:");
        while (true) {
            System.out.print("Estado origen (o 'fin'): ");
            String origen = scanner.nextLine();
            if (origen.equalsIgnoreCase("fin")) break;

            System.out.print("Estado destino: ");
            String destino = scanner.nextLine();

            System.out.print("Regla (opcional): ");
            String regla = scanner.nextLine();
            if (regla.isEmpty()) regla = null;

            EstadoTicket e1 = buscarEstado(flujo, origen);
            EstadoTicket e2 = buscarEstado(flujo, destino);

            if (e1 != null && e2 != null) {
                flujo.agregarTransicion(new TransicionEstado(e1, e2, regla));
            } else {
                System.out.println("Uno de los estados no existe.");
            }
        }
    }

    private void agregarAcciones(FlujoTrabajoTicket flujo) {
        System.out.println("¿Desea agregar acciones automáticas? (s/n)");
        String opcion = scanner.nextLine();
        if (opcion.equalsIgnoreCase("s")) {
            while (true) {
                System.out.print("Estado destino para acción (o 'fin'): ");
                String estado = scanner.nextLine();
                if (estado.equalsIgnoreCase("fin")) break;

                EstadoTicket e = buscarEstado(flujo, estado);
                if (e != null) {
                    System.out.print("Descripción de la acción: ");
                    String descripcion = scanner.nextLine();
                    flujo.agregarAccionAutomatica(new AccionAutomatica(e, descripcion));
                } else {
                    System.out.println("Estado no encontrado.");
                }
            }
        }
    }

    private EstadoTicket buscarEstado(FlujoTrabajoTicket flujo, String nombre) {
        for (EstadoTicket e : flujo.getEstados()) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }
}

