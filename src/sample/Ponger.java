package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.InetAddress;

public class Ponger {

    private MainCompanion mainCompanion;

    public Ponger(MainCompanion mainCompanion) {
        this.mainCompanion = mainCompanion;
    }

    public void pingUrls(ObservableList<PingThing> model) {
        for (PingThing p: model) {
            if (!p.getUrl().equals("<new>")) {
                new Thread(() -> pingUrl(p)).start();
            }
        }
    }

    private void pingUrl(PingThing pingThing) {
        try {
            InetAddress address = InetAddress.getByName(pingThing.getUrl());
            long startTime = System.nanoTime();
            if (address.isReachable(3000)) {
                long endTime = System.nanoTime();
                pingThing.setLatency(Math.round((endTime - startTime) / 1000000.0) + " ms");
            } else {
                pingThing.setLatency("offline");
            }
            String ip = address.getHostAddress();
            if (ip != null) {
                pingThing.setIp(ip);
            }
        } catch (UnknownError e) {
            pingThing.setLatency("UnknownHost");
        } catch (IOException e) {
            pingThing.setLatency("Error");
        }
        Platform.runLater(() -> mainCompanion.refresh());
    }
}
