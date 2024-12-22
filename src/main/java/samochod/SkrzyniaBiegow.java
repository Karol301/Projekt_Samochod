package samochod;

public class SkrzyniaBiegow {
    private int aktualnyBieg;
    private int iloscBiegow;
    private double aktualnePrzelozenie;
    private Sprzeglo sprzeglo;

    public SkrzyniaBiegow(int iloscBiegow) {
        this.aktualnyBieg = 0;
        this.iloscBiegow = iloscBiegow;
        this.sprzeglo = new Sprzeglo();
    }

    public void zwiększBieg() {
        aktualnyBieg++;
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

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }
}
