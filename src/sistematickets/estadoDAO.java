package sistematickets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class estadoDAO {

    // Crear un estado
    public void create(C5_EstadoTicket estado) {
        String sql = """
        INSERT INTO estado_ticket (
            nombre_estado,
            descripcion_estado,
            estado_final,
        ) VALUES (?, ?, ?)
    """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, estado.getNombre());
            ps.setString(2, estado.getDescripcion());
            ps.setBoolean(3, estado.esFinal());
            //ps.setString(4, estado.getEstadosSiguientes());

            ps.executeUpdate();
            System.out.println("Estado de ticket agregado correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(estadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Leer un estado por nombre_estado
    public C5_EstadoTicket read(String nombreEstado) {
        String sql = """
            SELECT 
                nombre_estado,
                descripcion_estado,
                estado_final,
                estados_siguientes
            FROM estado_ticket
            WHERE nombre_estado = ?
        """;

        try (Connection conn = conexionBD.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombreEstado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    C5_EstadoTicket estado = new C5_EstadoTicket();

                    estado.setNombre(rs.getString("nombre_estado"));
                    estado.setDescripcion(rs.getString("descripcion_estado"));
                    estado.setEsEstadoFinal(rs.getBoolean("estado_final"));
                    // estado.setEstadosSiguientes(rs.getString("estados_siguientes"));

                    return estado;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(estadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
