package sistematickets;

public abstract class C2_Persona {
    private String nombre;
    private String correo;

    // Constructor
    public C2_Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    // Métodos getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método común a todas las personas
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);

    }
}
