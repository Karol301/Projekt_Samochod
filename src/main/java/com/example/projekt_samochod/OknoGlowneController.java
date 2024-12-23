package com.example.projekt_samochod;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samochod.Samochod;
import samochod.Silnik;

import java.util.ArrayList;
import java.util.List;

public class OknoGlowneController {

    @FXML
    private TextField modelField;
    @FXML
    private TextField nrRejestracyjnyField;
    @FXML
    private TextField wagaField;
    @FXML
    private TextField predkoscField;
    @FXML
    private TextField silnikNazwaField;
    @FXML
    private TextField silnikCenaField;
    @FXML
    private TextField silnikWagaField;
    @FXML
    private TextField MaxObrotySilnikaField;

    @FXML
    private ChoiceBox<String> choiceBoxSamochody;

    private List<Samochod> listaSamochodow = new ArrayList<>();
    private List<Silnik> listaSilnikow = new ArrayList<>();
    private ObservableList<String> modeleSamochodow = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        choiceBoxSamochody.setItems(modeleSamochodow);

        choiceBoxSamochody.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            wyswietlDaneSamochodu(newValue);
        });
    }

    public void ustawNowySamochod(Samochod samochod) {
        if (samochod != null) {
            listaSamochodow.add(samochod);
            modeleSamochodow.add(samochod.getModel());
        }
    }

    public void ustawNowySilnik(Silnik silnik) {
        if (silnik != null) {
            listaSilnikow.add(silnik);
        }
    }

    private void wyswietlDaneSamochodu(String model) {
        for (int i = 0; i < listaSamochodow.size(); i++) {
            Samochod samochod = listaSamochodow.get(i);
            if (samochod.getModel().equals(model)) {
                modelField.setText(samochod.getModel());
                nrRejestracyjnyField.setText(samochod.getNrRejest());
                wagaField.setText(String.valueOf(samochod.getWaga()));
                predkoscField.setText(String.valueOf(samochod.getMaxPredkosc()));

                Silnik silnik = listaSilnikow.get(i);
                silnikNazwaField.setText(silnik.getNazwa());
                silnikCenaField.setText(String.valueOf(silnik.getCena()));
                silnikWagaField.setText(String.valueOf(silnik.getWaga()));
                MaxObrotySilnikaField.setText(String.valueOf(silnik.getMaxObroty()));
                return;
            }
        }
        modelField.clear();
        nrRejestracyjnyField.clear();
        wagaField.clear();
        predkoscField.clear();
        silnikNazwaField.clear();
        silnikCenaField.clear();
        silnikWagaField.clear();
        MaxObrotySilnikaField.clear();
    }

    @FXML
    public void dodajSamochod(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dodanie_samochodu.fxml"));
            Parent root = loader.load();

            // Pobranie kontrolera dla nowego okna
            DodanieSamochoduController controller = loader.getController();
            controller.setParentController(this);

            Stage stage = new Stage();
            stage.setTitle("Dodaj nowy samochód");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(ActionEvent actionEvent) {
    }

    public void stop(ActionEvent actionEvent) {
    }

    public void zwiekszBieg(ActionEvent actionEvent) {
    }

    public void zmniejszBieg(ActionEvent actionEvent) {
    }

    public void nacisnij(ActionEvent actionEvent) {
    }

    public void zwolnij(ActionEvent actionEvent) {
    }

    public void dodajGazu(ActionEvent actionEvent) {
    }

    public void ujmijGazu(ActionEvent actionEvent) {
    }

    public void usunsamochod(ActionEvent actionEvent) {
    }
}