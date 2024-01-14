package com.example.sigma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlightsController {
    @FXML
    private TableColumn<String, FlightData> LastName;
    @FXML
    private TableColumn <String, FlightData>Nic;
    @FXML
    private TableColumn <String, FlightData>Passport;
    @FXML
    private TableColumn <String, FlightData>Address;
    @FXML
    private TableColumn<String, FlightData> Dob;
    @FXML
    private TableColumn <String, FlightData>Gender;
    @FXML
    private TableColumn <String, FlightData>From;
    @FXML
    private TableColumn <String, FlightData>To;
    @FXML
    private TableColumn <String, FlightData>DepartureDate;
    @FXML
    private TableColumn <String, FlightData>ArriveDate;
    @FXML
    private TableColumn <String, FlightData>Time;
    @FXML
    private TableColumn <String, FlightData>PhoneNumber;
    @FXML
    private TableColumn <String, FlightData>FirstName;
    @FXML
    private TableColumn <String, FlightData> id;
    @FXML
    private Pane FlightsPane;

    @FXML
    private TableView<FlightData> tableView;

    public class FlightData {
        private String id;
        private String firstName;
        private String lastName;
        private String nicNo;
        private String passport;
        private String address;
        private String dateOfBirth;
        private String gender;
        private String flightFrom;
        private String flightTo;
        private String departureDate;
        private String arriveDate;
        private String departureTime;
        private String phoneNumber;

        public void setId(String id) {
            this.id = id;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public void setNicNo(String nicNo) {
            this.nicNo = nicNo;
        }

        public void setPassport(String passport) {
            this.passport = passport;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setFlightFrom(String flightFrom) {
            this.flightFrom = flightFrom;
        }

        public void setFlightTo(String flightTo) {
            this.flightTo = flightTo;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public void setArriveDate(String arriveDate) {
            this.arriveDate = arriveDate;
        }

        public void setDepartureTime(String departureTime) {
            this.departureTime = departureTime;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getNicNo() {
            return nicNo;
        }

        public String getPassport() {
            return passport;
        }

        public String getAddress() {
            return address;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public String getFlightFrom() {
            return flightFrom;
        }

        public String getFlightTo() {
            return flightTo;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public String getArriveDate() {
            return arriveDate;
        }

        public String getDepartureTime() {
            return departureTime;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
    }


    public void initialize() throws SQLException {
        System.out.println("initializing...");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        Nic.setCellValueFactory(new PropertyValueFactory<>("nicNo"));
        Passport.setCellValueFactory(new PropertyValueFactory<>("passport"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        Dob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        From.setCellValueFactory(new PropertyValueFactory<>("flightFrom"));
        To.setCellValueFactory(new PropertyValueFactory<>("flightTo"));
        DepartureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        ArriveDate.setCellValueFactory(new PropertyValueFactory<>("arriveDate"));
        Time.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        fillTables();
    }

    @FXML
    void backButtonAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sigma/AirlineMain.fxml"));
            Pane Pane1 = loader.load();
            FlightsPane.getChildren().clear();
            FlightsPane.getChildren().add(Pane1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void fillTables(){
        final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
        final String DATABASE_USER = "postgres";
        final String DATABASE_PASSWORD = "20061616Ali";
        try (Connection con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customer")) {

            tableView.getItems().clear();

            while (rs.next()) {
                FlightData flight = new FlightData();
                flight.setId(rs.getString("id"));
                flight.setFirstName(rs.getString("FirstName"));
                flight.setLastName(rs.getString("LastName"));
                flight.setNicNo(rs.getString("NicNO"));
                flight.setPassport(rs.getString("passport"));
                flight.setAddress(rs.getString("address"));
                flight.setDateOfBirth(rs.getString("dateOfBirth"));
                flight.setGender(rs.getString("Gender"));
                flight.setFlightFrom(rs.getString("flight_from"));
                flight.setFlightTo(rs.getString("flight_to"));
                flight.setDepartureDate(rs.getString("flight_date"));
                flight.setArriveDate(rs.getString("flight_arrive"));
                flight.setDepartureTime(rs.getString("time_departure"));
                flight.setPhoneNumber(rs.getString("Contact"));

                tableView.getItems().add(flight);
            }


        }catch (SQLException ex) {
            Logger lgr = Logger.getLogger(AddCustomerController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}
