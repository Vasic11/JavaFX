package app.model;

import java.util.ArrayList;
import java.util.List;

public class Baza {
    private static Baza instance = null;
    private List<Korisnik>registovaniKorisnci = new ArrayList<>();
    private Baza(){

    }
    public static Baza getInstance(){
        if(instance == null)
            instance = new Baza();
        return instance;
    }

    public List<Korisnik> getRegistovaniKorisnci() {
        return registovaniKorisnci;
    }

    public void setRegistovaniKorisnci(List<Korisnik> registovaniKorisnci) {
        this.registovaniKorisnci = registovaniKorisnci;
    }
    public boolean dodajKorisnika(Korisnik k){
        if(registovaniKorisnci.contains(k))
            return false;
        registovaniKorisnci.add(k);
        return true;
    }
}
