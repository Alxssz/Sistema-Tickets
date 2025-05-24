package sistematickets;

import java.time.LocalDateTime;

public class D10_Nota {
    private String contenido;
    private String archivoAdjunto; 
    private String autor;
    private LocalDateTime fecha;

    public D10_Nota(String contenido, String archivoAdjunto, String autor) {
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