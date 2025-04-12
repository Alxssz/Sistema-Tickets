package sistematickets;

public class TransicionEstado {
    private EstadoTicket origen;
    private EstadoTicket destino;
    private String regla;

    public TransicionEstado(EstadoTicket origen, EstadoTicket destino, String regla) {
        this.origen = origen;
        this.destino = destino;
        this.regla = regla;
    }

    public EstadoTicket getOrigen() { return origen; }
    public EstadoTicket getDestino() { return destino; }
    public String getRegla() { return regla; }
}

