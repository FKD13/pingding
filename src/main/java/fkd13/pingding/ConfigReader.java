package fkd13.pingding;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class ConfigReader {

    private String path;

    public ConfigReader(String path) {
        this.path = path;
    }

    public ObservableList<PingThing> getUrls() {
        ObservableList<PingThing> model = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)))) {
            String line = reader.readLine();
            while (line != null) {
                model.add(new PingThing(line.trim(), "-", "-"));
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not load config.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }
}
