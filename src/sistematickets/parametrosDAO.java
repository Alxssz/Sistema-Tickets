package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class parametrosDAO {

    public void create(C1_ParametrosDelSistema parametros) {
        String sql = """
            INSERT INTO parametros_sistema (
                id,
                nombre_empresa,
                logo,
                idioma,
                zona_horaria,
                tiempo_vencimiento_ticket,
                nivel_prioridad_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, parametros.getId());
            ps.setString(2, parametros.getNombreEmpresa());
            ps.setString(3, parametros.getLogo());
            ps.setString(4, parametros.getIdioma());
            ps.setString(5, parametros.getZonaHoraria());
            ps.setInt(6, parametros.getTiempoVencimiento());
            ps.setNull(7, java.sql.Types.INTEGER);

            ps.executeUpdate();
            System.out.println("Parametros del sistema agregados correctamente.");

        } catch (SQLException ex) {
            Logger.getLogger(parametrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public C1_ParametrosDelSistema read(int id) throws Exception {
        String sql = """
            SELECT 
                id,
                nombre_empresa,
                logo,
                idioma,
                zona_horaria,
                tiempo_vencimiento_ticket
            FROM parametros_sistema
            WHERE id = ?
        """;

        //nivel_prioridad_id
        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C1_ParametrosDelSistema parametros = new C1_ParametrosDelSistema();

                    parametros.setId(rs.getInt("id"));
                    parametros.setNombreEmpresa(rs.getString("nombre_empresa"));
                    parametros.setLogo(rs.getString("logo"));
                    parametros.setIdioma(rs.getString("idioma"));
                    parametros.setZonaHoraria(rs.getString("zona_horaria"));
                    parametros.setTiempoVencimiento(rs.getInt("tiempo_vencimiento_ticket"));

                    return parametros;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(parametrosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
