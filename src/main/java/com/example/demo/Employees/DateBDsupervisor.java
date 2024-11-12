package com.example.demo;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import static com.example.demo.Controller.AdminController.sInputGet1;
import static com.example.demo.Controller.AdminController.sInputGet2;
import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.DB.URL;
import static com.example.demo.Controller.SuperVisorController.*;


public class DateBDsupervisor {

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    public static String tableVariant;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackId;

    @FXML
    private AnchorPane LabelText;

    @FXML
    private Button NextId;

    @FXML
    private ScrollPane ScrollLabel;

    @FXML
    private TableColumn<StorageData, String> col1;

    @FXML
    private TableColumn<StorageData, String> col2;

    @FXML
    private TableColumn<StorageData, String> col3;

    @FXML
    private TableColumn<StorageData, String> col4;

    @FXML
    private TableColumn<StorageData, String> col5;

    @FXML
    private TableColumn<StorageData, String> col6;

    @FXML
    private TableColumn<StorageData, String> col7;

    @FXML
    private TableColumn<StorageData, String> col8;

    @FXML
    private TableColumn<StorageData, String> col9;

    @FXML
    private TableView<StorageData> tableAll;

    @FXML
    void goBack(ActionEvent event) {

    }

    @FXML
    void goNext(ActionEvent event) {

    }


    @FXML
    void initialize() throws SQLException {


        if( tableVariant.equalsIgnoreCase("SelectUsers")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
                    preparedStatement.setString(1, sInputGet2);
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM users;");
                }

                sInputGet2 = null;
                sInputGet1 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String username = "username";
                String password = "password";
                String user_group = "user_group";
                String employee_id = "employee_id";
                col1.setText(id);
                col2.setText(username);
                col3.setText(password);
                col4.setText(user_group);
                col5.setText(employee_id);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(username), resultSet.getString(password), resultSet.getString(user_group), resultSet.getString(employee_id)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());
                col5.setCellValueFactory(cellData -> cellData.getValue().column5Property());

