package sistematickets;

import java.sql.Connection;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;


public class conexionBD {

    private static PGSimpleDataSource dataSource;
    private static Connection conn = null;

    static {
        dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"ep-curly-breeze-a5jfx8w9-pooler.us-east-2.aws.neon.tech"});
        dataSource.setDatabaseName("Sistema_Gestion_Tickets");
        dataSource.setUser("Sistema_Gestion_Tickets_owner");
        dataSource.setPassword("npg_yk0xLRW6jZKU");
    }

    public static Connection getConexion() throws SQLException {
        return dataSource.getConnection();
    }
}
