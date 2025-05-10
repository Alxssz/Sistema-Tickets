package sistematickets;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ParametrosDelSistema implements Serializable {

    private String nombreEmpresa;
    private String logo;
    private String idioma;
    private String zonaHoraria;
    private int tiempoVencimientoTicket;
    private List<String> prioridades = new ArrayList<>();

    public ParametrosDelSistema(String nombreEmpresa, String logo, String idioma, String zonaHoraria, int tiempoVencimientoTicket) throws Exception {
        setNombreEmpresa(nombreEmpresa);
        setLogo(logo);
        setIdioma(idioma);
        setZonaHoraria(zonaHoraria);
        setTiempoVencimientoTicket(tiempoVencimientoTicket);
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        if (nombreEmpresa != null && nombreEmpresa.length() >= 3 && nombreEmpresa.length() <= 100) {
            this.nombreEmpresa = nombreEmpresa;
        } else {
            System.out.println("El nombre no puede estar vacio. Debe contener entre 3 y 100 caracteres.");
        }
    }

    public void AgregarPrioridad(String Prioridad) {
        prioridades.add(Prioridad);
    }

    public void EliminarPrioridad(String Prioridad) {
        prioridades.remove(Prioridad);
    }

    public void setLogo(String logo) throws Exception {
        if (logo == null || (!logo.endsWith(".jpg") && !logo.endsWith(".png"))) {
            throw new Exception("El logo debe estar en formato JPG o PNG.");
        }
        // Verificar el tamaño del archivo (máximo 2MB)
        File archivo = new File(logo);
        if (archivo.length() > 2 * 1024 * 1024) {  // 2MB en bytes
            throw new Exception("El logo debe tener un tamaño máximo de 2MB.");
        }

        this.logo = logo;
    }

    public void setIdioma(String idioma) throws Exception {
        if (idioma == null || idioma.trim().isEmpty()) {
            throw new Exception("Debe seleccionar un idioma.");
        }
        this.idioma = idioma;
    }

    public void setZonaHoraria(String zonaHoraria) throws Exception {
        if (zonaHoraria == null || zonaHoraria.trim().isEmpty()) {
            throw new Exception("Debe seleccionar una zona horaria.");
        }
        this.zonaHoraria = zonaHoraria;
    }

    public void setTiempoVencimientoTicket(int tiempoVencimientoTicket) throws Exception {
        if (tiempoVencimientoTicket >= 1 && tiempoVencimientoTicket <= 365) {
            this.tiempoVencimientoTicket = tiempoVencimientoTicket;
        } else {
            throw new Exception("El tiempo de vencimiento debe estar entre 1 y 365 días.");
        }
    }

    public void setPrioridades(List<String> prioridades) throws Exception {
        if (prioridades == null || prioridades.size() < 3) {
            throw new Exception("Debe definir al menos tres niveles de prioridad.");
        }
        this.prioridades = prioridades;
    }

    // Getters
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getLogo() {
        return logo;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getZonaHoraria() {
        return zonaHoraria;
    }

    public int getTiempoVencimientoTicket() {
        return tiempoVencimientoTicket;
    }

    public List<String> getPrioridades() {
        return prioridades;
    }

    //metodos
    public void mostrarEmpresa() {
        System.out.println(nombreEmpresa + " |" + logo + " |" + idioma + " |" + zonaHoraria + " |" + tiempoVencimientoTicket);
    }

    public void setDiasVencimientoTicket(int diasVencimiento) {
       
    }

    public Integer getDiasVencimientoTicket() {
        return tiempoVencimientoTicket;
    }
}
