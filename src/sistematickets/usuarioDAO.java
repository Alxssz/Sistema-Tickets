package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDAO {

    // Crear un usuario
    public void create(C2_UsuarioSistema usuario) {
        String sql = """
            INSERT INTO usuarios (
                nombre_completo,
                correo,
                nombre_usuario,
                contraseña,
                rol_asignado,
                departamento_id,
                activo_inactivo
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getNombreUsuario());
            ps.setString(4, usuario.getContrasena());
            ps.setInt(5, usuario.getRol().getId());
            if (usuario.getDepartamento() == null) {
                System.out.println("El usuario no tiene un departamento asignado.");
            }
            ps.setInt(6, usuario.getDepartamento().getId());
            ps.setBoolean(7, usuario.getActivo());
            ps.executeUpdate();
            System.out.println("Usuario agregado correctamente a la base de datos.");

        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public C2_UsuarioSistema read(String nombreUsuario) {
        String sql = """
        SELECT 
            nombre_completo,
            correo,
            nombre_usuario,
            contraseña,
            rol_asignado,
            departamento_id,
            activo_inactivo
        FROM usuarios
        WHERE nombre_usuario = ?
    """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C2_UsuarioSistema usuario = new C2_UsuarioSistema();

                    usuario.setNombre(rs.getString("nombre_completo"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setContrasena(rs.getString("contraseña"));
                    C2_Rol rol = new C2_Rol(rs.getString("rol_asignado"));
                    usuario.setRol(rol);

                    C3_Departamento departamento = new C3_Departamento(rs.getInt("departamento_id"));
                    usuario.setDepartamento(departamento);
                    usuario.setActivo(rs.getBoolean("activo_inactivo"));

                    return usuario;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
