package sistematickets;

import java.time.LocalDateTime;

public abstract class HistorialBase {

    protected String usuario;
    protected LocalDateTime fechaHora;

    public HistorialBase(String usuario) {
        this.usuario = usuario;
        this.fechaHora = LocalDateTime.now();
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void mostrarInformacion() {
        System.out.println("Usuario: " + usuario + ", Fecha: " + fechaHora);
    }
}
