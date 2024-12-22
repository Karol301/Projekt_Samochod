package samochod;

public class Silnik {
    private int maxObroty;
    private int obroty;

    public Silnik(int maxObroty) {
        this.maxObroty = maxObroty;

    }

    public void uruchom() {
        this.obroty = 1000;
    }

    public void zatrzymaj() {
        this.obroty = 0;
    }

    public int getObroty() {
        return this.obroty;
    }

    public void zwiekszObroty(int wartosc) {
        if (this.obroty + wartosc <= maxObroty) {
            this.obroty += wartosc;
        } else {
            this.obroty = maxObroty;
        }
    }

    public void zmniejszObroty(int wartosc) {
        if (this.obroty - wartosc >= 0) {
            this.obroty -= wartosc;
        } else {
            this.obroty = 0;
        }
    }
}

