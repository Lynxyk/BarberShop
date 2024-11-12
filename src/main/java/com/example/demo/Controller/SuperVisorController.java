package com.example.demo.Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.DateBDsupervisor.tableVariant;

public class SuperVisorController {

    @FXML
    private Button discId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TextField EstablishmentAddress;

    @FXML
    private TextField EstablishmentId;

    @FXML
    private TextField EstablishmentNumberEmpl;

    @FXML
    private TextField EstablishmentPhone;

    @FXML
    private TextField EstablishmentPostalCode;

    @FXML
    private TextField EstablishmentType;

    @FXML
    private TextField clientFIO;

    @FXML
    private TextField clientId;

    @FXML
    private TextField clientPyhone;

    @FXML
    private TextField clientStatus;

    @FXML
    private TextField employeeAge;

    @FXML
    private TextField employeeExperence;

    @FXML
    private TextField employeeFio;

    @FXML
    private TextField employeeId;

    @FXML
    private TextField employeeInfo;

    @FXML
    private TextField employeePhone;

    @FXML
    private TextField employeePosition;

    @FXML
    private TextField employeeRating;

    @FXML
    private TextField employeeSalary;

    @FXML
    private Button findBonusid;

    @FXML
    private Button findBookingid;

    @FXML
    private Button findClientid;

    @FXML
    private Button findEmployeeId;

    @FXML
    private Button findEstablishmentId;

    @FXML
    private Button findReceiptId;

    @FXML
    private Button findServiceid;

    @FXML
    private TextField receiptClientId;

    @FXML
    private TextField receiptId;

    @FXML
    private TextField receiptIdBooking;

    @FXML
    private TextField receiptIdEstablishment;

    @FXML
    private TextField receiptPrice;

    @FXML
    private TextField serviceId;

    @FXML
    private TextField serviceName;

    @FXML
    private TextField servicePrice;

    public static String sInputGet1;
    public static String sInputGet2;
    public static String sInputGet3;
    public static String sInputGet4;

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    TransitionButton transitionButton = new TransitionButton();


