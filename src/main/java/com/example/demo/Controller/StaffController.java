package com.example.demo.Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.DateBDmanager.tableVariant;

public class StaffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField BonusClientId;

    @FXML
    private Button discId;

    @FXML
    private TextField BonusId;

    @FXML
    private TextField BonusSize;

    @FXML
    private TextField BookingDate;

    @FXML
    private TextField BookingId;

    @FXML
    private TextField BookingServiceId;

    @FXML
    private TextField BookingidClient;

    @FXML
    private TextField BookingidEmployee;

    @FXML
    private TextField BookingratingEmployee;

    @FXML
    private TextField clientFIO;

    @FXML
    private TextField clientId;

    @FXML
    private TextField clientPyhone;

    @FXML
    private TextField clientStatus;

    @FXML
    private Button findBonusid;

    @FXML
    private Button findBookingid;

    @FXML
    private Button findClientid;

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    public static String sInputGet1;
    public static String sInputGet2;
    public static String sInputGet3;
    public static String sInputGet4;

    TransitionButton transitionButton = new TransitionButton();




    @FXML
    void findBooking(ActionEvent event) {
        if(BookingDate.getText().length() >= 1 ){
            sInputGet2 = BookingDate.getText();
            tableVariant = "Select_booking";
        } else if (BookingidEmployee.getText().length() >= 1) {
            sInputGet1 = BookingidEmployee.getText();
            tableVariant = "Select_booking";
        } else if (BookingidClient.getText().length() >= 1) {
            sInputGet3 = BookingidClient.getText();
            tableVariant = "Select_booking";
        } else if (BookingId.getText().length() >= 1) {
            sInputGet4 = BookingId.getText();
            tableVariant = "Select_booking";
        }
        else {
            tableVariant = "Select_booking";
        }
        transitionButton.transitiionNotClose(findBonusid, "DateBDmanager.fxml", "date");

    }

    @FXML
    void findClient(ActionEvent event) {
        if(clientPyhone.getText().length() >= 1 ){
            sInputGet2 = clientPyhone.getText();
            tableVariant = "Select_client";
        } else if (clientFIO.getText().length() >= 1) {
            sInputGet1 = clientFIO.getText();
            tableVariant = "Select_client";
        } else if (clientId.getText().length() >= 1) {
            sInputGet3 = clientId.getText();
            tableVariant = "Select_client";
        }
        else {
            tableVariant = "Select_client";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDmanager.fxml", "date");

    }

    @FXML
    void insBooking(ActionEvent event) {
        if( BookingServiceId.getText().length() >=1 && BookingDate.getText().length() >=1 && BookingidEmployee.getText().length() >=1 &&
                BookingratingEmployee.getText().length() >=1 && BookingidClient.getText().length() >=1 ) {
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO booking (client_id, service_id, booking_date, master_rating, master_id) VALUES (?, ?, ?, ?, ?) ");

                preparedStatement.setInt(1, Integer.parseInt(BookingidClient.getText()));
                preparedStatement.setInt(2, Integer.parseInt(BookingServiceId.getText()));
                preparedStatement.setDate(3, Date.valueOf(BookingDate.getText()));
                preparedStatement.setDouble(4, Double.parseDouble(BookingratingEmployee.getText()));
                preparedStatement.setInt(5, Integer.parseInt(BookingidEmployee.getText()));
                preparedStatement.executeUpdate();

                BookingidClient.setText("");
                BookingId.setText("");
                BookingratingEmployee.setText("");
                BookingidEmployee.setText("");
                BookingDate.setText("");
                BookingServiceId.setText("");



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            BookingId.setText("Введите все значения");
        }


    }

    @FXML
    void insClient(ActionEvent event) {
        if(  clientFIO.getText().length() >= 1 && clientPyhone.getText().length() >= 1 && clientStatus.getText().length() >= 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO client (full_name, contact_information, client_status) VALUES (?, ?, ?) ");

                preparedStatement.setString(1, clientFIO.getText());
                preparedStatement.setString(2, clientPyhone.getText());
                preparedStatement.setString(3, clientStatus.getText());
                preparedStatement.executeUpdate();

                clientId.setText("");
                clientFIO.setText("");
                clientPyhone.setText("");
                clientStatus.setText("");



            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            clientId.setText("Введите все значения");
        }

    }

    @FXML
    void intBonus(ActionEvent event) {
        if(BonusClientId.getText().length() >= 1 ){
            sInputGet2 = BonusClientId.getText();
            tableVariant = "Select_client_bonus";
        } else if (BonusId.getText().length() >= 1) {
            sInputGet1 = BonusId.getText();
            tableVariant = "Select_client_bonus";
        } else {
            tableVariant = "Select_client_bonus";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDmanager.fxml", "date");

    }

    @FXML
    void discGo(ActionEvent event) {

        TransitionButton transitionButton = new TransitionButton();
        transitionButton.transitiionWithoutEvent(discId, "hello-view.fxml", "BarberShop");

    }

    @FXML
    void initialize() {


    }

}
