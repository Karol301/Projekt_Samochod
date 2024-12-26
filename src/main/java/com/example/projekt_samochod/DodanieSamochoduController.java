package com.example.projekt_samochod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samochod.Komponent;
import samochod.Pozycja;
import samochod.Samochod;
import samochod.Silnik;

public class DodanieSamochoduController {

    @FXML
    private TextField nazwaSamochoduField;
    @FXML
    private TextField nrRejestracyjnyField;
    @FXML
    private TextField wagaSamochoduField;
    @FXML
    private TextField predkoscSamochoduField;

    @FXML
    private TextField nazwaSilnikaField;
    @FXML
    private TextField cenaSilnikaField;
    @FXML
    private TextField wagaSilnikaField;

    @FXML
    private TextField nazwaSkrzyniField;
    @FXML
    private TextField cenaSkrzyniField;
    @FXML
    private TextField wagaSkrzyniField;

    @FXML
    private TextField nazwaSprzeglaField;
    @FXML
    private TextField cenaSprzeglaField;
    @FXML
    private TextField wagaSprzeglaField;
    @FXML
    private TextField maxObrotySilnikaField;

    private OknoGlowneController parentController;

    public void setParentController(OknoGlowneController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void dodaj_samochod_accept(ActionEvent actionEvent) {
        try {
            // Pobranie danych dla samochodu
            String model = nazwaSamochoduField.getText();
            String nrRejestracyjny = nrRejestracyjnyField.getText();
            double wagaSamochodu = Double.parseDouble(wagaSamochoduField.getText());
            double predkoscMax = Double.parseDouble(predkoscSamochoduField.getText());

            // Pobranie danych dla silnika
            String nazwaSilnika = nazwaSilnikaField.getText();
            double cenaSilnika = Double.parseDouble(cenaSilnikaField.getText());
            double wagaSilnika = Double.parseDouble(wagaSilnikaField.getText());
            int maxObrotySilnika = Integer.parseInt(maxObrotySilnikaField.getText());

            //Pobranie danych dla skrzyni biegów
            String nazwaSkrzyni = nazwaSkrzyniField.getText();
            double cenaSkrzyni = Double.parseDouble(cenaSkrzyniField.getText());
            double wagaSkrzyni = Double.parseDouble(wagaSkrzyniField.getText());

            //Pobranie danych dla sprzegla
            String nazwaSprzegla = nazwaSprzeglaField.getText();
            double cenaSprzegla = Double.parseDouble(cenaSprzeglaField.getText());
            double wagaSprzegla = Double.parseDouble(wagaSprzeglaField.getText());

            // Utworzenie samochodu i silnika
            Pozycja startowaPozycja = new Pozycja(0, 0);
            Samochod samochod = new Samochod(nrRejestracyjny, model, startowaPozycja, predkoscMax, wagaSamochodu);
            Silnik silnik = new Silnik(nazwaSilnika, wagaSilnika, cenaSilnika, maxObrotySilnika);
            Komponent skrzynia_biegow_komponent = new Komponent(nazwaSkrzyni, wagaSkrzyni, cenaSkrzyni);
            Komponent sprzeglo_komponent = new Komponent(nazwaSprzegla, wagaSprzegla, cenaSprzegla);

            // Przekazanie danych do głównego okna
            if (parentController != null) {
                parentController.ustawNowySamochod(samochod, silnik, skrzynia_biegow_komponent, sprzeglo_komponent);
            }

            // Zamknięcie okna
            Stage stage = (Stage) nazwaSamochoduField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Błąd przy dodawaniu samochodu: " + e.getMessage());
        }
    }
}
