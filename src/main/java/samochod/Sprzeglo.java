package samochod;

public class Sprzeglo {
    private boolean stanSprzegla;

    public Sprzeglo(){
        this.stanSprzegla = false;
    }

    public void wcisnij() {
        stanSprzegla = true;
    }

    public void zwolnij() {
        stanSprzegla = false;
    }

    public boolean getStanSprzegla() {
        return stanSprzegla;
    }
}
