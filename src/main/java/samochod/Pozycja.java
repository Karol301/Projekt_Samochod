package samochod;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "Pozycja{x=" + x + ", y=" + y + "}";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Zmień argumenty na double:
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
