package com.example.demo.Controller;

import java.sql.*;

import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.DateBDmanager.tableVariant;

public class ManagerController {

    TransitionButton transitionButton = new TransitionButton();

    @FXML
    private TextField BonusClientId;

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
    private Button discId;

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



    @FXML
    void DeleteBooking(ActionEvent event) {
        if(BookingId.getText().length() >= 1 ){
            sInputGet2 = BookingId.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteBookingById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            BookingId.setText("");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('booking_id_seq', (SELECT MAX(id) FROM booking));");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeleteClient(ActionEvent event) {
        if(clientId.getText().length() >= 1 ){
            sInputGet2 = clientId.getText();

        } else if (clientFIO.getText().length() >= 1) {
            sInputGet1 = clientFIO.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteClientById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("DELETE FROM client WHERE full_name = ?");
                preparedStatement.setString(1, sInputGet1);
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            clientId.setText("");
            clientFIO.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('client_id_seq', (SELECT MAX(id) FROM client))");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


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
    void findBonus(ActionEvent event) {
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
