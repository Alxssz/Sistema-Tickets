package sistematickets;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class C1_ParametrosDelSistema implements Serializable {

    // Variables
    private String nombreEmpresa;
    private String logo;
    private String idioma;
    private String zonaHoraria;
    private int tiempoVencimiento;
    private List<String> prioridades = new ArrayList<>();
    private List<String> historialDeCambios;

    // Constructores
    public C1_ParametrosDelSistema() {
        prioridades = new ArrayList<>();
        try {
            setNombreEmpresa("Mi Empresa");
            setLogo("");
            setIdioma("Español");
            setZonaHoraria("UTC");
            setTiempoVencimiento(30);

            // Prioridades por defecto
            prioridades.add("Alta");
            prioridades.add("Media");
            prioridades.add("Baja");

            historialDeCambios = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al cargar parametros");
        }
    }

    public C1_ParametrosDelSistema(String nombreEmpresa, String logo, String idioma, String zonaHoraria, int tiempoVencimiento) {
        this.nombreEmpresa = nombreEmpresa;
        this.logo = logo;
        this.idioma = idioma;
        this.zonaHoraria = zonaHoraria;
        this.tiempoVencimiento = tiempoVencimiento;
        this.historialDeCambios = historialDeCambios;
    }

    // Setters
    public void setNombreEmpresa(String nombreEmpresa) {
        if (nombreEmpresa != null && nombreEmpresa.length() >= 3 && nombreEmpresa.length() <= 100) {
            this.nombreEmpresa = nombreEmpresa;
        } else {
            System.out.println("El nombre no puede estar vacio. Debe contener entre 3 y 100 caracteres.");
        }
    }

    public void setLogo(String rutaLogo) throws Exception {
        if (rutaLogo != null && !rutaLogo.isEmpty()) {
            if (!(rutaLogo.toLowerCase().endsWith(".jpg") || rutaLogo.toLowerCase().endsWith(".png"))) {
                throw new Exception("El logo debe estar en formato JPG o PNG.");
            }
        }
        this.logo = rutaLogo;
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

    public void setTiempoVencimiento(int tiempoVencimiento) throws Exception {
        if (tiempoVencimiento >= 1 && tiempoVencimiento <= 365) {
            this.tiempoVencimiento = tiempoVencimiento;
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

    public void setHistorialDeCambios(List<String> historialDeCambios) {
        this.historialDeCambios = historialDeCambios;
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

    public int getTiempoVencimiento() {
        return tiempoVencimiento;
    }

    public List<String> getPrioridades() {
        return prioridades;
    }

    public List<String> getHistorialDeCambios() {
        return historialDeCambios;
    }

    //  prioridades 
    public void AgregarPrioridad(String prioridad) {
        prioridades.add(prioridad);
    }

    public void EliminarPrioridad(String prioridad) {
        prioridades.remove(prioridad);
    }

    // Métodos 
    public void mostrarInformacion() {
        System.out.println("DATOS DE LA EMPRESA");
        System.out.println("Nombre Empresa: " + nombreEmpresa);
        System.out.println("Logo: " + logo);
        System.out.println("Idioma: " + idioma);
        System.out.println("Zona Horaria: " + zonaHoraria);
        System.out.println("Tiempo vencimiento del los ticket:" + tiempoVencimiento);
        System.out.println("Prioridades");
        if (prioridades.isEmpty()) {
            System.out.println("No hay prioridades definidas.");
        } else {
            for (int i = 0; i < prioridades.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + prioridades.get(i));
            }
        }
        System.out.println();
    }

    // guardar y cargar parametros
    public void guardarParametrosSistema() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Archivos/parametros_sistema.bin"))) {
            oos.writeObject(this);
            System.out.println("Parametros del sistema guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar los parametros del sistema.");
        }
    }

    public static C1_ParametrosDelSistema cargarParametrosSistema() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Archivos/parametros_sistema.bin"))) {
            return (C1_ParametrosDelSistema) ois.readObject();
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo de parametros, Se usara configuracion por defecto.");
            return null;
        }
    }

    //  guardar y cargar prioridades 
    public void guardarPrioridades() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/Archivos/prioridades.bin"))) {
            oos.writeObject(prioridades);
            System.out.println("Prioridades guardadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar las prioridades: " + e.getMessage());
        }
    }

    public void cargarPrioridades() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/Archivos/prioridades.bin"))) {
            prioridades = (List<String>) ois.readObject();
            System.out.println("Prioridades cargadas correctamente.");
        } catch (Exception e) {
            System.out.println("No se pudo cargar el archivo de prioridades. Se usara lista defecto ");
            prioridades = new ArrayList<>();
        }
    }
}
