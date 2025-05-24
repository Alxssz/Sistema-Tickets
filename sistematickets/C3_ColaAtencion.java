package sistematickets;

import java.util.LinkedList;
import java.util.Queue;

public class C3_ColaAtencion {

    private C3_Departamento departamento;
    private Queue<C7_Ticket> ticketsEnCola;

    public C3_ColaAtencion(C3_Departamento departamento) {
        this.departamento = departamento;
        this.ticketsEnCola = new LinkedList<>();
    }

    // Agregar un ticket a la cola
    public void agregarTicket(C7_Ticket ticket) {
        ticketsEnCola.add(ticket);
        System.out.println("Ticket agregado a la cola del departamento " + departamento.getNombre());
    }

    // Tomar (y remover) el primer ticket de la cola
    public C7_Ticket tomarTicket() {
        if (ticketsEnCola.isEmpty()) {
            System.out.println("No hay tickets en la cola para el departamento " + departamento.getNombre());
            return null;
        }
        return ticketsEnCola.poll();
    }

    // Mostrar tickets en la cola sin sacarlos
    public void mostrarCola() {
        if (ticketsEnCola.isEmpty()) {
            System.out.println("La cola de atencion del departamento " + departamento.getNombre() + " esta vacia.");
        } else {
            System.out.println("Tickets en la cola del departamento " + departamento.getNombre() + ":");
            for (C7_Ticket ticket : ticketsEnCola) {
                System.out.println("- Ticket ID: " + ticket.getId() + ", Titulo: " + ticket.getTitulo());
            }
        }
    }

    // Obtener el departamento asociado
    public C3_Departamento getDepartamento() {
        return departamento;
    }

    // Obtener la cola como lista (opcional)
    public Queue<C7_Ticket> getTickets() {
        return ticketsEnCola;
    }
}
