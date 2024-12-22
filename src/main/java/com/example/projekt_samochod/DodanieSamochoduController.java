package com.example.projekt_samochod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samochod.Pozycja;
import samochod.Samochod;

public class DodanieSamochoduController {

    @FXML
    private TextField nazwaField;
    @FXML
    private TextField nrRejestracyjnyField;
    @FXML
    private TextField wagaField;
    @FXML
    private TextField predkoscField;

    private Samochod nowySamochod;

    private OknoGlowneController parentController;

    public void setParentController(OknoGlowneController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void dodaj_samochod_accept(ActionEvent actionEvent) {
        try {
            // Pobranie danych z pól tekstowych
            String model = nazwaField.getText();
            String nrRejestracyjny = nrRejestracyjnyField.getText();
            double waga = Double.parseDouble(wagaField.getText());
            double predkosc = Double.parseDouble(predkoscField.getText());

            // Tworzenie nowego samochodu
            Pozycja aktPoz = new Pozycja(0, 0);
            nowySamochod = new Samochod(nrRejestracyjny, model, aktPoz, predkosc, waga);

            // Przekazanie nowego samochodu do głównego okna
            if (parentController != null) {
                parentController.ustawNowySamochod(nowySamochod);
            }

            // Zamknięcie okna
            Stage stage = (Stage) nazwaField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            System.out.println("Błąd przy dodawaniu samochodu: " + e.getMessage());
        }
    }
}
