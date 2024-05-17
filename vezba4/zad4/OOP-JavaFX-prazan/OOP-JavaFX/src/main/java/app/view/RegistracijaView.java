package app.view;

import app.model.Baza;
import app.model.Korisnik;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegistracijaView extends VBox {
    private Label lbRegistracija;
    private GridPane gp;
    private Label lbIme;
    private Label lbPrezime;
    private Label lbEmail;
    private Label lbLozinka;
    private TextField txIme;
    private TextField txPrezime;
    private TextField txEmail;
    private PasswordField pwLozinka;
    private Button btnRegistruj;
    //Drugi deo
    private Label lbPorukaRegistruj;
    private Label lbPitanje;
    private TextField txEmailPrijava;
    private PasswordField pwLozinkaPrijava;
    private Button btnPrijavi;
    private Label lbPorukaPrijava;


    public RegistracijaView() {
        initElements();
        addElements();
        addActions();
    }

    private void addActions() {
        btnRegistruj.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               String regIme = txIme.getText();
               String regPrezime = txPrezime.getText();
               String rgEmail = txEmail.getText();
               String regLozinka = pwLozinka.getText();

               if(regIme.isEmpty()){
                   lbPorukaRegistruj.setText("Unesite ime.");
                    return;
               }
               else if (regPrezime.isEmpty()) {
                   lbPorukaRegistruj.setText("Unesite prezime.");
                   return;
               } else if (!rgEmail.contains("@")) {
                   lbPorukaRegistruj.setText("Niste uneli '@'");
                   return;
               } else if (regLozinka.length()<5) {
                   lbPorukaRegistruj.setText("Lozinka mora da bude duza od 5 karaktera.");
                   return;
               }else {
                   Korisnik korisnik = new Korisnik(regIme,regPrezime,rgEmail,regLozinka);
                   System.out.println(korisnik);
                   if(!Baza.getInstance().dodajKorisnika(korisnik)){
                       lbPorukaRegistruj.setText("Korisnik sa tim email-om vec postoji.");
                       return;
                   }
                   txIme.clear();
                   txPrezime.clear();
                   txEmail.clear();
                   pwLozinka.clear();
                   lbPorukaRegistruj.setText("Uspesno ste se registrovali.");
                   Baza.getInstance().dodajKorisnika(korisnik);
                   System.out.println("Broj registovanih korisnika: "+Baza.getInstance().getRegistovaniKorisnci().size());
               }
            }
        });

        btnPrijavi.setOnAction(e->{
            String unetiEmail = txEmailPrijava.getText();
            String unetaLozina = pwLozinkaPrijava.getText();

            for(Korisnik k: Baza.getInstance().getRegistovaniKorisnci()){
                if(k.getEmail().equals(unetiEmail)){
                    if (k.getLozinka().equals(unetaLozina)){
                        lbPorukaPrijava.setText("Uspesno ste se prijavili.");
                        Scene scene = new Scene(new MainView());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Main view");
                        stage.show();
                        return;
                    }else{
                        lbPorukaPrijava.setText("Pogresna lozinka.");
                        lbPorukaRegistruj.setText("");
                        return;
                    }
                }else{
                    lbPorukaPrijava.setText("Niste registovani sa ovim email-om.");
                    lbPorukaRegistruj.setText("");
                }
            }
        });
    }

    private void addElements() {
        gp.addColumn(0, lbIme,lbPrezime,lbEmail,lbLozinka);
        gp.addColumn(1,txIme,txPrezime,txEmail,pwLozinka);
        gp.setVgap(5);
        gp.setHgap(5);

        this.getChildren().addAll(lbRegistracija,gp,btnRegistruj,lbPorukaRegistruj,lbPitanje,txEmailPrijava,pwLozinkaPrijava,btnPrijavi,lbPorukaPrijava);
        this.setPadding(new Insets(10,10,10,10));
        this.setSpacing(5);

    }

    private void initElements() {
        lbRegistracija = new Label("Registracija");

        gp = new GridPane();
        lbIme = new Label("Ime:");
        lbPrezime = new Label("Prezime:");
        lbEmail = new Label("Email:");
        lbLozinka = new Label("Lozinka:");
        txIme = new TextField();
        txPrezime = new TextField();
        txEmail = new TextField();
        pwLozinka = new PasswordField();

        btnRegistruj = new Button("Registruj se");
        lbPorukaRegistruj = new Label();

        lbPitanje = new Label("Vec imate nalog? Prijavite se.");
        txEmailPrijava = new TextField();
        pwLozinkaPrijava = new PasswordField();
        btnPrijavi = new Button("Prijavi se");
        lbPorukaPrijava = new Label();

    }
}
