package com.example.demo;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class DB {
    public static String URL = "jdbc:postgresql://localhost:5432/BarberShop";
    public static String LOGIN;
    public static String PASSWORD;

    Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;

    public void SearchDriver() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL JDBC Driver found.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("PostgreSQL JDBC Driver is not found.");
        }
    }

    public void TestConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Connection Success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection Failed");
        }
    }
    public String checkRole() {
        String check = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT g.groname \n" +
                    "FROM pg_user u \n" +
                    "JOIN pg_group g ON u.usesysid = ANY(g.grolist) \n" +
                    "WHERE u.usename = current_user;");
            resultSet.next();
            check = resultSet.getString(1);
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
        return check;
    }


    public String raport(String query) {
        String check = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                if(check == null){
                    check = resultSet.getString(1) + " -> " + resultSet.getString(2) + "\n";
                } else {
                    check = check + resultSet.getString(1) + " -> " + resultSet.getString(2)+ "\n";
                }

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
        return check;
    }

    public String raportTotalProfit(String query) {
        String check = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                if(check == null){
                    check = resultSet.getString(1) + "\n";
                } else {
                    check = check + resultSet.getString(1) + "\n";
                }

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
        return check;
    }

    public String convertToSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));


            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return null;
    }












}