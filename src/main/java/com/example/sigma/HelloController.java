package com.example.sigma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HelloController {

    @FXML
    private Pane Pane1;

    @FXML
    void CustomerX(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sigma/AddCustomer.fxml"));
            Pane addCustomerPane = loader.load();
            Pane1.getChildren().clear();
            Pane1.getChildren().add(addCustomerPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void CustomerY(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sigma/FlightsInf.fxml"));
            Pane FlightsPane = loader.load();
            Pane1.getChildren().clear();
            Pane1.getChildren().add(FlightsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
