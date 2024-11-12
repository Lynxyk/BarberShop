package com.example.demo.Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.DB.URL;

public class AddUsersController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ToggleGroup GroupRole;

    @FXML
    private URL location;

    @FXML
    private Button addId;

    @FXML
    private TextField employeeid;

    @FXML
    private Button rollBackId;

    @FXML
    private Button commitId;

    @FXML
    private TextField id;

    @FXML
    private TextField login;

    @FXML
    private Label success;

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    @FXML
    private TextField password;

    @FXML
    private TextField userGroup;

    private Savepoint savepoint;

    DB db = new DB();

    @FXML
    void AddGo(ActionEvent event) {


        RadioButton selectedRadio = (RadioButton) GroupRole.getSelectedToggle();
        String group = selectedRadio.getText();

        if (selectedRadio != null){

            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                connection.setAutoCommit(false);
                statement = connection.createStatement();


                savepoint = connection.setSavepoint();
                statement.execute("INSERT INTO users (username, password, user_group, employee_id) " +
                        "VALUES (" + "'" + login.getText() +  "', '" + db.convertToSHA256(password.getText())  + "', '" + userGroup.getText() + "', " + employeeid.getText() +   ");"  );


                statement.execute("CREATE ROLE \"" + login.getText() + "\" WITH\n" +
                        "\tLOGIN\n" +
                        "\tNOSUPERUSER\n" +
                        "\tNOCREATEDB\n" +
                        "\tNOCREATEROLE\n" +
                        "\tINHERIT\n" +
                        "\tNOREPLICATION\n" +
                        "\tCONNECTION LIMIT -1\n" +
                        "\tPASSWORD '" + password.getText() + "';");

                statement.execute("GRANT \"" + group + "\" TO \"" + login.getText() + "\" WITH ADMIN OPTION" + ";");



            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
         else {
            success.setText("Выберите роль");
        }



    }

    @FXML
    void commitGo(ActionEvent event) {
        try {
            connection.commit();
            success.setText("Пользователь добавлен");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void rollbackGo(ActionEvent event) {
        try {
            connection.rollback(savepoint);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void initialize() {

    }

}
