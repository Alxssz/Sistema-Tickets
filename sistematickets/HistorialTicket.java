package sistematickets;

import java.util.ArrayList;
import java.util.List;

public class HistorialTicket {
    private List<String> historial;

    public HistorialTicket() {
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
