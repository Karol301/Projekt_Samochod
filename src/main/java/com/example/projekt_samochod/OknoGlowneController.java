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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import samochod.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OknoGlowneController implements Listener {

    public TextField BiegSkrzyniField;
    @FXML
    public TextField StanSprzeglaField;
    @FXML
    public Pane mapa;
    public TextField aktualnaPredkoscField;
    public TextField aktualneObrotyField;
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
    private ImageView carIcon;
    @FXML
    private ChoiceBox<String> choiceBoxSamochody;

    private List<Samochod> listaSamochodow = new ArrayList<>();
    private List<Silnik> listaKomponentowSilnika = new ArrayList<>();
    private List<Komponent> listaKomponentowSkrzyni = new ArrayList<>();
    private List<Komponent> listaKomponentowSprzegla = new ArrayList<>();
    private ObservableList<String> modeleSamochodow = FXCollections.observableArrayList();
    public HashMap<String, String> stanSprzeglaSamochodu = new HashMap<>();
    public HashMap<String, Integer> biegSamochodu = new HashMap<>();
    public HashMap<String, Integer> predkoscSamochodu = new HashMap<>();
    public HashMap<String, Integer> obrotySamochodu = new HashMap<>();

    private Sprzeglo sprzeglo;
    private SkrzyniaBiegow skrzynia_biegow;
    private Samochod samochod;
    private Timeline timeline;
    private Silnik silnik;

    @Override
    public void update() {
        Platform.runLater(this::refresh);
    }

    @FXML
    public void initialize() {
        choiceBoxSamochody.setItems(modeleSamochodow);

        choiceBoxSamochody.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            wyswietlDaneSamochodu(newValue);
            if (newValue != null) {
                String wybranyModel = newValue;
                skrzynia_biegow = new SkrzyniaBiegow(wybranyModel);
                sprzeglo = new Sprzeglo(wybranyModel);
                aktualnaPredkoscField.setText("0");
                aktualneObrotyField.setText("0");
                for (Samochod s : listaSamochodow) {
                    if (s.getModel().equals(newValue)) {
                        samochod = s;
                        refresh();
                        break;
                    }
                }
                for (Silnik s : listaKomponentowSilnika) {
                    if (s.getNazwa().equals(newValue)) {
                        silnik = s;
                    }
                }
            }
        });

        carIcon = new ImageView(new Image(getClass().getResource("/com/example/projekt_samochod/samochod.png").toExternalForm()));
        carIcon.setFitWidth(50);
        carIcon.setFitHeight(50);
        mapa.getChildren().add(carIcon);

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> refresh()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        mapa.setOnMouseClicked(this::obsluzKlikniecieMapy);
    }

    private void obsluzKlikniecieMapy(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        Pozycja nowaPozycja = new Pozycja(x, y);
        System.out.println("Klik na mapie: x=" + x + ", y=" + y);

        if (samochod != null) {
            samochod.jedzDo(nowaPozycja);
            samochod.wlacz();
            System.out.println("Nowy cel: " + samochod.getNowaPozycja());
        }
        refresh();
    }

    private void refresh() {
        if (samochod != null) {
            System.out.println("Aktualna pozycja samochodu: " + samochod.getAktPozycja().getX() + ", " + samochod.getAktPozycja().getY());

            Platform.runLater(() -> {
                carIcon.setTranslateX(samochod.getAktPozycja().getX());
                carIcon.setTranslateY(samochod.getAktPozycja().getY());
            });
        }
    }


    public void ustawNowySamochod(Samochod samochod, Silnik silnik, Komponent skrzynia, Komponent sprzeglo) {
        if (samochod != null && silnik != null && skrzynia != null && sprzeglo != null) {
            listaSamochodow.add(samochod);
            samochod.addListener(this);
            listaKomponentowSilnika.add(silnik);
            listaKomponentowSkrzyni.add(skrzynia);
            listaKomponentowSprzegla.add(sprzeglo);
            modeleSamochodow.add(samochod.getModel());

            choiceBoxSamochody.setItems(modeleSamochodow);

            this.silnik = silnik;
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

                Silnik silnik = listaKomponentowSilnika.get(i);
                silnikNazwaField.setText(silnik.getNazwa());
                silnikCenaField.setText(String.valueOf(silnik.getCena()));
                silnikWagaField.setText(String.valueOf(silnik.getWaga()));
                MaxObrotySilnikaField.setText(String.valueOf(silnik.getMaxObroty()));

                Komponent komponentSkrzyniBiegow = listaKomponentowSkrzyni.get(i);
                NazwaSkrzyniField.setText(komponentSkrzyniBiegow.getNazwa());
                WagaSkrzyniField.setText(String.valueOf(komponentSkrzyniBiegow.getWaga()));
                CenaSkrzyniField.setText(String.valueOf(komponentSkrzyniBiegow.getCena()));

                Komponent komponentSprzeglo = listaKomponentowSprzegla.get(i);
                nazwaSprzeglaField.setText(komponentSprzeglo.getNazwa());
                CenaSprzeglaField.setText(String.valueOf(komponentSprzeglo.getCena()));
                WagaSprzeglaField.setText(String.valueOf(komponentSprzeglo.getWaga()));

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

    private void usunWybranySamochod(String model) {
        for (int i = 0; i < listaSamochodow.size(); i++) {
            if (listaSamochodow.get(i).getModel().equals(model)) {
                listaSamochodow.remove(i);
                samochod.removeListener(this);
                listaKomponentowSilnika.remove(i);
                listaKomponentowSkrzyni.remove(i);
                listaKomponentowSprzegla.remove(i);
                modeleSamochodow.remove(model);
                break;
            }
        }
    }

    public void usunsamochod(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();

        if (wybranyModel != null) {
            usunWybranySamochod(wybranyModel);
        } else {
            System.out.println("Nie wybrano modelu do usunięcia");
        }
    }

    public void nacisnij(ActionEvent actionEvent) {
        sprzeglo.wcisnij();
        boolean akt_stan_sprzegla = sprzeglo.getStanSprzegla();

        String wybranyModel = choiceBoxSamochody.getValue();
        if (wybranyModel != null && samochod.getStan()) {
            stanSprzeglaSamochodu.put(wybranyModel, String.valueOf(akt_stan_sprzegla));

            for (HashMap.Entry<String, String> entry : stanSprzeglaSamochodu.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (wybranyModel.equals(key)){
                    System.out.println("Model: " + entry.getKey() + ", Stan: " + entry.getValue());

                    if (value == "true"){
                        StanSprzeglaField.setText("Wciśnięte");
                    }
                    else {
                        StanSprzeglaField.setText("Nie wciśnięte");
                    }
                }
            }

        } else {
          System.out.println("Nie wybrano modelu");
        }
    }

    public void zwolnij(ActionEvent actionEvent) {
        sprzeglo.zwolnij();
        boolean akt_stan_sprzegla = sprzeglo.getStanSprzegla();

        String wybranyModel = choiceBoxSamochody.getValue();
        if (wybranyModel != null && samochod.getStan()) {
            stanSprzeglaSamochodu.put(wybranyModel, String.valueOf(akt_stan_sprzegla));

            for (HashMap.Entry<String, String> entry : stanSprzeglaSamochodu.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (wybranyModel.equals(key)){
                    if (value == "true"){
                        StanSprzeglaField.setText("Wciśnięte");
                    }
                    else {
                        StanSprzeglaField.setText("Nie wciśnięte");
                    }
                }

            }
            System.out.println("Aktualna zawartość mapy stanu Sprzegla:");
            for (HashMap.Entry<String, String> entry : stanSprzeglaSamochodu.entrySet()) {
                System.out.println("Model: " + entry.getKey() + ", Stan: " + entry.getValue());
            }
        } else {
            System.out.println("Nie wybrano modelu");
        }
    }

    public void zwiekszBieg(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();
        String stan_sprzegla = stanSprzeglaSamochodu.get(wybranyModel);
        if (stan_sprzegla == "true") {
            skrzynia_biegow.zwiekszBieg();
            Integer akt_bieg = skrzynia_biegow.getAktBieg();

            if (wybranyModel != null && samochod.getStan()) {
                biegSamochodu.put(wybranyModel, akt_bieg);

                // Wyświetlenie biegu dla wybranego modelu w polu tekstowym
                for (HashMap.Entry<String, Integer> entry : biegSamochodu.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (wybranyModel.equals(key)) {
                        BiegSkrzyniField.setText(String.valueOf(value));
                        silnik.resetujObroty();
                        aktualneObrotyField.setText(String.valueOf(silnik.getObroty()));
                    }
                }
            }
        }
        else {
            System.out.println("Nie wybrano modelu");
        }
    }

    public void zmniejszBieg(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();
        String stan_sprzegla = stanSprzeglaSamochodu.get(wybranyModel);
        if (stan_sprzegla == "true") {
            skrzynia_biegow.zmniejszBieg();
            Integer akt_bieg = skrzynia_biegow.getAktBieg();

            if (wybranyModel != null && samochod.getStan()) {
                biegSamochodu.put(wybranyModel, akt_bieg);
                for (HashMap.Entry<String, Integer> entry : biegSamochodu.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (wybranyModel.equals(key)) {
                        BiegSkrzyniField.setText(String.valueOf(value));
                        silnik.resetujObroty();
                        aktualneObrotyField.setText(String.valueOf(silnik.getObroty()));
                    }
                }
            }
        }
        else {
            System.out.println("Nie wybrano modelu");
        }
    }

    public void dodajGazu(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();

        if (wybranyModel != null && samochod.getStan()) {
            silnik.zwiekszObroty();
            silnik.dodajgazu();
            Integer aktObroty = silnik.getObroty();
            Integer aktPredkosc = silnik.getPredkosc();

            obrotySamochodu.put(wybranyModel, aktObroty);
            predkoscSamochodu.put(wybranyModel, aktPredkosc);

            for (HashMap.Entry<String, Integer> entry : obrotySamochodu.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (wybranyModel.equals(key)) {
                    aktualneObrotyField.setText(String.valueOf(value));
                }
            }
            for (HashMap.Entry<String, Integer> entry : predkoscSamochodu.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (wybranyModel.equals(key)) {
                    aktualnaPredkoscField.setText(String.valueOf(value));
                }
            }
            samochod.setSilnik(silnik);
            int predkosc = samochod.getAktPredkosc();
            System.out.println("Aktualna prędkość samochodu: " + predkosc + " km/h");
        }
    }

    public void ujmijGazu(ActionEvent actionEvent) {
        String wybranyModel = choiceBoxSamochody.getValue();

        if (wybranyModel != null && samochod.getStan()) {
            silnik.zmniejszObroty();
            silnik.ujmijgazu();
            Integer aktObroty = silnik.getObroty();
            Integer aktPredkosc = silnik.getPredkosc();

            obrotySamochodu.put(wybranyModel, aktObroty);
            predkoscSamochodu.put(wybranyModel, aktPredkosc);

            for (HashMap.Entry<String, Integer> entry : obrotySamochodu.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (wybranyModel.equals(key)) {
                    aktualneObrotyField.setText(String.valueOf(value));
                }
            }
            for (HashMap.Entry<String, Integer> entry : predkoscSamochodu.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (wybranyModel.equals(key)) {
                    aktualnaPredkoscField.setText(String.valueOf(value));
                }
            }
            samochod.setSilnik(silnik);
            int predkosc = samochod.getAktPredkosc();
            System.out.println("Aktualna prędkość samochodu: " + predkosc + " km/h");
        }
    }

    public void start(ActionEvent actionEvent) {
        silnik.uruchom();
        samochod.wlacz();
        aktualneObrotyField.setText(String.valueOf(silnik.getObroty()));
    }

    public void stop(ActionEvent actionEvent) {
        samochod.wylacz();
        aktualneObrotyField.setText(String.valueOf(0));
        aktualnaPredkoscField.setText(String.valueOf(0));
    }
}
