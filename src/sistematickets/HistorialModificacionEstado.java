package sistematickets;

public class HistorialModificacionEstado extends HistorialBase {

    private String estadoModificado;
    private String accion; // "CREADO", "MODIFICADO", "ELIMINADO"

    public HistorialModificacionEstado(String usuario, String estadoModificado, String accion) {
        super(usuario);
        this.estadoModificado = estadoModificado;
        this.accion = accion;
    }

    public String getEstadoModificado() {
        return estadoModificado;
    }

    public String getAccion() {
        return accion;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Accion: " + accion + ", Estado: " + estadoModificado);
    }
}
