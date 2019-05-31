package pingding.logs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDAO implements AutoCloseable{

    private Connection connection;

    public LogDAO(Connection connection) {
        this.connection = connection;
    }

    public void logUrl(String url, String state, int ping) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO logs(url, state, ping) VALUES (?, ?, ?)")) {
            ps.setString(1, url);
            ps.setString(2, state);
            ps.setInt(3, ping);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
