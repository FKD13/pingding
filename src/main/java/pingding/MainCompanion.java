package pingding;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import pingding.pinglimitwindow.PingLimitCompanion;

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
    @FXML
    public MenuItem pinglimit;


    private ObservableList<PingThing> model;
    private Ponger ponger;
    private Timer timer;
    private Main main;

    // some settings
    private int goodPingLimit = 30;

    public MainCompanion(Main main) {
        this.main = main;
    }

    public void initialize() {

        model = new ConfigReader("/config.txt").getUrls();
        ponger = new Ponger(this);

        table.setItems(model);

        table.setEditable(true);

        ping.setEditable(false);
        ping.setMinWidth(100);
        ping.setCellValueFactory(new PropertyValueFactory<>("latency"));
        ping.setCellFactory(c -> new PingCell(this));

        url.setMinWidth(200);
        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        url.setCellFactory(c -> new TextFieldTableCell<>(new DefaultStringConverter()));
        url.setOnEditCommit(new UrlEditCommitHandler(ponger));

        ip.setEditable(false);
        ip.setCellValueFactory(new PropertyValueFactory<>("ip"));
        ip.setCellFactory(c -> new TextFieldTableCell<>());

        delete.setOnAction(e -> onDelete());
        insert.setOnAction(e -> onInsert());
        exit.setOnAction(e -> onExit());
        pinglimit.setOnAction(e -> onPingLimit());

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
        Platform.exit();
    }

    private void onPingLimit() {
        main.openPopupWindow("/PingLimitWindow.fxml", "PingLimit", new PingLimitCompanion(this));
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

    public void quit() {
        timer.cancel();
        new ConfigWriter("/config.txt").saveUrls(model);
    }

    //getters and setters

    public int getGoodPingLimit() {
        return goodPingLimit;
    }

    public void setGoodPingLimit(int goodPingLimit) {
        this.goodPingLimit = goodPingLimit;
    }
}
