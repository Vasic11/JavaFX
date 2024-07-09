package app.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrviProzor extends VBox{
    private GridPane gridPane;
    private Label lblPrazan;
    private HBox hBox1;
    private Label lblUnesiBrojPitanja;
    private TextField textField;
    private TextArea textArea;
    private Button btnObrisi;
    private List<String> listaPitanja;
    private Label lblIzvucenaPitanja;
    private ListView lwIzvucenaPitanja;
    private Button btnOdaberi;
    private ObservableList<String>olPitanja;
    private ObservableList<String>olOdabranaPitanja;
    private Label lblPoruka;
    private int brojPitanja;
    private List<String>randomLista;

    public PrviProzor(){
        initElements();
        addElements();
        addActions();
    }

    private void addActions() {
        btnOdaberi.setOnAction(e->{
            String tekst = textArea.getText();
            listaPitanja.clear();
            olPitanja.clear();
            String[] splitovano = tekst.split("[0-9.]");
            try {
                brojPitanja = Integer.parseInt(textField.getText());
                for (String s : splitovano) {
                    if (s.isEmpty() || s.trim().isEmpty()) {
                        continue;
                    } else {
                        listaPitanja.add(s.trim());
                    }

                }
                if(brojPitanja>listaPitanja.size()) {
                    lblPoruka.setText("Proveri broj pitanja");
                    return;
                }
                System.out.println(listaPitanja);
                for (String l : listaPitanja) {
                    System.out.println(l.trim());
                    olPitanja.addAll(l);
                }


                randomLista = getRandomElements(listaPitanja,brojPitanja);
                olOdabranaPitanja.clear();
                for (String s: randomLista) {
                    if(!olOdabranaPitanja.contains(s)){
                        olOdabranaPitanja.add(s);
                    }
                }

                lwIzvucenaPitanja.setItems(olOdabranaPitanja);
                lblPoruka.setText("");
            }catch (Exception a){
                lblPoruka.setText("Proveri unos broja");
            }



        });

        btnObrisi.setOnAction(e->{
            olPitanja.clear();
            textArea.clear();
            listaPitanja.clear();
            olOdabranaPitanja.clear();
            lblPoruka.setText("Obrisano je sve");


        });
    }

    private void addElements() {
        hBox1.getChildren().addAll(lblUnesiBrojPitanja,textField);
        hBox1.setSpacing(10);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        gridPane.addRow(0,hBox1,lblPrazan,lblIzvucenaPitanja);
        gridPane.addRow(1,textArea,btnOdaberi,lwIzvucenaPitanja);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        this.getChildren().addAll(gridPane,btnObrisi,lblPoruka);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    private void initElements() {
        olPitanja = FXCollections.observableArrayList();
        olOdabranaPitanja = FXCollections.observableArrayList();
        randomLista = new ArrayList<>();
        hBox1 = new HBox();
        lblUnesiBrojPitanja = new Label("Unesite broj pitanja:");
        textField = new TextField();
        textField.setPrefWidth(40);
        listaPitanja = new ArrayList<>();
        textArea = new TextArea();
        btnObrisi = new Button("Obrisi sve");
        lblIzvucenaPitanja = new Label("Izvucena pitanja:");
        lwIzvucenaPitanja = new ListView<>();
        lwIzvucenaPitanja.setPrefWidth(600);
        btnOdaberi = new Button("Odaberi");
        gridPane = new GridPane();
        lblPrazan = new Label();
        lblPoruka = new Label();
        lblPoruka.setTextFill(Color.RED);


    }

    public List<String> getRandomElements(List<String> list, int numberOfElements) {
        Random random = new Random();
        List<String> randomElements = new ArrayList<>();
        List<String> tempList = new ArrayList<>(list);

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(tempList.size());
            randomElements.add(tempList.remove(randomIndex));
        }

        return randomElements;
    }

}
