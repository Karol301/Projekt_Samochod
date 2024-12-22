package samochod;

public class Samochod {
    private boolean stanWlaczenia;
    private String nrRejest;
    private String model;
    private double maxPredkosc;
    private double aktPredkosc;
    private double waga;
    private Pozycja aktPozycja;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;

    public Samochod(String nrRejest, String model, Pozycja aktpozycja, double maxPredkosc, double waga) {
        this.stanWlaczenia = false;
        this.nrRejest = nrRejest;
        this.model = model;
        this.maxPredkosc = maxPredkosc;
        this.aktPredkosc = 0;
        this.waga = waga;
        this.aktPozycja = aktpozycja;
    }

    public void wlacz(){
        stanWlaczenia = true;
    }
    public void wylacz(){
        stanWlaczenia = false;
    }
    public double getWaga(){
        return waga;
    }
    public double getAktPredkosc(){
        return aktPredkosc;
    }
    public Pozycja getAktPozycja(){
        return aktPozycja;
    }
    public double getMaxPredkosc(){
        return maxPredkosc;
    }
    private boolean StanWlaczenia(){
        return stanWlaczenia;
    }
    public String getNrRejest(){
        return nrRejest;
    }
    public String getModel(){
        return model;
    }
    private double maxPredkosc(){
        return maxPredkosc;
    }
}
