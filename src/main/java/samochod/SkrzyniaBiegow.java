package samochod;

public class SkrzyniaBiegow {
    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;

    public SkrzyniaBiegow(int iloscBiegow) {
        this.iloscBiegow = iloscBiegow;
        this.aktualnyBieg = 0;
        this.aktualnePrzelozenie = 1.0;
    }

    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
        }
    }

    public int getAktBieg() {
        return aktualnyBieg;
    }

    public double getAktPrzelozenie() {
        return aktualnePrzelozenie;
    }

    public void setAktPrzelozenie(double przelozenie) {
        this.aktualnePrzelozenie = przelozenie;
    }
}
