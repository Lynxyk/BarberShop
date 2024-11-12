package com.example.demo.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RaportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label outputLabel;

    DB db = new DB();

    @FXML
    void bestEmployee() {


        outputLabel.setText(db.raport("SELECT * FROM bestEmployee"));

    }

    @FXML
    void ind_profit(ActionEvent event) {

        outputLabel.setText(db.raport("SELECT * FROM ind_profit"));
    }

    @FXML
    void noTopService(ActionEvent event) {
        outputLabel.setText(db.raport("SELECT * FROM noTopService"));
    }

    @FXML
    void topService(ActionEvent event) {
        outputLabel.setText(db.raport("SELECT * FROM topService"));
    }

    @FXML
    void totalProfit(ActionEvent event) {
        outputLabel.setText(db.raportTotalProfit("SELECT * FROM totalProfit"));
    }

    @FXML
    void initialize() {

    }

}
