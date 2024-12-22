package com.example.projekt_samochod;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import samochod.Pozycja;
import samochod.Samochod;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class OknoGlowneController {
    @FXML
    TextField modelField;
    @FXML
    TextField nrRejestracyjnyField;
    @FXML
    TextField wagaField;
    @FXML
    TextField predkoscField;

    @FXML
    public void start(ActionEvent actionEvent) {
        String model = modelField.getText();
        String nrRejestracyjny = nrRejestracyjnyField.getText();
        double waga = Double.parseDouble(wagaField.getText());
        double predkosc = Double.parseDouble(predkoscField.getText());

        Pozycja akt_poz = new Pozycja(0,0);
        Samochod samochod = new Samochod(model, nrRejestracyjny, akt_poz, waga, predkosc);

        System.out.println("Samochod wlaczony" + samochod.getMaxPredkosc());

    }

    public void stop(ActionEvent actionEvent) {
        System.out.println("Samochod wylaczony");
    }

    public void zwiekszBieg(ActionEvent actionEvent) {
        System.out.println("Bieg zwiekszony");
    }

    public void zmniejszBieg(ActionEvent actionEvent) {
        System.out.println("Bieg zmniejszony");
    }

    public void nacisnij(ActionEvent actionEvent) {
        System.out.println("Nacisnij");
    }

    public void zwolnij(ActionEvent actionEvent) {
        System.out.println("Zwolnij");
    }

    public void dodajGazu(ActionEvent actionEvent) {
        System.out.println("Gaz dodany");
    }

    public void ujmijGazu(ActionEvent actionEvent) {
        System.out.println("Gaz odjety");
    }

    @FXML
    public void dodajSamochod(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dodanie_samochodu.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setWidth(800);
            stage.setHeight(800);
            stage.setTitle("Dodaj nowy samochód");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usunsamochod(ActionEvent actionEvent) {
        System.out.println("Samochod usuniety");
    }
}