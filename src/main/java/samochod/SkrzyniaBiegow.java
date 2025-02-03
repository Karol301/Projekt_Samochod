package samochod;

public class SkrzyniaBiegow{
    private int aktualnyBieg;
    private double aktualnePrzelozenie;
    private int iloscBiegow;

    public SkrzyniaBiegow(String model) {
        this.iloscBiegow = 6;
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
}
