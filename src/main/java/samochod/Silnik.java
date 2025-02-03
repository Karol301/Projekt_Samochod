package samochod;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;
    private int predkosc;
    private boolean stanWlaczenia;

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        obroty = 700;
        stanWlaczenia = true;
    }

    public String getNazwa() {
        return super.getNazwa();
    }

    public void zatrzymaj() {
        obroty = 0;
        stanWlaczenia = false;
    }

    public void zwiekszObroty() {
        if (obroty < maxObroty) {
            obroty += 300;
        }
    }

    public void zmniejszObroty() {
        if (obroty > 700) {
            obroty -= 300;
        }
    }

    public void dodajgazu() {
        if (obroty > 700 && obroty < 4000) {
            predkosc += 3;
        }
    }
    public void ujmijgazu(){
        if (obroty > 700 && obroty < 4000) {
            predkosc -= 3;
        }
    }

    public void resetujObroty() {
        obroty = 2000;
    }

    public int getMaxObroty() {
        return maxObroty;
    }

    public int getObroty() {
        return obroty;
    }

    public int getPredkosc() {
        return predkosc;
    }
}

