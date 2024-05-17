package app;

import app.view.RegistracijaView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(new RegistracijaView());
        primaryStage.setTitle("Login Form");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
