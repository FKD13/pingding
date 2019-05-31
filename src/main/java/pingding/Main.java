package pingding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private MainCompanion mainCompanion;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainCompanion = new MainCompanion(this);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        loader.setController(mainCompanion);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("PingDing");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openPopupWindow(String pathToFxml, String windowName, Popup popup) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(pathToFxml));
            loader.setController(popup);
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(windowName);
            stage.setScene(scene);
            stage.show();
            popup.setStage(stage);
        } catch (IOException e) {
            System.err.println("Could not open popup");
        }
    }

    @Override
    public void stop() throws Exception {
        mainCompanion.quit();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
