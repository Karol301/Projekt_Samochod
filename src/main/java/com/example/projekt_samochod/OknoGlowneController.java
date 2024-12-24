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
import samochod.Komponent;
import samochod.Samochod;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private TextField NazwaSkrzyniField;
    @FXML
    private TextField CenaSkrzyniField;
    @FXML
    private TextField WagaSkrzyniField;
    @FXML
    private TextField nazwaSprzeglaField;
    @FXML
    private TextField CenaSprzeglaField;
    @FXML
    private TextField WagaSprzeglaField;
    @FXML
    private ImageView carImageView;
    @FXML
    private ChoiceBox<String> choiceBoxSamochody;

    private List<Samochod> listaSamochodow = new ArrayList<>();
    private List<Komponent> listaKomponentowSilnika = new ArrayList<>();
    private List<Komponent> listaKomponentowSkrzyni = new ArrayList<>();
    private List<Komponent> listaKomponentowSprzegla = new ArrayList<>();
    private ObservableList<String> modeleSamochodow = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        choiceBoxSamochody.setItems(modeleSamochodow);

        choiceBoxSamochody.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            wyswietlDaneSamochodu(newValue);
            pokazObrazSamochodu(newValue);
        });
    }

    private void pokazObrazSamochodu(String model) {
        if (model != null && !model.isEmpty()) {
            try {
                String imagePath = "src/main/resources/samochod.png";
                Image carImage = new Image(getClass().getResource(imagePath).toExternalForm());

                carImageView.setImage(carImage);
                carImageView.setVisible(true);
            } catch (Exception e) {
                System.err.println("Nie można załadować obrazka dla modelu: " + model);
                carImageView.setImage(null);
            }
        } else {
            carImageView.setImage(null);
        }
    }

    public void ustawNowySamochod(Samochod samochod, Komponent silnik, Komponent skrzynia, Komponent sprzeglo) {
        if (samochod != null && silnik != null && skrzynia != null) {
            listaSamochodow.add(samochod);
            listaKomponentowSilnika.add(silnik);
            listaKomponentowSkrzyni.add(skrzynia);
            listaKomponentowSprzegla.add(sprzeglo);
            modeleSamochodow.add(samochod.getModel());
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

                Komponent KomponentSilnik = listaKomponentowSilnika.get(i);
                silnikNazwaField.setText(KomponentSilnik.getNazwa());
                silnikCenaField.setText(String.valueOf(KomponentSilnik.getCena()));
                silnikWagaField.setText(String.valueOf(KomponentSilnik.getWaga()));

                Komponent komponentSkrzyniBiegow = listaKomponentowSkrzyni.get(i);
                NazwaSkrzyniField.setText(komponentSkrzyniBiegow.getNazwa());
                WagaSkrzyniField.setText(String.valueOf(komponentSkrzyniBiegow.getWaga()));
                CenaSkrzyniField.setText(String.valueOf(komponentSkrzyniBiegow.getCena()));

                Komponent komponentSprzeglo = listaKomponentowSprzegla.get(i);
                nazwaSprzeglaField.setText(komponentSprzeglo.getNazwa());
                CenaSprzeglaField.setText(String.valueOf(komponentSprzeglo.getWaga()));
                WagaSprzeglaField.setText(String.valueOf(komponentSprzeglo.getCena()));
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
        NazwaSkrzyniField.clear();
        WagaSkrzyniField.clear();
        CenaSkrzyniField.clear();
        nazwaSprzeglaField.clear();
        CenaSprzeglaField.clear();
        WagaSprzeglaField.clear();
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