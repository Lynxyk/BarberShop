package com.example.demo.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.demo.DB;
import com.example.demo.SMTP.EmailNotificationSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonNext;

    @FXML
    private TextField loginA;

    @FXML
    private PasswordField passWordA;

    DB db = new DB();
    TransitionButton transitionButton = new TransitionButton();

    @FXML
    void goNextWindow(ActionEvent event) {

        DB.LOGIN = loginA.getText();
        DB.PASSWORD = passWordA.getText();

        EmailNotificationSender emailNotificationSender = new EmailNotificationSender();

        try {
            String role = db.checkRole();
            if(role.equalsIgnoreCase("Admin")){
                transitionButton.transitiionWithoutEvent(buttonNext, "Admin.fxml", "Admin");
                emailNotificationSender.sendEmailNotification("luhmanov32167@inbox.ru");
            } else if (role.equalsIgnoreCase("Staff")) {
                transitionButton.transitiionWithoutEvent(buttonNext, "Staff.fxml", "Staff");
                emailNotificationSender.sendEmailNotification("luhmanov32167@inbox.ru");
            } else if (role.equalsIgnoreCase("Manager")) {
                transitionButton.transitiionWithoutEvent(buttonNext, "Manager.fxml", "Manager");
                emailNotificationSender.sendEmailNotification("luhmanov32167@inbox.ru");
            } else if (role.equalsIgnoreCase("Analyst")) {
                transitionButton.transitiionWithoutEvent(buttonNext, "Analyst.fxml", "Analyst");
                emailNotificationSender.sendEmailNotification("luhmanov32167@inbox.ru");
            } else if (role.equalsIgnoreCase("Supervisor")) {
                transitionButton.transitiionWithoutEvent(buttonNext, "Supervisor.fxml", "Supervisor");
                emailNotificationSender.sendEmailNotification("luhmanov32167@inbox.ru");
            }

        } catch (Exception e) {
            loginA.setText("Неверный логин или пароль");
            passWordA.setText("Неверный логин или пароль");
        }


    }

    @FXML
    void initialize() {

    }

}
