package com.example.sigma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCustomerController {
    @FXML
    private Pane addCutomerPane;
    @FXML
    private ChoiceBox<String> departureDateChoiceBox;
    @FXML
    private ChoiceBox<String> arriveDateChoiceBox;
    @FXML
    private ChoiceBox<String> flightFromChoiceBox;
    @FXML
    private ChoiceBox<String> flightToChoiceBox;
    @FXML
    private ChoiceBox<String> timeDepartureChoiceBox;
    @FXML
    private TextArea PassportID;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField CusID;
    @FXML
    private TextField Contact;
    @FXML
    private TextField dateOfBirthPicker;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;


    @FXML
    private TextField nicNumberField;


    @FXML
    private TextField addressArea;


    private Connection con;
    @FXML
    private Button confirmButton;

    @FXML
    private Button backButton;

    public void initialize() throws SQLException {
        fillDepartureDateChoiceBox();
    }
    @FXML
    void backButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sigma/AirlineMain.fxml"));
            Pane Pane1 = loader.load();
            addCutomerPane.getChildren().clear();
            addCutomerPane.getChildren().add(Pane1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void fillDepartureDateChoiceBox() throws SQLException {
        ObservableList<String> departureDates = FXCollections.observableArrayList();
        ObservableList<String> arrivalDates = FXCollections.observableArrayList();
        ObservableList<String> flightFrom = FXCollections.observableArrayList();
        ObservableList<String> flightTo = FXCollections.observableArrayList();
        ObservableList<String> timeDeparture = FXCollections.observableArrayList();
        final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
        final String DATABASE_USER = "postgres";
        final String DATABASE_PASSWORD = "20061616Ali";

        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT flight_date FROM Flights")) {

            while (rs.next()) {
                String date = rs.getString("flight_date");
                departureDates.add(date);
            }
            ResultSet rsArrival = stmt.executeQuery("SELECT DISTINCT flight_arrive FROM Flights");
            while (rsArrival.next()) {
                String arrival = rsArrival.getString("flight_arrive");
                arrivalDates.add(arrival);
            }
            ResultSet rsFlightFrom = stmt.executeQuery("SELECT DISTINCT flight_from FROM Flights");
            while (rsFlightFrom.next()) {
                String FlightFrom = rsFlightFrom.getString("flight_from");
                flightFrom.add(FlightFrom);
            }
            ResultSet rsFlightTo = stmt.executeQuery("SELECT DISTINCT flight_to FROM Flights");
            while (rsFlightTo.next()) {
                String FlightTo = rsFlightTo.getString("flight_to");
                flightTo.add(FlightTo);
            }
            ResultSet rsTimeDeparture = stmt.executeQuery("SELECT DISTINCT time_departure FROM Flights");
            while (rsTimeDeparture.next()) {
                String TimeDeparture = rsTimeDeparture.getString("time_departure");
                timeDeparture.add(TimeDeparture);
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(AddCustomerController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        arriveDateChoiceBox.setItems(arrivalDates);
        departureDateChoiceBox.setItems(departureDates);
        flightToChoiceBox.setItems(flightTo);
        flightFromChoiceBox.setItems(flightFrom);
        timeDepartureChoiceBox.setItems(timeDeparture);
    }

    public void getData(ActionEvent actionEvent){
        System.out.println(firstNameField.getText());
        System.out.println(lastNameField.getText());
        System.out.println(CusID.getText());
        System.out.println(PassportID.getText());
        System.out.println(nicNumberField.getText());
        System.out.println(dateOfBirthPicker.getText());
        System.out.println(maleRadio.getText());
        System.out.println(femaleRadio.getText());
        System.out.println(Contact.getText());
        System.out.println(addressArea.getText());
        String departureDate = departureDateChoiceBox.getValue();
        String arriveDate = arriveDateChoiceBox.getValue();
        String flightFrom = flightFromChoiceBox.getValue();
        String flightTo = flightToChoiceBox.getValue();
        String timeDeparture = timeDepartureChoiceBox.getValue();


        PostgresqlC.writeToDataBase(CusID.getText(),firstNameField.getText(),lastNameField.getText(),PassportID.getText(),nicNumberField.getText(),dateOfBirthPicker.getText()
        ,addressArea.getText(),maleRadio.getText(),femaleRadio.getText(),Contact.getText(), departureDate, arriveDate, flightFrom, flightTo, timeDeparture );
    }
    // Add event handlers and logic for your UI components

    // For example, you can add a method for the confirm button click event
    @FXML
    private void onConfirmButtonClicked() {
        // Handle confirm button click
    }
}
