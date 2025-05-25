package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class permisoDAO {

    public void create(C2_Permiso permiso) {
        String sql = """
            INSERT INTO permisos (
               nombre_permiso,
               descripcion_permiso
            ) VALUES (?,?)
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, permiso.getNombrePermiso());
            ps.setString(2, permiso.getDescripcion());

            ps.executeUpdate();
            System.out.println("Permiso agregado correctamente a base de datos.");

        } catch (SQLException ex) {
            Logger.getLogger(permisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public C2_Permiso read(int id) throws Exception {
        String sql = """
            SELECT 
                id_permiso,
                nombre_permiso,
                descripcion_permiso
            FROM permisos
            WHERE id_permiso = ?
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C2_Permiso permiso = new C2_Permiso();

                    permiso.setNombrePermiso(rs.getString("nombre_permiso"));
                    permiso.setDescripcion(rs.getString("descripcion_permiso"));

                    return permiso;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(permisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
