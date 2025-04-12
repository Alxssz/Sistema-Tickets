package sistematickets;

public class HistorialModificaciones extends HistorialBase {

    private String accion;

    public HistorialModificaciones(String usuario, String accion) {
        super(usuario);
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public String toLinea() {
        return usuario + "," + accion + "," + fechaHora.toString();
    }
}
