package samochod;

public class Komponent {
    private String nazwa;
    private double waga;
    private double cena;

    public Komponent(double cena, double waga, String nazwa) {
        this.cena = cena;
        this.waga = waga;
        this.nazwa = nazwa;
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
