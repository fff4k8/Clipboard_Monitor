package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent root = loader.load();

        Controller controller = loader.getController();

        primaryStage.setOnCloseRequest(event -> {
           controller.leaveMSGroup();
            Platform.exit();
            System.exit(0);
        });


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public void stop() throws Exception {

        super.stop();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
