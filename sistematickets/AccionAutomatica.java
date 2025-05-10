package sistematickets;

public class AccionAutomatica {

    private EstadoTicket estado;
    private String descripcion;

    public AccionAutomatica(EstadoTicket estado, String descripcion) {
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