    @FXML
    void editEmployee(ActionEvent event) {
        if(employeeId.getText().length() >= 1 && employeeFio.getText().length() < 1 && employeePosition.getText().length() < 1 && employeePhone.getText().length() < 1 &&
                employeeExperence.getText().length() < 1 && employeeSalary.getText().length() < 1 && employeeInfo.getText().length() < 1 && employeeAge.getText().length() < 1 &&
                employeeRating.getText().length() < 1){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;


                preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
                preparedStatement.setInt(1, Integer.parseInt(employeeId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    employeeFio.setText(resultSet.getString("full_name"));
                    employeePosition.setText(resultSet.getString("position"));
                    employeePhone.setText(resultSet.getString("contact_information"));
                    employeeExperence.setText(resultSet.getString("experience"));
                    employeeSalary.setText(resultSet.getString("salary"));
                    employeeInfo.setText(resultSet.getString("brief_information"));
                    employeeAge.setText(resultSet.getString("age"));
                    employeeRating.setText(resultSet.getString("rating"));
                }


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
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                preparedStatement = connection.prepareStatement("UPDATE employee SET full_name = ?, position = ?, contact_information = ?, experience = ?, salary = ?, " +
                        "brief_information = ?, age = ?, rating = ? WHERE id = ?");

                preparedStatement.setString(1, employeeFio.getText());
                preparedStatement.setString(2, employeePosition.getText());
                preparedStatement.setString(3, employeePhone.getText());
                preparedStatement.setInt(4, Integer.parseInt(employeeExperence.getText()));
                preparedStatement.setDouble(5, Double.parseDouble(employeeSalary.getText()));
                preparedStatement.setString(6, employeeInfo.getText());
                preparedStatement.setInt(7, Integer.parseInt(employeeAge.getText()));
                preparedStatement.setDouble(8, Double.parseDouble(employeeRating.getText()));
                preparedStatement.setInt(9, Integer.parseInt(employeeId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();

                    employeeFio.setText("");
                    employeePosition.setText("");
                    employeePhone.setText("");
                    employeeExperence.setText("");
                    employeeSalary.setText("");
                    employeeInfo.setText("");
                    employeeAge.setText("");
                    employeeRating.setText("");
                    employeeId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @FXML
    void editEstablishment(ActionEvent event) {

        if(EstablishmentId.getText().length() >= 1 && EstablishmentType.getText().length() < 1 && EstablishmentAddress.getText().length() < 1 &&
                EstablishmentPostalCode.getText().length() < 1 && EstablishmentPhone.getText().length() < 1 && EstablishmentNumberEmpl.getText().length() < 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;

                preparedStatement = connection.prepareStatement("SELECT * FROM establishment WHERE id = ?");

                preparedStatement.setInt(1, Integer.parseInt(EstablishmentId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    String s = resultSet.getString("address_with_postal_code");
                    System.out.println(s);

                    // Определяем регулярное выражение для поиска текста внутри скобок
                    Pattern pattern = Pattern.compile("\"([^\"]*)\",(\\d+)");
                    Matcher matcher = pattern.matcher(s);

                    String address = "";
                    String index = "";

                    if (matcher.find()) {
                        address = matcher.group(1);
                        index = matcher.group(2);
                    }



                    EstablishmentAddress.setText(address);
                    EstablishmentPostalCode.setText(index);


                    EstablishmentType.setText(resultSet.getString("establishment_type"));

                    EstablishmentPhone.setText(resultSet.getString("phone_number"));
                    EstablishmentNumberEmpl.setText(resultSet.getString("number_of_employees"));
                }


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
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                preparedStatement = connection.prepareStatement("UPDATE establishment SET establishment_type = ? , " +
                        "address_with_postal_code = ROW(?, ?), phone_number = ?, number_of_employees = ? WHERE id = ?");
                preparedStatement.setString(1, EstablishmentType.getText());
                preparedStatement.setString(2, EstablishmentAddress.getText());
                preparedStatement.setString(3, EstablishmentPostalCode.getText());
                preparedStatement.setString(4, EstablishmentPhone.getText());
                preparedStatement.setInt(5, Integer.parseInt(EstablishmentNumberEmpl.getText()));
                preparedStatement.setInt(6, Integer.parseInt(EstablishmentId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    EstablishmentType.setText("");
                    EstablishmentAddress.setText("");
                    EstablishmentPostalCode.setText("");
                    EstablishmentPhone.setText("");
                    EstablishmentNumberEmpl.setText("");
                    EstablishmentId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @FXML
    void findBonus(ActionEvent event) {

        System.out.println(BonusClientId.getText());

        if(BonusClientId.getText().length() >= 1 ){
            sInputGet2 = BonusClientId.getText();
            tableVariant = "Select_client_bonus";
        } else if (BonusId.getText().length() >= 1) {
            sInputGet1 = BonusId.getText();
            tableVariant = "Select_client_bonus";
        } else {
            tableVariant = "Select_client_bonus";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");



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


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");



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


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");

    }


    @FXML
    void findEmployee(ActionEvent event) {
        if(employeeId.getText().length() >= 1 ){
            sInputGet2 = employeeId.getText();
            tableVariant = "Select_employee";
        } else if (employeeFio.getText().length() >= 1) {
            sInputGet1 = employeeFio.getText();
            tableVariant = "Select_employee";
        } else if (employeePosition.getText().length() >= 1) {
            sInputGet3 = employeePosition.getText();
            tableVariant = "Select_employee";
        }
        else {
            tableVariant = "Select_employee";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");
    }

    @FXML
    void findEstablishment(ActionEvent event) {
        if(EstablishmentId.getText().length() >= 1 ){
            sInputGet2 = EstablishmentId.getText();
            tableVariant = "Select_establishment";
        } else if (EstablishmentPhone.getText().length() >= 1) {
            sInputGet1 = EstablishmentPhone.getText();
            tableVariant = "Select_establishment";
        } else {
            tableVariant = "Select_establishment";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");

    }

    @FXML
    void findReceipt(ActionEvent event) {

        if(receiptClientId.getText().length() >= 1 ){
            sInputGet2 = receiptClientId.getText();
            tableVariant = "SelectReceipt";
        } else if (receiptId.getText().length() >= 1) {
            sInputGet1 = receiptId.getText();
            tableVariant = "SelectReceipt";
        } else {
            tableVariant = "SelectReceipt";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");

    }

    @FXML
    void findService(ActionEvent event) {

        if(serviceName.getText().length() >= 1 ){
            sInputGet2 = serviceName.getText();
            tableVariant = "SelectService";
        } else if (serviceId.getText().length() >= 1) {
            sInputGet1 = serviceId.getText();
            tableVariant = "SelectService";
        } else {
            tableVariant = "SelectService";
        }


        transitionButton.transitiionNotClose(findBonusid, "DateBDsupervisor.fxml", "date");

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
