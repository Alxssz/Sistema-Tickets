package sistematickets;

import java.time.LocalDateTime;

/**
 *
 * @author Familia Boror Ruiz
 */
public class Nota {
    private String contenido;
    private String archivoAdjunto; // Puede ser la ruta o nombre del archivo
    private String autor;
    private LocalDateTime fecha;

    public Nota(String contenido, String archivoAdjunto, String autor) {
        this.contenido = contenido;
        this.archivoAdjunto = archivoAdjunto;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
    }

    public String getContenido() {
        return contenido;
    }

    public String getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
}