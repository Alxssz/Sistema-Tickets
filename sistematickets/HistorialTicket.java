package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class HistorialTicket extends HistorialBase {

    private List<String> historial;

    public HistorialTicket(List<String> historial, String usuario) {
        super(usuario);
        this.historial = new ArrayList<>();
    }

    public void agregarAccion(String accion) {
        historial.add(accion);
    }

    public void mostrarHistorial() {
        for (String accion : historial) {
            System.out.println(accion);
        }
    }
}
