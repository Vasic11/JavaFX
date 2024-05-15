package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Komentar");
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setPadding(new Insets(10,10,10,10));
        Label lbIme = new Label("Ime:");
        Label lbEmail = new Label("Email:");
        Label lbKomentar = new Label("Komentar:");
        TextField txIme = new TextField();
        TextField txEmail = new TextField();
        TextArea txKomenrae = new TextArea();
        txKomenrae.setWrapText(true);
        txKomenrae.setPrefColumnCount(20);
        txKomenrae.setPrefRowCount(5);
        gp.addColumn(0, lbIme, lbEmail, lbKomentar);
        gp.addColumn(1, txIme, txEmail, txKomenrae);

        Button btn1 = new Button("Ponisti");
        Button btn2 = new Button("Snimi");
        Label lbPoruka = new Label();

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                txIme.clear();
                txEmail.clear();
                txKomenrae.clear();
                lbPoruka.setText("");
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!txEmail.getText().contains("@"))
                    lbPoruka.setText("Niste uneli tacan email.");
                else{
                    lbPoruka.setText("");
                }
            }
        });
        txIme.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.isControlDown() && event.getCode() == KeyCode.S){
                    btn2.fire();
                if (!txEmail.getText().contains("@"))
                    lbPoruka.setText("Niste uneli tacan email.");
                else
                    lbPoruka.setText("");}
            }
        });
        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().addAll(btn1,btn2,lbPoruka);
        gp.add(hBox,1,3);
        gp.add(lbPoruka,1,5);



        Scene scene = new Scene(gp,350,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
