package samochod;

public class Samochod {
    private boolean stanWlaczenia;
    private String nrRejestr;
    private String model;
    private double predkoscMax;
    private Pozycja aktualnaPozycja;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;
    private Sprzeglo sprzeglo;

    public Samochod(String nrRejestr, String model, Pozycja aktualnaPozycja, double predkoscMax, double waga) {
        this.nrRejestr = nrRejestr;
        this.model = model;
        this.aktualnaPozycja = aktualnaPozycja;
        this.predkoscMax = predkoscMax;
        this.skrzynia = new SkrzyniaBiegow(6);
        this.silnik = new Silnik("Silnik", waga, 0, 5000);
        this.sprzeglo = new Sprzeglo();
    }

    public void wlacz() {
        stanWlaczenia = true;
        silnik.uruchom();
    }

    public void wylacz() {
        stanWlaczenia = false;
        silnik.zatrzymaj();
    }

    public void jedzDo(Pozycja cel) {
        aktualnaPozycja = cel;
    }

    public double getWaga() {
        return silnik.getWaga(); // uproszczone obliczenie
    }

    public double getAktPredkosc() {
        return predkoscMax * skrzynia.getAktPrzelozenie();
    }

    public Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public String getModel() {
        return model;
    }

    public String getNrRejest() {
        return nrRejestr;
    }

    public double getMaxPredkosc() {
        return predkoscMax;
    }

    public Silnik getSilnik() {
        return silnik;
    }
}