package pingding.logs;

import java.sql.Connection;

public class SqliteDAC{

    private Connection connection;

    public SqliteDAC(Connection connection) {
        this.connection = connection;
    }

    public LogDAO getLogDAO() {
        return new LogDAO(connection);
    }
}
