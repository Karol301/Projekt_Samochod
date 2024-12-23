package com.example.projekt_samochod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    private TextField MaxObrotySilnikaField;

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
            int maxObroty = Integer.parseInt(MaxObrotySilnikaField.getText());

            // Utworzenie samochodu i silnika
            Pozycja startowaPozycja = new Pozycja(0, 0);
            Silnik silnik = new Silnik(nazwaSilnika, wagaSilnika, cenaSilnika, maxObroty);
            Samochod samochod = new Samochod(nrRejestracyjny, model, startowaPozycja, predkoscMax, wagaSamochodu);

            // Przekazanie danych do głównego okna
            if (parentController != null) {
                parentController.ustawNowySamochod(samochod);
            }

            if (parentController != null) {
                parentController.ustawNowySilnik(silnik);
            }

            // Zamknięcie okna
            Stage stage = (Stage) nazwaSamochoduField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Błąd przy dodawaniu samochodu: " + e.getMessage());
        }
    }
}
