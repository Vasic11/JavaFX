package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public static void main(String[] args) {
        launch();
    }
    private List<String> korisnici = new ArrayList<>();
    private List<String>nalozi = new ArrayList<>();
    private List<String>lozinke = new ArrayList<>();
    private List<String>prijavljeniKorisnic = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login Form");
        GridPane gp = new GridPane();

        gp.setAlignment(Pos.CENTER);
        gp.setHgap(5);
        gp.setVgap(5);
        gp.setPadding(new Insets(5,7,5,7));

        Label lbRegistracija = new Label("Registracija");
        Label lbIme = new Label("Ime:");
        Label lbPrezime = new Label("Prezime:");
        Label lbEmail = new Label("Email:");
        Label lbLozinka = new Label("Lozinka:");

        Label lbPrazan = new Label("");
        TextField txIme = new TextField();
        TextField txPrezime = new TextField();
        TextField txEmail = new TextField();
        PasswordField pwLozinka = new PasswordField();

        gp.addColumn(0,lbRegistracija,lbIme,lbPrezime,lbEmail,lbLozinka);
        gp.addColumn(1,lbPrazan,txIme,txPrezime,txEmail,pwLozinka);

        Button btnRegistrujSe = new Button("Registruj se");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().add(btnRegistrujSe);
        gp.add(vBox,0,5);

        Label lbPorka = new Label();
        Label lbNalog = new Label("Vec imate nalog? Prijavite se.");

        TextField txNalogEmail = new TextField();
        PasswordField pwNalogLozinka = new PasswordField();
        Button btnPrijaviSe = new Button("Prijavi se");
        Label lbPorukaPrijavi = new Label();

        //gp.addColumn(0,lbPorka,lbNalog,txNalogEmail,pwNalogLozinka);
        vBox.getChildren().addAll(lbPorka,lbNalog,txNalogEmail,pwNalogLozinka,btnPrijaviSe,lbPorukaPrijavi);
        vBox.setSpacing(5);



        btnRegistrujSe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!txEmail.getText().contains("@")&&txEmail.getText().isEmpty()&& txEmail.getText().length()<5)
                    lbPorka.setText("Niste dobro napisali Email.");
                else if (!txEmail.getText().contains("@")) {
                    lbPorka.setText("Fali '@'");
                } else if (txIme.getText().isEmpty()) {
                    lbPorka.setText("Niste uneli ime.");
                }
                else if (txPrezime.getText().isEmpty()){
                    lbPorka.setText("Niste uneli prezime");
                }
                else if(pwLozinka.getText().length()<5){
                    lbPorka.setText("Lozinka treba da bude duza.");
                }
                else{
                    lbPorka.setText("");
                    String korisnik = "Korisnicki Email: " + txEmail.getText() + " Korisnicka lozinka: " + pwLozinka.getText();
                    System.out.println(korisnik);
                    boolean postojiNalog = false;
                    for (String k : nalozi) {
                        if (k.equals(txEmail.getText())) {
                            postojiNalog = true;
                            break;
                        }
                    }
                    if (postojiNalog) {
                        lbPorka.setText("Već imate nalog.");
                    } else {
                        korisnici.add(korisnik);
                        nalozi.add(txEmail.getText());
                        lozinke.add(pwLozinka.getText());
                        txIme.clear();
                        txPrezime.clear();
                        txEmail.clear();
                        pwLozinka.clear();
                        lbPorka.setText("Uspesno ste se registrovali.");
                    }
                    for(String k:korisnici)
                        System.out.println(k + " "  + korisnici.size());

                    for(String k:nalozi)
                        System.out.println(k + " " + nalozi.size());
                }
            }
        });

        btnPrijaviSe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(nalozi.contains(txNalogEmail.getText()) && lozinke.contains(pwNalogLozinka.getText())){
                    lbPorukaPrijavi.setText("Uspesno ste se prijavili.");
                    lbPorka.setText("");
                    txNalogEmail.clear();
                    pwNalogLozinka.clear();
                    prijavljeniKorisnic.add(txNalogEmail.getText());
                    for(String k: prijavljeniKorisnic)
                        System.out.println(k);
                }
                else {
                    lbPorukaPrijavi.setText("Niste dobro uneli podatke.");
                    lbPorka.setText("");
                }
            }
        });



        Scene scene = new Scene(gp, 300, 370);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
