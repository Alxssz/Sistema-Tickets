package sistematickets;

import java.util.*;
import java.io.*;

public class C4_RegistroUsuarios {

    private List<C2_UsuarioSistema> listaUsuarios = new ArrayList<>();
    private final String archivoUsuarios = "usuarios.txt";

    public C4_RegistroUsuarios() {
        cargarUsuariosDesdeArchivo();
    }

    // Agregar usuario
    public boolean agregarUsuario(C2_UsuarioSistema nuevo) {
        if (existeUsuario(nuevo.getNombreUsuario(), nuevo.getCorreo())) {
            System.out.println("Error: Ya existe un usuario con ese nombre de usuario o correo.");
            return false;
        }
        listaUsuarios.add(nuevo);
        guardarUsuariosEnArchivo();
        return true;
    }

    // Editar usuario por nombreUsuario
    public boolean editarUsuario(String nombreUsuario, C2_UsuarioSistema usuarioModificado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            C2_UsuarioSistema u = listaUsuarios.get(i);
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                if (!nombreUsuario.equalsIgnoreCase(usuarioModificado.getNombreUsuario())
                        && existeUsuario(usuarioModificado.getNombreUsuario(), usuarioModificado.getCorreo())) {
                    System.out.println("Error: El nuevo nombre de usuario o correo ya existe.");
                    return false;
                }
                listaUsuarios.set(i, usuarioModificado);
                guardarUsuariosEnArchivo();
                return true;
            }
        }
        System.out.println("Usuario a editar no encontrado.");
        return false;
    }

    // Eliminar usuario por nombreUsuario
    public boolean eliminarUsuario(String nombreUsuario) {
        Iterator<C2_UsuarioSistema> iter = listaUsuarios.iterator();
        while (iter.hasNext()) {
            C2_UsuarioSistema u = iter.next();
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                iter.remove();
                guardarUsuariosEnArchivo();
                return true;
            }
        }
        System.out.println("Usuario a eliminar no encontrado.");
        return false;
    }

    public void verUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        System.out.println("Lista de usuarios registrados:");
        System.out.println("------------------------------------------------------------");
        for (C2_UsuarioSistema user : listaUsuarios) {
            System.out.println("Nombre completo   : " + user.getNombre());
            System.out.println("Nombre de usuario : " + user.getNombreUsuario());
            System.out.println("Correo            : " + user.getCorreo());
            System.out.println("Contrasena        : " + user.getContrasena());
            System.out.println("Rol               : " + user.getRol());
            if (user.getDepartamento() != null) {
                System.out.println("Departamento      : " + user.getDepartamento().getNombre());
                System.out.println("Descripción dep   : " + user.getDepartamento().getDescripcion());
            } else {
                System.out.println("Departamento      : No asignado");
            }
            System.out.println("------------------------------------------------------------");
        }
    }

    // Verifica si existe usuario con ese nombreUsuario o correo
    private boolean existeUsuario(String nombreUsuario, String correo) {
        for (C2_UsuarioSistema u : listaUsuarios) {
            if (u.getNombreUsuario().equalsIgnoreCase(nombreUsuario) || u.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    // Carga usuarios desde archivo
    private void cargarUsuariosDesdeArchivo() {
        listaUsuarios.clear();
        File archivo = new File(archivoUsuarios);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // nombre,correo,nombreUsuario,contrasena,rol,nombreDepartamento,descripcionDepartamento
                String[] partes = linea.split(",");
                if (partes.length >= 7) {
                    String nombre = partes[0];
                    String correo = partes[1];
                    String nombreUsuario = partes[2];
                    String contrasena = partes[3];
                    C2_Rol rol = C2_Rol.valueOf(partes[4].toUpperCase());

                    C3_Departamento departamento = null;
                    if (!partes[5].equalsIgnoreCase("null") && !partes[5].isEmpty()) {
                        String nombreDepa = partes[5];
                        String descripcionDepa = partes[6];
                        departamento = new C3_Departamento(nombreDepa, descripcionDepa);
                    }

                    C2_UsuarioSistema usuario = new C2_UsuarioSistema(nombre, correo, nombreUsuario, contrasena, rol, departamento);
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    // Guarda usuarios en archivo
    private void guardarUsuariosEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoUsuarios))) {
            for (C2_UsuarioSistema u : listaUsuarios) {
                String departamento = (u.getDepartamento() != null) ? u.getDepartamento().getNombre() : "null";
                pw.println(u.getNombre() + "," + u.getCorreo() + "," + u.getNombreUsuario() + "," + u.getContrasena() + "," + u.getRol() + "," + departamento);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    // Lista usuarios
    public void listarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (C2_UsuarioSistema u : listaUsuarios) {
            System.out.println("Usuario: " + u.getNombreUsuario() + " - Nombre completo: " + u.getNombre() + " - Rol: " + u.getRol());
        }
    }
}
