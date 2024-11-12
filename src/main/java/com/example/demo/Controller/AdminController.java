package com.example.demo.Controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.AddressPostalCode;
import com.example.demo.DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import static com.example.demo.DB.LOGIN;
import static com.example.demo.DB.PASSWORD;
import static com.example.demo.Controller.DateBDController.tableVariant;

public class  AdminController {

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    TransitionButton transitionButton = new TransitionButton();

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button discId;

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
    private Button addUserId;

    @FXML
    private TextField clientFIO;

    @FXML
    private TextField clientId;

    @FXML
    private TextField clientPyhone;

    @FXML
    private TextField clientStatus;

    @FXML
    private Button editBonusid;

    @FXML
    private Button editBookingid;

    @FXML
    private Button editClientid;

    @FXML
    private Button editEEid;

    @FXML
    private Button editEmployeeid;

    @FXML
    private Button editEstablishmentid;

    @FXML
    private Button editReceiptid;

    @FXML
    private Button editServiceid;

    @FXML
    private Button editUsersid;

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


    @FXML
    void DeleteBonus(ActionEvent event) {
        if(BonusClientId.getText().length() >= 1 ){
            sInputGet2 = BonusClientId.getText();

        } else if (BonusId.getText().length() >= 1) {
            sInputGet1 = BonusId.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("DELETE FROM client_bonus WHERE client_id = ?");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("CALL DeleteClientBonusById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            BonusClientId.setText("");
            BonusId.setText("");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('client_bonus_id_seq', (SELECT MAX(id) FROM client_bonus));");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

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
    void DeleteEE(ActionEvent event) {

        if(eeIdEmployee.getText().length() >= 1 ){
            sInputGet2 = eeIdEmployee.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteEstablishmentEmployeeByEmployeeId(?)");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            eeIdEmployee.setText("");
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

    @FXML
    void DeleteEmployee(ActionEvent event) {

        if(employeeId.getText().length() >= 1 ){
            sInputGet2 = employeeId.getText();

        } else if (employeeFio.getText().length() >= 1) {
            sInputGet1 = employeeFio.getText();

        }

        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteEmployeeById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE full_name = ?");
                preparedStatement.setString(1, sInputGet1);
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            employeeId.setText("");
            employeeFio.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('employee_id_seq', (SELECT MAX(id) FROM employee))");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeleteEstablishment(ActionEvent event) {

        if(EstablishmentId.getText().length() >= 1 ){
            sInputGet2 = EstablishmentId.getText();

        } else if (EstablishmentAddress.getText().length() >= 1) {
            sInputGet1 = EstablishmentAddress.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteEstablishmentById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("DELETE FROM establishment WHERE address = ?");
                preparedStatement.setString(1, sInputGet1);
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            EstablishmentAddress.setText("");
            EstablishmentId.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('establishment_id_seq', (SELECT MAX(id) FROM establishment))");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void DeleteReceipt(ActionEvent event) {
        if(receiptId.getText().length() >= 1 ){
            sInputGet2 = receiptId.getText();

        }
        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("CALL DeleteReceiptById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet2));
                preparedStatement.executeUpdate();
            }
            sInputGet1 = null;
            sInputGet2 = null;
            receiptId.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('receipt_id_seq', (SELECT MAX(id) FROM receipt))");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void DeleteService(ActionEvent event) {

        if(serviceName.getText().length() >= 1 ){
            sInputGet2 = serviceName.getText();

        } else if (serviceId.getText().length() >= 1) {
            sInputGet1 = serviceId.getText();

        }

        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("DELETE FROM service WHERE name = ?");
                preparedStatement.setString(1, sInputGet2);
                preparedStatement.executeUpdate();

            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("CALL DeleteServiceById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                preparedStatement.executeUpdate();

            }
            sInputGet1 = null;
            sInputGet2 = null;
            serviceName.setText("");
            serviceId.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('service_id_seq', (SELECT MAX(id) FROM service));");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void DeleteUsers(ActionEvent event) {

        if(usersLogin.getText().length() >= 1 ){
            sInputGet2 = usersLogin.getText();

        } else if (usersId.getText().length() >= 1) {
            sInputGet1 = usersId.getText();

        }

        try {
            connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            if(sInputGet2 != null){
                preparedStatement = connection.prepareStatement("DELETE FROM users WHERE username = ?");
                preparedStatement.setString(1, sInputGet2);
                preparedStatement.executeUpdate();

            } else if (sInputGet1 != null) {
                preparedStatement = connection.prepareStatement("CALL DeleteUserById(?);");
                preparedStatement.setInt(1, Integer.parseInt(sInputGet1));
                preparedStatement.executeUpdate();

            }

            sInputGet1 = null;
            sInputGet2 = null;
            usersLogin.setText("");
            usersId.setText("");



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement = connection.prepareStatement("SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));");
                preparedStatement.executeQuery();
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void addUsergo(ActionEvent event) {

        transitionButton.transitiionNotClose(addUserId, "AddUsers.fxml", "AddUsers");


    }

    @FXML
    void editBonus(ActionEvent event) {

        if(BonusId.getText().length() >=1 && BonusClientId.getText().length() < 1 && BonusSize.getText().length() <1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;


                    preparedStatement = connection.prepareStatement("SELECT * FROM client_bonus WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(BonusId.getText()));
                    resultSet = preparedStatement.executeQuery();




                    while (resultSet.next()){
                        BonusClientId.setText(resultSet.getString("client_id"));
                        BonusSize.setText(resultSet.getString("bonus"));
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

                    preparedStatement = connection.prepareStatement("UPDATE client_bonus SET client_id = ? , bonus = ? WHERE id = ?");
                    preparedStatement.setInt(1, Integer.parseInt(BonusClientId.getText()));
                    preparedStatement.setInt(2, Integer.parseInt(BonusSize.getText()));
                    preparedStatement.setInt(3, Integer.parseInt(BonusId.getText()));
                    preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    BonusClientId.setText("");
                    BonusSize.setText("");
                    BonusId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }


    }

    @FXML
    void editBooking(ActionEvent event) {

        if(BookingId.getText().length() >= 1 && BookingidClient.getText().length() < 1 && BookingServiceId.getText().length() < 1 && BookingDate.getText().length() < 1 &&
        BookingratingEmployee.getText().length() < 1 && BookingidEmployee.getText().length() < 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;


                preparedStatement = connection.prepareStatement("SELECT * FROM booking WHERE id = ?");
                preparedStatement.setInt(1, Integer.parseInt(BookingId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    BookingidClient.setText(resultSet.getString("client_id"));
                    BookingServiceId.setText(resultSet.getString("service_id"));
                    BookingDate.setText(resultSet.getString("booking_date"));
                    BookingratingEmployee.setText(resultSet.getString("master_rating"));
                    BookingidEmployee.setText(resultSet.getString("master_id"));
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

                preparedStatement = connection.prepareStatement("UPDATE booking SET client_id = ? , service_id = ?, booking_date = ?, master_rating = ?, master_id = ? WHERE id = ?");
                preparedStatement.setInt(1, Integer.parseInt(BookingidClient.getText()));
                preparedStatement.setInt(2, Integer.parseInt(BookingServiceId.getText()));
                preparedStatement.setDate(3, Date.valueOf(BookingDate.getText()));
                preparedStatement.setDouble(4, Double.parseDouble(BookingratingEmployee.getText()));
                preparedStatement.setInt(5, Integer.parseInt(BookingidEmployee.getText()));
                preparedStatement.setInt(6, Integer.parseInt(BookingId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    BookingidClient.setText("");
                    BookingServiceId.setText("");
                    BookingDate.setText("");
                    BookingratingEmployee.setText("");
                    BookingidEmployee.setText("");
                    BookingId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @FXML
    void editClient(ActionEvent event) {
        if(clientId.getText().length() >= 1 && clientFIO.getText().length() < 1 && clientPyhone.getText().length() < 1 && clientStatus.getText().length() < 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;


                preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
                preparedStatement.setInt(1, Integer.parseInt(clientId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    clientFIO.setText(resultSet.getString("full_name"));
                    clientPyhone.setText(resultSet.getString("contact_information"));
                    clientStatus.setText(resultSet.getString("client_status"));

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

                preparedStatement = connection.prepareStatement("UPDATE client SET full_name = ? , contact_information = ?, client_status = ? WHERE id = ?");
                preparedStatement.setString(1, clientFIO.getText());
                preparedStatement.setString(2, clientPyhone.getText());
                preparedStatement.setString(3, clientStatus.getText());
                preparedStatement.setInt(4, Integer.parseInt(clientId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    clientFIO.setText("");
                    clientPyhone.setText("");
                    clientStatus.setText("");
                    clientId.setText("");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @FXML
    void editEE(ActionEvent event) {

        if(eeIdEmployee.getText().length() >= 1 && eeIdEstablishment.getText().length() < 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;


                preparedStatement = connection.prepareStatement("SELECT * FROM establishment_employee WHERE employee_id = ?");
                preparedStatement.setInt(1, Integer.parseInt(eeIdEmployee.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    eeIdEstablishment.setText(resultSet.getString("establishment_id"));

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

                preparedStatement = connection.prepareStatement("UPDATE establishment_employee SET establishment_id = ?  WHERE employee_id = ?");
                preparedStatement.setInt(1, Integer.parseInt(eeIdEstablishment.getText()));
                preparedStatement.setInt(2, Integer.parseInt(eeIdEmployee.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    eeIdEstablishment.setText("");
                    eeIdEmployee.setText("");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

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
    void editReceipt(ActionEvent event) {
        if(receiptId.getText().length() >= 1 && receiptClientId.getText().length() < 1 && receiptIdEstablishment.getText().length() < 1 &&
        receiptIdBooking.getText().length() < 1 && receiptPrice.getText().length() < 1){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;

                preparedStatement = connection.prepareStatement("SELECT * FROM receipt WHERE id = ?");

                preparedStatement.setInt(1, Integer.parseInt(receiptId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    receiptClientId.setText(resultSet.getString("client_id"));
                    receiptIdEstablishment.setText(resultSet.getString("establishment_id"));
                    receiptIdBooking.setText(resultSet.getString("booking_id"));
                    receiptPrice.setText(resultSet.getString("price"));
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

                preparedStatement = connection.prepareStatement("UPDATE receipt SET client_id = ? , " +
                        "establishment_id = ?, booking_id = ?, price = ? WHERE id = ?");
                preparedStatement.setInt(1, Integer.parseInt(receiptClientId.getText()));
                preparedStatement.setInt(2, Integer.parseInt(receiptIdEstablishment.getText()));
                preparedStatement.setInt(3, Integer.parseInt(receiptIdBooking.getText()));
                preparedStatement.setDouble(4, Double.parseDouble(receiptPrice.getText()));
                preparedStatement.setInt(5, Integer.parseInt(receiptId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    receiptClientId.setText("");
                    receiptIdEstablishment.setText("");
                    receiptIdBooking.setText("");
                    receiptPrice.setText("");
                    receiptId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @FXML
    void editService(ActionEvent event) {
        if(serviceId.getText().length() >= 1 && serviceName.getText().length() < 1 && servicePrice.getText().length() < 1){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;

                preparedStatement = connection.prepareStatement("SELECT * FROM service WHERE id = ?");

                preparedStatement.setInt(1, Integer.parseInt(serviceId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    serviceName.setText(resultSet.getString("name"));
                    servicePrice.setText(resultSet.getString("price"));
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

                preparedStatement = connection.prepareStatement("UPDATE service SET name = ? , " +
                        "price = ? WHERE id = ?");
                preparedStatement.setString(1, serviceName.getText());
                preparedStatement.setDouble(2, Double.parseDouble(servicePrice.getText()));
                preparedStatement.setInt(3, Integer.parseInt(serviceId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    serviceName.setText("");
                    servicePrice.setText("");
                    serviceId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    DB db = new DB();

    @FXML
    void editUsers(ActionEvent event) {

        if(usersId.getText().length() >= 1 && usersLogin.getText().length() < 1 && usersPassword.getText().length() < 1 &&
                usersGroup.getText().length() < 1 && usersEmployeeId.getText().length() < 1){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                ResultSet resultSet;

                preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");

                preparedStatement.setInt(1, Integer.parseInt(usersId.getText()));
                resultSet = preparedStatement.executeQuery();




                while (resultSet.next()){
                    usersLogin.setText(resultSet.getString("username"));
                    usersPassword.setText(resultSet.getString("password"));
                    usersGroup.setText(resultSet.getString("user_group"));
                    usersEmployeeId.setText(resultSet.getString("employee_id"));
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

                preparedStatement = connection.prepareStatement("UPDATE users SET username = ? , " +
                        "password = ?, user_group = ?, employee_id = ? WHERE id = ?");

                preparedStatement.setString(1, usersLogin.getText());
                preparedStatement.setString(2, db.convertToSHA256(usersPassword.getText()));
                preparedStatement.setString(3, usersGroup.getText());
                preparedStatement.setInt(4, Integer.parseInt(usersEmployeeId.getText()));
                preparedStatement.setInt(5, Integer.parseInt(usersId.getText()));
                preparedStatement.executeUpdate();




            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                    statement.close();
                    usersLogin.setText("");
                    usersPassword.setText("");
                    usersGroup.setText("");
                    usersEmployeeId.setText("");
                    usersId.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");



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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");



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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");
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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");

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


        transitionButton.transitiionNotClose(findUsersId, "DateBD.fxml", "date");



    }

    @FXML
    void inSBonus(ActionEvent event) {

        if(BonusSize.getText().length() >=1 && BonusClientId.getText().length() >=1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO client_bonus (client_id, bonus) VALUES (?, ?) ");
                preparedStatement.setInt(1, Integer.parseInt(BonusClientId.getText()));
                preparedStatement.setInt(2, Integer.parseInt(BonusSize.getText()));
                preparedStatement.executeUpdate();

                BonusId.setText("");
                BonusSize.setText("");
                BonusClientId.setText("");



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
            BonusId.setText("Введите все значения");
        }

    }

    @FXML
    void inSBooking(ActionEvent event) {
        if(BookingServiceId.getText().length() >=1 && BookingDate.getText().length() >=1 && BookingidEmployee.getText().length() >=1 &&
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
    void inSClient(ActionEvent event) {
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
    void inSEE(ActionEvent event) {
        if(eeIdEstablishment.getText().length() >= 1 && eeIdEmployee.getText().length() >= 1){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO establishment_employee (establishment_id, employee_id ) VALUES (?, ?) ");
                preparedStatement.setInt(1, Integer.parseInt(eeIdEstablishment.getText()));
                preparedStatement.setInt(2, Integer.parseInt(eeIdEmployee.getText()));
                preparedStatement.executeUpdate();

                eeIdEstablishment.setText("");
                eeIdEmployee.setText("");




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
        }else {
            eeIdEstablishment.setText("Введите все значения");
        }

    }

    @FXML
    void inSEmployee(ActionEvent event) {
        if(employeeFio.getText().length() >= 1 && employeePosition.getText().length() >= 1 && employeePhone.getText().length() >= 1 &&
        employeeExperence.getText().length() >= 1 && employeeSalary.getText().length() >= 1 && employeeInfo.getText().length() >= 1 && employeeAge.getText().length() >= 1 &&
        employeeRating.getText().length() >= 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO employee (full_name, position, contact_information, experience, salary, brief_information, age, rating)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");

                preparedStatement.setString(1, employeeFio.getText());
                preparedStatement.setString(2, employeePosition.getText());
                preparedStatement.setString(3, employeePhone.getText());
                preparedStatement.setInt(4, Integer.parseInt(employeeExperence.getText()));
                preparedStatement.setDouble(5, Double.parseDouble(employeeSalary.getText()));
                preparedStatement.setString(6, employeeInfo.getText());
                preparedStatement.setInt(7, Integer.parseInt(employeeAge.getText()));
                preparedStatement.setDouble(8, Double.parseDouble(employeeRating.getText()));
                preparedStatement.executeUpdate();

                employeeId.setText("");
                employeeFio.setText("");
                employeePosition.setText("");
                employeePhone.setText("");
                employeeExperence.setText("");
                employeeSalary.setText("");
                employeeInfo.setText("");
                employeeAge.setText("");
                employeeRating.setText("");



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

            employeeId.setText("Введите все значения");
        }

    }

    @FXML
    void inSEstbalishment(ActionEvent event) {
        if(EstablishmentType.getText().length() >= 1 && EstablishmentAddress.getText().length() >= 1
                && EstablishmentPostalCode.getText().length() >= 1 && EstablishmentPhone.getText().length() >= 1 && EstablishmentNumberEmpl.getText().length() >= 1 ){

            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();

                AddressPostalCode addressPostalCode = new AddressPostalCode(EstablishmentAddress.getText(), EstablishmentPostalCode.getText());

                preparedStatement = connection.prepareStatement("INSERT INTO establishment (establishment_type, address_with_postal_code, phone_number, number_of_employees) " +
                        "VALUES (?, ROW(?, ?), ?, ?) ");

                preparedStatement.setString(1, EstablishmentType.getText());
                preparedStatement.setString(2, EstablishmentAddress.getText());
                preparedStatement.setInt(3, Integer.parseInt(EstablishmentPostalCode.getText()));
                preparedStatement.setString(4, EstablishmentPhone.getText());
                preparedStatement.setInt(5, Integer.parseInt(EstablishmentNumberEmpl.getText()));
                preparedStatement.executeUpdate();

                EstablishmentId.setText("");
                EstablishmentType.setText("");
                EstablishmentAddress.setText("");
                EstablishmentPostalCode.setText("");
                EstablishmentPhone.setText("");
                EstablishmentNumberEmpl.setText("");



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

        } else{
            EstablishmentId.setText("Введите все значения");
        }

    }

    @FXML
    void inSReceipt(ActionEvent event) {
        if( receiptClientId.getText().length() >= 1 && receiptIdEstablishment.getText().length() >= 1 &&
        receiptIdBooking.getText().length() >= 1 && receiptPrice.getText().length() >= 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO receipt (client_id, establishment_id, booking_id, price) " +
                        "VALUES (?, ?, ?, ?) ");

                preparedStatement.setInt(1, Integer.parseInt(receiptClientId.getText()));
                preparedStatement.setInt(2, Integer.parseInt(receiptIdEstablishment.getText()));
                preparedStatement.setInt(3, Integer.parseInt(receiptIdBooking.getText()));
                preparedStatement.setDouble(4, Double.parseDouble(receiptPrice.getText()));

                preparedStatement.executeUpdate();

                receiptId.setText("");
                receiptClientId.setText("");
                receiptIdEstablishment.setText("");
                receiptIdBooking.setText("");
                receiptPrice.setText("");




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
        } else{
            receiptId.setText("Введите все значения");
        }
    }


    @FXML
    void inService(ActionEvent event) {
        if( serviceName.getText().length() >= 1 && servicePrice.getText().length() >= 1 ){
            try {
                connection = DriverManager.getConnection(DB.URL, LOGIN, PASSWORD);
                statement = connection.createStatement();


                preparedStatement = connection.prepareStatement("INSERT INTO service (name, price) VALUES (?, ?) ");

                preparedStatement.setString(1, serviceName.getText());
                preparedStatement.setDouble(2, Double.parseDouble(servicePrice.getText()));
                preparedStatement.executeUpdate();

                serviceId.setText("");
                serviceName.setText("");
                servicePrice.setText("");



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
            serviceId.setText("Введите все значения");
        }

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
