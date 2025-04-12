package sistematickets;

import java.util.ArrayList;

public class Departamento {
    private String nombre;
    private String descripcion;
    private ArrayList<UsuarioSistema> tecnicos;

    public Departamento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tecnicos = new ArrayList<>();
    }

    public void agregarTecnico(UsuarioSistema tecnico) {
        this.tecnicos.add(tecnico);
    }

    public ArrayList<UsuarioSistema> getTecnicos() {
        return tecnicos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
