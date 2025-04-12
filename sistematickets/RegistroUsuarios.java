package sistematickets;

import java.util.*;
import java.io.*;

public class RegistroUsuarios {
    Scanner scanner = new Scanner(System.in);
    String rutaUsuarios = "usuarios.txt";
    String rutaHistorial = "historial.txt";

    // constructor: crea los archivos si no existen
    public RegistroUsuarios() {
        crearArchivoSiNoExiste(rutaUsuarios);
        crearArchivoSiNoExiste(rutaHistorial);
    }

    public void registrarNuevoUsuario(String usuarioActual) {
        UsuarioSistema admin = buscarUsuario(usuarioActual);
        System.out.println("REGISTRAR USUARIO ROL ADMINISTRADOR");
        if (admin == null || !admin.getRol().equalsIgnoreCase("Administrador")) {
            System.out.println("solo un administrador puede registrar usuarios.");
            return;
        }

        System.out.println("ingrese el nombre de usuario (o escriba CANCELAR): ");
        String nombre = scanner.nextLine();
        if (nombre.equalsIgnoreCase("CANCELAR")) {
            System.out.println("registro cancelado.");
            return;
        }

        System.out.println("ingrese el correo electronico: ");
        String correo = scanner.nextLine();

        if (existeUsuario(nombre, correo)) {
            System.out.println("error: ya existe un usuario con ese nombre o correo.");
            return;
        }

        System.out.println("ingrese la contrasena: ");
        String contrasena = scanner.nextLine();

        System.out.println("ingrese el rol (Administrador/Tecnico/Usuario): ");
        String rol = scanner.nextLine();

        Departamento departamento = null;
        if (rol.equalsIgnoreCase("Administrador") || rol.equalsIgnoreCase("Tecnico") || rol.equalsIgnoreCase("Usuario")) {
            departamento = null;
        }

        System.out.println("ingrese el nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();

        UsuarioSistema nuevo = new UsuarioSistema(nombre, correo, nombreUsuario, contrasena, rol, departamento);

        guardarUsuario(nuevo);
        guardarHistorial(new HistorialModificaciones(usuarioActual, "registro de nuevo usuario: " + nombre));

        System.out.println("usuario registrado exitosamente.");
    }

    private boolean existeUsuario(String nombre, String correo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                UsuarioSistema u = UsuarioSistema.desdeLinea(linea);
                if (u.getNombreUsuario().equalsIgnoreCase(nombre) || u.getCorreo().equalsIgnoreCase(correo)) {
                    return true;
                }
            }
        } catch (IOException e) {}
        return false;
    }

    private UsuarioSistema buscarUsuario(String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaUsuarios))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                UsuarioSistema u = UsuarioSistema.desdeLinea(linea);
                if (u.getNombreUsuario().equalsIgnoreCase(nombre)) return u;
            }
        } catch (IOException e) {}
        return null;
    }

    public void guardarUsuario(UsuarioSistema usuario) {
        try (FileWriter fw = new FileWriter(rutaUsuarios, true)) {
            fw.write(usuario.toLinea() + "\n");
        } catch (IOException e) {}
    }

    private void guardarHistorial(HistorialModificaciones h) {
        try (FileWriter fw = new FileWriter(rutaHistorial, true)) {
            fw.write(h.toLinea() + "\n");
        } catch (IOException e) {}
    }

    // metodo para crear un archivo si no existe
    private void crearArchivoSiNoExiste(String ruta) {
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) {
                archivo.createNewFile(); // crea el archivo vacío
            }
        } catch (IOException e) {}
    }
}
