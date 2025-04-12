package sistematickets;

public class ParametrosDelSistema {

    private String nombreEmpresa;
    private String logo;
    private String idioma;
    private String zonaHoraria;
    private int tiempoVencimientoTicket;
    private String prioridades;

    public ParametrosDelSistema(String nombreEmpresa, String logo, String idioma, String zonaHoraria, int tiempoVencimientoTicket) {
        if (nombreEmpresa.length() >= 3 && nombreEmpresa.length() <= 100) {
            this.nombreEmpresa = nombreEmpresa;
        } else {
            System.out.println("El nombre no puede estar vacio Debe contener entre 3 y 100 caracteres");
        }
        this.logo = logo;
        this.idioma = idioma;
        this.zonaHoraria = zonaHoraria;
        this.tiempoVencimientoTicket = tiempoVencimientoTicket;
    }
//setters
    public void setNombreEmpresa(String nombreEmpresa) {
        if (nombreEmpresa.length() > 3 || nombreEmpresa.length() < 100) {
            this.nombreEmpresa = nombreEmpresa;
        } else {
            System.out.println("El nombre no puede estar vacio Debe contener entre 3 y 100 caracteres");
        }
    }

    public void setLogo(String logo) {
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
        if (tiempoVencimientoTicket <= 0 || tiempoVencimientoTicket < 365) {
            throw new Exception("El tiempo de vencimiento debe ser mayor que cero y menor a 365.");
        }
        this.tiempoVencimientoTicket = tiempoVencimientoTicket;
    }

    public void setPrioridades(String prioridades) {
        this.prioridades = prioridades;
    }

    public String getNombreEmpresa() {
        return this.nombreEmpresa;
    }
//getters
    public String getLogo() {
        return this.logo;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public String getZonaHoraria() {
        return this.zonaHoraria;
    }

    public int getTiempoVencimientoTicket() {
        return this.tiempoVencimientoTicket;
    }

    public String getPrioridades() {
        return this.prioridades;
    }

    //metodos
    public void mostrarEmpresa() {
        System.out.println(nombreEmpresa + " |" + logo + " |" + idioma + " |" + zonaHoraria + " |" + tiempoVencimientoTicket);
    }
}
