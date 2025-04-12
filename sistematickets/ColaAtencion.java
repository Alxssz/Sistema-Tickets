package sistematickets;

import java.util.LinkedList;
import java.util.Queue;


public class ColaAtencion {
    private Departamento departamento;
    private Queue<String> tickets; // Aquí se almacenarán los tickets

    public ColaAtencion(Departamento departamento) {
        this.departamento = departamento;
        this.tickets = new LinkedList<>();
    }


    public void agregarTicket(String ticketId) {
        tickets.add(ticketId);
    }

    public String tomarTicket() {
        return tickets.poll();
    }

    public Queue<String> getTickets() {
        return tickets;
    }
}
