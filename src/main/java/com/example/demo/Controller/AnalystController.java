package com.example.demo.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.demo.DateBDanalyst.tableVariant;

public class AnalystController {


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
    private TextField eeIdEmployee;

    @FXML
    private TextField eeIdEstablishment;

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
    private Button findEEid;

    @FXML
    private Button discId;

    @FXML
    private Button findEmployeeId;

    @FXML
    private Button findEstablishmentId;

    @FXML
    private Button findReceiptId;

    @FXML
    private Button findServiceid;

    @FXML
    private Button findUsersId;

    @FXML
    private Button raportId;

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

    @FXML
    private TextField usersEmployeeId;

    @FXML
    private TextField usersGroup;

    @FXML
    private TextField usersId;

    @FXML
    private TextField usersLogin;

    @FXML
    private TextField usersPassword;

    public static String sInputGet1;
    public static String sInputGet2;
    public static String sInputGet3;
    public static String sInputGet4;

    TransitionButton transitionButton = new TransitionButton();

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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");



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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");



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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");

    }

    @FXML
    void findEE(ActionEvent event) {

        if(eeIdEstablishment.getText().length() >= 1 ){
            sInputGet2 = eeIdEstablishment.getText();
            tableVariant = "Select_establishment_employee";
        } else if (eeIdEmployee.getText().length() >= 1) {
            sInputGet1 = eeIdEmployee.getText();
            tableVariant = "Select_establishment_employee";
        } else {
            tableVariant = "Select_establishment_employee";
        }


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");
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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");

    }

    @FXML
    void findUsers(ActionEvent event) {

        if(usersLogin.getText().length() >= 1 ){
            sInputGet2 = usersLogin.getText();
            tableVariant = "SelectUsers";
        } else if (usersId.getText().length() >= 1) {
            sInputGet1 = usersId.getText();
            tableVariant = "SelectUsers";
        } else {
            tableVariant = "SelectUsers";
        }


        transitionButton.transitiionNotClose(findUsersId, "DateBDBanalyst.fxml", "date");



    }

    @FXML
    void goRaport() {
        TransitionButton transitionButton = new TransitionButton();
        transitionButton.transitiionNotClose(raportId, "Raport.fxml", "Raport");

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
