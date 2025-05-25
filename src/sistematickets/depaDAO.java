package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class depaDAO {

    // Crear un departamento
    public void create(C3_Departamento departamento) {
        String sql = """
            INSERT INTO departamentos (
                nombre_departamento,
                descripcion_depa
            ) VALUES (?, ?)
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getDescripcion());

            ps.executeUpdate();
            System.out.println("Departamento agregado correctamente a la base de datos.");

        } catch (SQLException ex) {
            Logger.getLogger(depaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Leer un departamento por nombre (o podrías usar otro campo)
    public C3_Departamento read(int id) {
        String sql = """
        SELECT 
            nombre_departamento,
            descripcion_depa
        FROM departamentos
        WHERE id_departamento = ?
    """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C3_Departamento departamento = new C3_Departamento();

                    departamento.setNombre(rs.getString("nombre_departamento"));
                    departamento.setDescripcion(rs.getString("descripcion_depa"));

                    return departamento;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(depaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
