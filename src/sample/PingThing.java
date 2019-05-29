package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PingThing {

    private StringProperty url = new SimpleStringProperty();
    private StringProperty latency = new SimpleStringProperty();
    private StringProperty ip = new SimpleStringProperty();

    public PingThing(String url, String latency, String ip) {
        this.url.set(url);
        this.latency.set(latency);
        this.ip.set(ip);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getLatency() {
        return latency.get();
    }

    public void setLatency(String latency) {
        this.latency.set(latency);
    }

    public String getIp() {
        return ip.get();
    }

    public void setIp(String ip) {
        this.ip.set(ip);
    }

    // get properties


    public StringProperty ipProperty() {
        return ip;
    }

    public StringProperty urlProperty() {
        return url;
    }

    public StringProperty latencyProperty() {
        return latency;
    }
}
