package app;

import app.view.PrviProzor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class App extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new PrviProzor());
        stage.setTitle("Proberi znanje");
        stage.setScene(scene);
        stage.show();
    }
}
