package samochod;

public class Komponent {
    protected String nazwa;
    protected double waga;
    protected double cena;

    public Komponent(String nazwa, double waga, double cena) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getWaga() {
        return waga;
    }

    public double getCena() {
        return cena;
    }
}
