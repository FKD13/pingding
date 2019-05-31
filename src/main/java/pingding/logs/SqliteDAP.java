package pingding.logs;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnection;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteDAP {

    private String JDBC_URL = "jdbc:sqlite:" + getClass().getResource("/log.db").getPath();

    public SqliteDAC getSqliteDAC() throws SQLException{
        return new SqliteDAC(DriverManager.getConnection(JDBC_URL));
    }
}
