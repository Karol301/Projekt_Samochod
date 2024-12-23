package samochod;

public class Silnik extends Komponent {
    private int maxObroty;
    private int obroty;

    public Silnik(String nazwa, double waga, double cena, int maxObroty) {
        super(nazwa, waga, cena);
        this.maxObroty = maxObroty;
        this.obroty = 0;
    }

    public void uruchom() {
        obroty = 1000; // domyślne obroty początkowe
    }

    public void zatrzymaj() {
        obroty = 0;
    }

    public void zwiekszObroty() {
        if (obroty < maxObroty) {
            obroty += 500;
        }
    }

    public void zmniejszObroty() {
        if (obroty > 0) {
            obroty -= 500;
        }
    }

    public int getMaxObroty() {
        return maxObroty;
    }

    public int getObroty() {
        return obroty;
    }
}

