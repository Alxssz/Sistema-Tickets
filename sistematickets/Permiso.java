package sistematickets;

public class Permiso {

    private String nombrePermiso;
    private String descripcion;

    public Permiso(String nombrePermiso, String descripcion) {
        if (nombrePermiso.length() > 3 && nombrePermiso.length() < 50) {
            this.nombrePermiso = nombrePermiso;
        } else {
            System.out.println("Debe contener entre 3 y 50 caracteres. No puede estar vacío.");
        }

        this.descripcion = descripcion;

    }

    // Métodos getter y setter
    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        if (nombrePermiso.length() > 3 && nombrePermiso.length() < 50) {
            this.nombrePermiso = nombrePermiso;
        } else {
            System.out.println("Debe contener entre 3 y 50 caracteres. No puede estar vacío.");
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return nombrePermiso;  // O cualquier otra propiedad que quieras mostrar
    }
}
