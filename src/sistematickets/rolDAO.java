package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class rolDAO {

    public void create(C2_Rol rol) {
        String sql = """
            INSERT INTO roles (
               nombre_rol,
               descripcion_rol
            ) VALUES (?,?)
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());

            ps.executeUpdate();
            System.out.println("Rol agregados correctamente a base de datos.");

        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public C2_Rol read(int id) throws Exception {
        String sql = """
            SELECT 
                id_rol,
                nombre_rol,
                descripcion_rol
            FROM roles
            WHERE id_rol = ?
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C2_Rol rol = new C2_Rol();

                    rol.setNombreRol(rs.getString("nombre_rol"));
                    rol.setDescripcion(rs.getString("descripcion_rol"));

                    return rol;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
