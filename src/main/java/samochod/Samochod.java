package samochod;

public class Samochod extends Thread {
    private boolean stanWlaczenia; // Stan samochodu: włączony/wyłączony
    private String nrRejestr;      // Numer rejestracyjny
    private String model;          // Model samochodu
    private double predkoscMax;    // Maksymalna prędkość
    private Pozycja aktualnaPozycja; // Aktualna pozycja na mapie
    private Pozycja cel;           // Cel ruchu
    private SkrzyniaBiegow skrzynia; // Skrzynia biegów
    private Silnik silnik;         // Silnik
    private Sprzeglo sprzeglo;     // Sprzęgło
    private double waga;           // Waga samochodu
    private boolean ruchAktywny;   // Flaga kontrolująca wątek

    public Samochod(String nrRejestr, String model, Pozycja aktualnaPozycja, double predkoscMax, double waga) {
        this.nrRejestr = nrRejestr;
        this.model = model;
        this.aktualnaPozycja = aktualnaPozycja;
        this.predkoscMax = predkoscMax;
        this.waga = waga;
        this.cel = null; // Na początku brak celu
        this.ruchAktywny = true; // Wątek uruchomiony
        this.stanWlaczenia = false; // Samochód domyślnie wyłączony
        this.start(); // Startowanie wątku
    }

    @Override
    public void run() {
        while (true) {
            if (cel != null) {
                double deltaX = cel.getX() - aktualnaPozycja.getX();
                double deltaY = cel.getY() - aktualnaPozycja.getY();
                double dystans = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (dystans > 0.2) {
                    double ruchX = (deltaX / dystans) * predkoscMax;
                    double ruchY = (deltaY / dystans) * predkoscMax;

                    aktualnaPozycja.setX(aktualnaPozycja.getX() + ruchX);
                    aktualnaPozycja.setY(aktualnaPozycja.getY() + ruchY);

                    System.out.println("Samochód porusza się. Pozycja: " + aktualnaPozycja);
                } else {
                    cel = null;
                    System.out.println("Samochód dotarł do celu.");
                }
            }

            try {
                Thread.sleep(100); // Odświeżanie co 100 ms
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

    public void wlacz() {
        this.stanWlaczenia = true;
    }

    public synchronized Pozycja getAktPozycja() {
        return aktualnaPozycja;
    }

    public synchronized void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
    }


    public void zatrzymaj() {
        this.cel = null; // Zatrzymuje ruch
    }

    public Pozycja getNowaPozycja() {
        return cel;
    }

    public double getWaga() {
        return waga;
    }

    public double getAktPredkosc() {
        return predkoscMax * (skrzynia != null ? skrzynia.getAktPrzelozenie() : 1.0);
    }

    public String getModel() {
        return model;
    }

    public String getNrRejest() {
        return nrRejestr;
    }

    public double getMaxPredkosc() {
        return predkoscMax;
    }

    public SkrzyniaBiegow getSkrzyniaBiegow() {
        return skrzynia;
    }

    public void setSkrzyniaBiegow(SkrzyniaBiegow skrzynia) {
        this.skrzynia = skrzynia;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    public void setSprzeglo(Sprzeglo sprzeglo) {
        this.sprzeglo = sprzeglo;
    }

    public Silnik getSilnik() {
        return silnik;
    }

    public void setSilnik(Silnik silnik) {
        this.silnik = silnik;
    }

    public void zatrzymajWatek() {
        this.ruchAktywny = false;
        this.interrupt(); // Przerywa wątek
    }
}
