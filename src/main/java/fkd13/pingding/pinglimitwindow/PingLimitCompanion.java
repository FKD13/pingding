package fkd13.pingding.pinglimitwindow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import fkd13.pingding.MainCompanion;
import fkd13.pingding.Popup;

public class PingLimitCompanion implements Popup {

    @FXML
    public TextField textfield;
    @FXML
    public Slider slider;
    @FXML
    public Button confirm;

    private Stage stage;
    private MainCompanion mainCompanion;

    public PingLimitCompanion(MainCompanion mainCompanion) {
        this.mainCompanion = mainCompanion;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        slider.setMin(0);
        slider.setMax(500);
        textfield.textProperty().bindBidirectional(slider.valueProperty(), new NumberStringConverter());
        confirm.setOnAction(e -> onConfirm());

        slider.setValue(mainCompanion.getGoodPingLimit());
    }

    private void onConfirm() {
        mainCompanion.setGoodPingLimit((int) Math.ceil(slider.getValue()));
        stage.close();
    }
}
