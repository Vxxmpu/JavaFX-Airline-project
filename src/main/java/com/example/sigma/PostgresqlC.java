package com.example.sigma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresqlC {
    public static void writeToDataBase(String id, String firstNameField, String lastNameField, String passportIDText, String NicNumber, String dateOfBirth, String Address, String maleRadioText, String femaleRadioText, String contactText
    , String departureDate, String arriveDate, String flightFrom, String flightTo, String timeDeparture) {
        final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
        final String DATABASE_USER = "postgres";
        final String DATABASE_PASSWORD = "20061616Ali";

        String firstName = firstNameField;
        String lastName = lastNameField;
        String CID = id;
        String passport = passportIDText;
        String nic = NicNumber;
        String dob = dateOfBirth;
        String address = Address;
        String male = maleRadioText;
        String female = femaleRadioText;
        String contact = contactText;
        String departure = departureDate;
        String arrive = arriveDate;
        String from = flightFrom;
        String to = flightTo;
        String time = timeDeparture;
        String query = "INSERT INTO customer(\"FirstName\", \"LastName\",\"id\",\"passport\",\"NicNO\",\"dateOfBirth\",\"address\",\"Gender\",\"Contact\",\"flight_from\",\"flight_to\",\"flight_date\",\"flight_arrive\",\"time_departure\") VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?,? ,?, ?, ? ,?)";


        try(Connection con = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD)) {
            PreparedStatement pst = con.prepareStatement(query);

            pst.setString(1,firstName);
            pst.setString(2,lastName);
            pst.setString(3,CID);
            pst.setString(4,passport);
            pst.setString(5,nic);
            pst.setString(6, String.valueOf(dob));
            pst.setString(7,address);
            pst.setString(8,male);
            pst.setString(9, contact);
            pst.setString(10,departure);
            pst.setString(11,arrive);
            pst.setString(12,from);
            pst.setString(13,to);
            pst.setString(14,time);
            pst.executeUpdate();
            System.out.println("Succesfully created");

        }catch (SQLException ex){
            Logger lgr  = Logger.getLogger(PostgresqlC.class.getName());

            lgr.log(Level.SEVERE, ex.getMessage(),ex);
        };
    }
}
