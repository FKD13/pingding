package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private MainCompanion mainCompanion;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainCompanion = new MainCompanion();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
        loader.setController(mainCompanion);
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("PingDing");
        primaryStage.setScene(scene);
        primaryStage.show();
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
