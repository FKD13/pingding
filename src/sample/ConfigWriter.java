package sample;

import javafx.collections.ObservableList;

import java.io.*;
import java.net.URISyntaxException;

public class ConfigWriter {

    private String path;

    public ConfigWriter(String path) {
        this.path = path;
    }

    public void saveUrls(ObservableList<PingThing> model) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(new File(getClass().getResource(path).toURI())))){
            for (PingThing p: model) {
                writer.println(p.getUrl());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
