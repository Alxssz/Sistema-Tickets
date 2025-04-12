package sistematickets;

import java.time.ZoneId;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HistorialCambios extends HistorialBase {

    private String descripcion;

    public HistorialCambios(String usuario, Date fecha, String descripcion) {
        super(usuario);
        this.fechaHora = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.descripcion = descripcion;
    }

    public void mostrarHistorial(){
        System.out.println("Historial[" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(
            java.sql.Timestamp.valueOf(fechaHora)) + "] " + usuario + ": " + descripcion);
    }

    @Override
    public String toString() {
        return "HistorialCambios{" + "usuario=" + usuario + ", fecha=" + fechaHora + ", descripcion=" + descripcion + '}';
    }
}
