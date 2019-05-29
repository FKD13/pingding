package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;

import java.util.Timer;
import java.util.TimerTask;


public class MainCompanion {

    @FXML
    public TableView table;
    @FXML
    public TableColumn<PingThing, String> url;
    @FXML
    public TableColumn<PingThing, String> ping;
    @FXML
    public TableColumn<PingThing, String> ip;
    @FXML
    public MenuItem delete;
    @FXML
    public MenuItem insert;
    @FXML
    public MenuItem exit;

    private ObservableList<PingThing> model;
    private Ponger ponger;
    private Timer timer;

    public void initialize() {

        model = new ConfigReader("/sample/config.txt").getUrls();
        table.setItems(model);

        table.setEditable(true);

        ping.setEditable(false);
        ping.setMinWidth(100);
        ping.setCellValueFactory(new PropertyValueFactory<>("latency"));
        ping.setCellFactory(c -> new TextFieldTableCell<>());

        url.setMinWidth(200);
        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        url.setCellFactory(c -> new TextFieldTableCell<>(new DefaultStringConverter()));

        ip.setEditable(false);
        ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
        ip.setCellFactory(c -> new TextFieldTableCell<>());

        delete.setOnAction(e -> onDelete());
        insert.setOnAction(e -> onInsert());
        exit.setOnAction(e -> onExit());

        ponger = new Ponger(this);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ponger.pingUrls(model);
            }
        }, 0, 10000);
    }

    public void refresh() {
        if (table.getEditingCell() == null) {
            table.refresh();
        }
    }

    private void onExit() {
        timer.cancel();
        new ConfigWriter("/sample/config.txt").saveUrls(model);
        Platform.exit();
    }

    private void onInsert() {
        model.add(new PingThing("<new>", "-", "-"));
        table.refresh();
    }

    private void onDelete() {
        PingThing selected = model.get(table.getSelectionModel().getSelectedIndex());
        if (selected != null) {
            model.remove(selected);
        }
        table.refresh();
    }
}
