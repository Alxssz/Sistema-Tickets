package sistematickets;

public class UsuarioSistema extends Persona {

    private String nombre;
    private String correo;
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private Departamento departamento; // Solo para técnicos
    private boolean activo;

    public UsuarioSistema(String nombre, String correo, String nombreUsuario, String contrasena, String rol, Departamento departamento) {
        super(nombre, correo);
        this.nombre = nombre;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.departamento = departamento;
        this.rol = rol;
        this.activo = true; // Por defecto activo
    }

    // Validaciones básicas
    public boolean esValido() {
        return nombre.length() >= 3 && nombre.length() <= 100
                && correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                && nombreUsuario.length() >= 5 && nombreUsuario.length() <= 30
                && contrasena.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$");
    }

    public boolean estaActivo() {
        return activo;
    }

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    // Getters
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public String getCorreo() {
        return correo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getRol() {
        return rol;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean getActivo() {
        return activo;
    }
    

    // Para guardar en archivo
    public String toLinea() {
        return nombre + "," + correo + "," + nombreUsuario + "," + contrasena + "," + rol + "," + (departamento == null ? "" : departamento) + "," + activo;
    }

public static UsuarioSistema desdeLinea(String linea) {
    String[] partes = linea.split(",");
    String nombre = partes[0];
    String correo = partes[1];
    String usuario = partes[2];
    String contrasena = partes[3];
    String rol = partes[4];
    String departamentoNombre = partes[5].isEmpty() ? null : partes[5];  // El departamento es un String

    // Crear un objeto Departamento con nombre y una descripción por defecto
    Departamento departamento = null;
    if (departamentoNombre != null && !departamentoNombre.isEmpty()) {
        departamento = new Departamento(departamentoNombre, "Descripción no disponible");
    }

    boolean activo = Boolean.parseBoolean(partes[6]);

    // Crear el UsuarioSistema con el objeto Departamento (o null si no tiene departamento)
    UsuarioSistema u = new UsuarioSistema(nombre, correo, usuario, contrasena, rol, departamento);

    if (!activo) {
        u.desactivar();
    }

    return u;
}
}
