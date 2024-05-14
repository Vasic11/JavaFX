package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");


        GridPane gp = new GridPane();

        Label lbIme = new Label("Korisnicko ime: ");
        Label lbLozinka  = new Label("Lozinka: ");
        TextField unosImena = new TextField();
        PasswordField pw = new PasswordField();

        gp.addColumn(0,lbIme,lbLozinka);
        gp.addColumn(1,unosImena,pw);

        gp.setAlignment(Pos.CENTER);
        gp.setVgap(10);
        gp.setHgap(10);
        gp.setPadding(new Insets(25,25,25,25));

        Button btn = new Button("Prijavi se");
        HBox hb = new HBox();
        hb.setAlignment(Pos.BOTTOM_RIGHT);
        hb.getChildren().addAll(btn);
        Label action = new Label();
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                unosImena.clear();
                pw.clear();
                action.setText("Pokrenuta akcija za prijavu.");


            }
        });


        gp.add(hb,1,2);
        gp.add(action,1,3);







        Scene scene = new Scene(gp ,300 , 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
