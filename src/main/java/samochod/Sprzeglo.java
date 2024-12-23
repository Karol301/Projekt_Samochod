package samochod;

public class Sprzeglo {
    private boolean stanSprzegla;

    public void wcisnij() {
        stanSprzegla = true;
    }

    public void zwolnij() {
        stanSprzegla = false;
    }

    public boolean isStanSprzegla() {
        return stanSprzegla;
    }
}
