package com.example.projekt_samochod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DodanieSamochoduController {
    @FXML
    private TextField nazwaField;
    @FXML
    private TextField nrRejestracyjnyField;
    @FXML
    private TextField wagaField;
    @FXML
    private TextField predkoscField;

    @FXML
    public void dodaj_samochod_accept(ActionEvent actionEvent) {
        // Pobierz dane z pól tekstowych
        String nazwa = nazwaField.getText();
        String nrRejestracyjny = nrRejestracyjnyField.getText();
        String waga = wagaField.getText();
        String predkosc = predkoscField.getText();

        // Przetwarzanie danych (przykład)
        System.out.println("Dodano samochód:");
        System.out.println("Nazwa: " + nazwa);
        System.out.println("Nr rejestracyjny: " + nrRejestracyjny);
        System.out.println("Waga: " + waga);
        System.out.println("Prędkość: " + predkosc);
    }
}
