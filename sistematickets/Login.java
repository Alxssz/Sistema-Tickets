package sistematickets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    public UsuarioSistema iniciarSesion(String nombreUsuario, String contrasena) {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                UsuarioSistema u = UsuarioSistema.desdeLinea(linea);
                if (u.getNombreUsuario().equals(nombreUsuario) && u.getContrasena().equals(contrasena)) {
                    if (!u.getActivo()) {
                        System.out.println("Usuario desactivado. No puede iniciar sesión.");
                        return null;
                    }
                    return u;
                }
            }
        } catch (IOException e) {}
        return null;
    }
}
