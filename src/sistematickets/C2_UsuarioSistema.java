package sistematickets;

import java.io.Serializable;

public class C2_UsuarioSistema implements Serializable {

    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String contrasena;
    C2_Rol rol;
    private C3_Departamento departamento;
    private boolean activo;

    public C2_UsuarioSistema(String nombre, String correo, String nombreUsuario, String contrasena, C2_Rol rol, C3_Departamento departamento) {
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.departamento = departamento;
        this.activo = true; // Por defecto activo
    }

    public C2_UsuarioSistema() {

    }

    // Validaciones básicas
    public boolean esValido() {
        return nombre != null && nombre.length() >= 3
                && correo != null && correo.contains("@")
                && nombreUsuario != null && nombreUsuario.length() >= 5
                && contrasena != null && contrasena.length() >= 8;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public C2_Rol getRol() {
        return rol;
    }

    public C3_Departamento getDepartamento() {
        return departamento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRolUsuario(C2_Rol rol) {
        this.rol = rol;
    }

    public void setDepartamento(C3_Departamento departamento) {
        this.departamento = departamento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setRol(C2_Rol rol) {
        this.rol = rol;
    }


    public static C2_UsuarioSistema desdeLinea(String linea) {
        String[] partes = linea.split(",", -1); // El -1 permite incluir campos vacíos

        if (partes.length < 6) {
            throw new IllegalArgumentException("La línea no tiene todos los campos requeridos: " + linea);
        }

        String nombre = partes[0].trim();
        String correo = partes[1].trim();
        String nombreUsuario = partes[2].trim();
        String contrasena = partes[3].trim();
        String rolTexto = partes[4].trim();
        String nombreDepartamento = partes[5].trim();
        String descripcionDepartamento = partes[6].trim();

        C2_Rol rol = C2_Rol.valueOf(rolTexto.toUpperCase());

        C3_Departamento departamento = null;
        if (!nombreDepartamento.isEmpty()) {
            departamento = new C3_Departamento(nombreDepartamento, descripcionDepartamento);
        }
        return new C2_UsuarioSistema(nombre, correo, nombreUsuario, contrasena, rol, departamento);
    }

    public String toLinea() {
        return nombre + "," + correo + "," + nombreUsuario + "," + contrasena + "," + rol + "," + (departamento == null ? "" : departamento) + "," + activo;
    }

    @Override
    public String toString() {
        return nombre;
    }

    void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);

    }
}
