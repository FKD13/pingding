package pingding.logs;

import pingding.MainCompanion;
import pingding.PingThing;

import java.sql.SQLException;

public class Logger {

    private MainCompanion mainCompanion;

    public Logger(MainCompanion mainCompanion) {
        this.mainCompanion = mainCompanion;
    }

    public void logPingThing(PingThing pingThing) {
        try (LogDAO logDAO = mainCompanion.getSqliteDAP().getSqliteDAC().getLogDAO()) {
            int ping = -1;
            String state = "offline";
            if (pingThing.getLatency().matches("[0-9]+")) {
                ping = Integer.parseInt(pingThing.getLatency());
                state = "bad";
            }
            logDAO.logUrl(pingThing.getUrl(), state, ping);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
