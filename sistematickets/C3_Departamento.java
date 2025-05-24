package sistematickets;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class C3_Departamento implements Serializable {

    private transient StringProperty nombre;        
    private transient StringProperty descripcion;    
    private String nombreString;                      
    private String descripcionString;                 

    private ArrayList<C2_Tecnico> tecnicos;
    private ArrayList<C2_Tecnico> tecnicosAsignados;

    public C3_Departamento(String nombre, String descripcion) {
        this.nombreString = nombre;
        this.descripcionString = descripcion;
        this.tecnicos = new ArrayList<>();
        this.tecnicosAsignados = new ArrayList<>();

        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        
        // Registrar automáticamente el departamento en el gestor de colas
        C3_GestorColas.registrarDepartamento(this);
    }

    // Obtener nombre en String (método principal)
    public String getNombre() {
        return (nombre != null) ? nombre.get() : nombreString;
    }

    // Setter para nombre
    public void setNombre(String nombre) {
        if (this.nombre != null) {
            this.nombre.set(nombre);
        }
        this.nombreString = nombre;
    }

    // Propiedad nombre para JavaFX
    public StringProperty nombreProperty() {
        if (nombre == null) {
            nombre = new SimpleStringProperty(nombreString);
        }
        return nombre;
    }

    // Obtener descripción en String
    public String getDescripcion() {
        return (descripcion != null) ? descripcion.get() : descripcionString;
    }

    // Setter para descripción
    public void setDescripcion(String descripcion) {
        if (this.descripcion != null) {
            this.descripcion.set(descripcion);
        }
        this.descripcionString = descripcion;
    }

    // Propiedad descripción para JavaFX
    public StringProperty descripcionProperty() {
        if (descripcion == null) {
            descripcion = new SimpleStringProperty(descripcionString);
        }
        return descripcion;
    }

    // Métodos para técnicos
    public ArrayList<C2_Tecnico> getTecnicos() {
        return tecnicos;
    }

    public void setTecnicos(ArrayList<C2_Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public ArrayList<C2_Tecnico> getTecnicosAsignados() {
        return tecnicosAsignados;
    }

    public void setTecnicosAsignados(ArrayList<C2_Tecnico> tecnicosAsignados) {
        this.tecnicosAsignados = tecnicosAsignados;
    }

    public void asignarTecnico(C2_Tecnico tecnico) {
        this.tecnicos.add(tecnico);
    }

    public void quitarTecnico(C2_Tecnico tecnico) {
        this.tecnicos.remove(tecnico);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
