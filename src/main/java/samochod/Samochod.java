package samochod;

import com.example.projekt_samochod.OknoGlowneController;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;

public class Samochod extends Thread {
    private boolean stanWlaczenia;
    private String nrRejestr;
    private String model;
    private double predkoscMax;
    private Pozycja aktualnaPozycja;
    private Pozycja cel;
    private SkrzyniaBiegow skrzynia;
    private Silnik silnik;
    private Sprzeglo sprzeglo;
    private double waga;
    private int aktPredkosc;
    private boolean ruchAktywny;

    // Lista subskrybentów
    private List<Listener> listeners = new ArrayList<>();

    public Samochod(String nrRejestr, String model, Pozycja aktualnaPozycja, double predkoscMax, double waga) {
        this.nrRejestr = nrRejestr;
        this.model = model;
        this.aktualnaPozycja = aktualnaPozycja;
        this.predkoscMax = predkoscMax;
        this.waga = waga;
        this.cel = null;
        this.ruchAktywny = true;
        this.stanWlaczenia = false;
        this.start();
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }
    public void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    @Override
    public void run() {
        System.out.println("Metoda run() uruchomiona.");

        while (ruchAktywny) {
            if (cel != null) {
                double deltaX = cel.getX() - aktualnaPozycja.getX();
                double deltaY = cel.getY() - aktualnaPozycja.getY();
                double dystans = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (dystans > 0.3) {
                    double aktPredkosc = getAktPredkosc();
                    double ruchX = (deltaX / dystans) * (aktPredkosc / 25);
                    double ruchY = (deltaY / dystans) * (aktPredkosc / 25);

                    aktualnaPozycja.setX(aktualnaPozycja.getX() + ruchX);
                    aktualnaPozycja.setY(aktualnaPozycja.getY() + ruchY);

                    System.out.println("Samochód porusza się. Pozycja: " + aktualnaPozycja);

                    notifyListeners();
                } else {
                    cel = null;
                    System.out.println("Samochód dotarł do celu.");
                }
            }

            try {
                double aktPredkosc = getAktPredkosc();
                long delay = (long) Math.max(10, 1000 / (aktPredkosc + 1));
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void wylacz() {
        this.stanWlaczenia = false;
        if (silnik != null) {
            silnik.zatrzymaj();
        }
    }

    public int getAktPredkosc() {
        if (silnik != null) {
            aktPredkosc = silnik.getPredkosc();
        } else {
            aktPredkosc = 0;
        }
        return aktPredkosc;
    }

    public void wlacz() {
        this.stanWlaczenia = true;
    }

    public boolean getStan() {return stanWlaczenia;}

    public synchronized Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public synchronized void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }

    public Pozycja getNowaPozycja() {
        return cel;
    }

    public double getWaga() {
        return waga;
    }

    public String getModel() { return model;}

    public String getNrRejest() {
        return nrRejestr;
    }

    public double getMaxPredkosc() {
        return predkoscMax;
    }

    public void setSilnik(Silnik silnik) {
        this.silnik = silnik;
    }
}
