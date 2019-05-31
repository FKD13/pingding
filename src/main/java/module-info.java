module pingding {
    requires javafx.controls;
    requires javafx.fxml;

    opens fkd13.pingding to javafx.fxml;
    exports fkd13.pingding;
}