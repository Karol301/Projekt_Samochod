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
    private ChoiceBox<String> choiceBoxSamochody;

    // Lista przechowująca obiekty Samochod
    private List<Samochod> listaSamochodow = new ArrayList<>();

    // ObservableList do aktualizacji ChoiceBox
    private ObservableList<String> modeleSamochodow = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Ustawienie ObservableList jako dane dla ChoiceBox
        choiceBoxSamochody.setItems(modeleSamochodow);

        // Listener na zmianę wyboru w ChoiceBox
        choiceBoxSamochody.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            wyswietlDaneSamochodu(newValue);
        });
    }

    // Metoda do ustawienia nowego samochodu
    public void ustawNowySamochod(Samochod nowySamochod) {
        if (nowySamochod != null) {
            // Dodaj samochód do listy
            listaSamochodow.add(nowySamochod);

            // Dodaj model do ChoiceBox
            modeleSamochodow.add(nowySamochod.getModel());
        }
    }

    // Wyświetlanie danych samochodu na podstawie wybranego modelu
    private void wyswietlDaneSamochodu(String model) {
        for (Samochod samochod : listaSamochodow) {
            if (samochod.getModel().equals(model)) {
                modelField.setText(samochod.getModel());
                nrRejestracyjnyField.setText(samochod.getNrRejest());
                wagaField.setText(String.valueOf(samochod.getWaga()));
                predkoscField.setText(String.valueOf(samochod.getMaxPredkosc()));
                return;
            }
        }
        // Jeśli nie znaleziono modelu, wyczyść pola
        modelField.clear();
        nrRejestracyjnyField.clear();
        wagaField.clear();
        predkoscField.clear();
    }

    // Obsługa przycisku dodania nowego samochodu
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

    // Obsługa przycisku usuwania samochodu
    @FXML
    public void usunsamochod(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();
        if (wybranyModel != null) {
            // Usuń samochód z listy
            listaSamochodow.removeIf(samochod -> samochod.getModel().equals(wybranyModel));

            // Usuń model z ChoiceBox
            modeleSamochodow.remove(wybranyModel);

            // Wyczyść pola szczegółów
            modelField.clear();
            nrRejestracyjnyField.clear();
            wagaField.clear();
            predkoscField.clear();
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
}