                tableAll.setItems(dataList);

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


        } else if (tableVariant.equalsIgnoreCase("SelectService")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM service WHERE name = ?");
                    preparedStatement.setString(1, sInputGet2);
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM service WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM service;");
                }

                sInputGet2 = null;
                sInputGet1 = null;


                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String name = "name";
                String price = "price";

                col1.setText(id);
                col2.setText(name);
                col3.setText(price);


                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(name), resultSet.getString(price)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());


                tableAll.setItems(dataList);

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


        } else if (tableVariant.equalsIgnoreCase("SelectReceipt")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE client_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM receipt;");
                }

                sInputGet2 = null;
                sInputGet1 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String client_id = "client_id";
                String establishment_id = "establishment_id";
                String booking_id = "booking_id";
                String price = "price";
                col1.setText(id);
                col2.setText(client_id);
                col3.setText(establishment_id);
                col4.setText(booking_id);
                col5.setText(price);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(client_id), resultSet.getString(establishment_id),
                            resultSet.getString(booking_id), resultSet.getString(price)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());
                col5.setCellValueFactory(cellData -> cellData.getValue().column5Property());

                tableAll.setItems(dataList);

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
        }

        else if (tableVariant.equalsIgnoreCase("Select_establishment_employee")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM establishment_employee WHERE establishment_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM establishment_employee WHERE employee_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM establishment_employee;");
                }

                sInputGet2 = null;
                sInputGet1 = null;


                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String establishment_id = "establishment_id";
                String employee_id = "employee_id";

                col1.setText(establishment_id);
                col2.setText(employee_id);


                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(establishment_id), resultSet.getString(employee_id)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());


                tableAll.setItems(dataList);

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
        }

        else if (tableVariant.equalsIgnoreCase("Select_establishment")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM establishment WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM establishment WHERE phone_number = ?");
                    preparedStatement.setString(1, sInputGet1);
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM establishment;");
                }

                sInputGet2 = null;
                sInputGet1 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String establishment_type = "establishment_type";
                String address_with_postal_code = "address_with_postal_code";
                String phone_number = "phone_number";
                String number_of_employees = "number_of_employees";

                col1.setText(id);
                col2.setText(establishment_type);
                col3.setText(address_with_postal_code);
                col4.setText(phone_number);
                col5.setText(number_of_employees);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(establishment_type),
                            resultSet.getString(address_with_postal_code), resultSet.getString(phone_number), resultSet.getString(number_of_employees)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());
                col5.setCellValueFactory(cellData -> cellData.getValue().column5Property());

                tableAll.setItems(dataList);

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
        }

        else if (tableVariant.equalsIgnoreCase("Select_employee")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE full_name = ?");
                    preparedStatement.setString(1, sInputGet1);
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet3 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE position = ?");
                    preparedStatement.setString(1, sInputGet3);
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM employee;");
                }

                sInputGet2 = null;
                sInputGet1 = null;
                sInputGet3 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String full_name = "full_name";
                String position = "position";
                String contact_information = "contact_information";
                String experience = "experience";
                String salary = "salary";
                String brief_information = "brief_information";
                String age = "age";
                String rating = "rating";

                col1.setText(id);
                col2.setText(full_name);
                col3.setText(position);
                col4.setText(contact_information);
                col5.setText(experience);
                col6.setText(salary);
                col7.setText(brief_information);
                col8.setText(age);
                col9.setText(rating);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(full_name), resultSet.getString(position),
                            resultSet.getString(contact_information), resultSet.getString(experience), resultSet.getString(salary), resultSet.getString(brief_information)
                            , resultSet.getString(age), resultSet.getString(rating)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());
                col5.setCellValueFactory(cellData -> cellData.getValue().column5Property());
                col6.setCellValueFactory(cellData -> cellData.getValue().column6Property());
                col7.setCellValueFactory(cellData -> cellData.getValue().column7Property());
                col8.setCellValueFactory(cellData -> cellData.getValue().column8Property());
                col9.setCellValueFactory(cellData -> cellData.getValue().column9Property());

                tableAll.setItems(dataList);

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
        } else if (tableVariant.equalsIgnoreCase("Select_client_bonus")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM client_bonus WHERE client_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM client_bonus WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM client_bonus;");
                }

                sInputGet2 = null;
                sInputGet1 = null;


                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String client_id = "client_id";
                String bonus = "bonus";

                col1.setText(id);
                col2.setText(client_id);
                col3.setText(bonus);


                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(client_id), resultSet.getString(bonus)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());


                tableAll.setItems(dataList);

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
        }

        else if (tableVariant.equalsIgnoreCase("Select_booking")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE booking_date = ?");
                    preparedStatement.setDate(1, Date.valueOf(sInputGet2));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE master_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet3 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE client_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet3));
                    resultSet = preparedStatement.executeQuery();
                }  else if (sInputGet4 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet4));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM booking;");
                }

                sInputGet2 = null;
                sInputGet1 = null;
                sInputGet3 = null;
                sInputGet4 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String client_id = "client_id";
                String service_id = "service_id";
                String booking_date = "booking_date";
                String master_rating = "master_rating";
                String master_id = "master_id";


                col1.setText(id);
                col2.setText(client_id);
                col3.setText(service_id);
                col4.setText(booking_date);
                col5.setText(master_rating);
                col6.setText(master_id);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(client_id), resultSet.getString(service_id),
                            resultSet.getString(booking_date), resultSet.getString(master_rating), resultSet.getString(master_id)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());
                col5.setCellValueFactory(cellData -> cellData.getValue().column5Property());
                col6.setCellValueFactory(cellData -> cellData.getValue().column6Property());

                tableAll.setItems(dataList);

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
        } else if (tableVariant.equalsIgnoreCase("Select_client")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;
                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE contact_information = ?");
                    preparedStatement.setString(1, sInputGet2);
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE full_name = ?");
                    preparedStatement.setString(1, sInputGet1);
                    resultSet = preparedStatement.executeQuery();
                } else if (sInputGet3 != null) {
                    preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet3));
                    resultSet = preparedStatement.executeQuery();
                }
                else {
                    resultSet = statement.executeQuery("SELECT * FROM client;");
                }

                sInputGet2 = null;
                sInputGet1 = null;
                sInputGet3 = null;

                ObservableList<StorageData> dataList = FXCollections.observableArrayList();

                String id = "id";
                String full_name = "full_name";
                String contact_information = "contact_information";
                String client_status = "client_status";

                col1.setText(id);
                col2.setText(full_name);
                col3.setText(contact_information);
                col4.setText(client_status);

                while (resultSet.next()) {
                    dataList.add(new StorageData(resultSet.getString(id), resultSet.getString(full_name), resultSet.getString(contact_information),
                            resultSet.getString(client_status)));
                }
                col1.setCellValueFactory(cellData -> cellData.getValue().column1Property());
                col2.setCellValueFactory(cellData -> cellData.getValue().column2Property());
                col3.setCellValueFactory(cellData -> cellData.getValue().column3Property());
                col4.setCellValueFactory(cellData -> cellData.getValue().column4Property());

                tableAll.setItems(dataList);

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

        }



        //УДАЛЕНИЕ ЖЕСТКОЕ
        if( tableVariant.equalsIgnoreCase("DELETEUsers")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
                    preparedStatement.setString(1, sInputGet2);
                    preparedStatement.executeUpdate();
                    col1.setText("Удалено");
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    preparedStatement.executeUpdate();
                    col1.setText("Удалено");
                }

                sInputGet1 = null;
                sInputGet2 = null;

                System.out.println("юсер удален");

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


        } else if (tableVariant.equalsIgnoreCase("DELETEService")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM service WHERE name = ?");
                    preparedStatement.setString(1, sInputGet2);
                    preparedStatement.executeUpdate();
                    col1.setText("Удалено");
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM service WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    preparedStatement.executeUpdate();
                    col1.setText("Удалено");
                }
                sInputGet1 = null;
                sInputGet2 = null;

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


        } else if (tableVariant.equalsIgnoreCase("DELETEReceipt")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM receipt WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;
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
        }

        else if (tableVariant.equalsIgnoreCase("DELETE_establishment_employee")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM establishment_employee WHERE employee_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;
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
        }

        else if (tableVariant.equalsIgnoreCase("DELETE_establishment")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM establishment WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM establishment WHERE address = ?");
                    preparedStatement.setString(1, sInputGet1);
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;
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
        }

        else if (tableVariant.equalsIgnoreCase("DELETE_employee")){
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE full_name = ?");
                    preparedStatement.setString(1, sInputGet1);
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;

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
        } else if (tableVariant.equalsIgnoreCase("DELETE_client_bonus")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM client_bonus WHERE client_id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM client_bonus WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;


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
        }

        else if (tableVariant.equalsIgnoreCase("DELETE_booking")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM booking WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;


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
        } else if (tableVariant.equalsIgnoreCase("DELETE_client")) {
            try {
                connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                if(sInputGet2 != null){
                    preparedStatement = connection.prepareStatement("DELETE FROM client WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                    preparedStatement.executeUpdate();
                } else if (sInputGet1 != null) {
                    preparedStatement = connection.prepareStatement("DELETE FROM client WHERE full_name = ?");
                    preparedStatement.setString(1, sInputGet1);
                    preparedStatement.executeUpdate();
                }
                sInputGet1 = null;
                sInputGet2 = null;
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

        }


    }

}

