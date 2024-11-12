package com.example.demo.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Класс отвечающий за переход к новому окну через кнопку
 */

public class TransitionButton {

    /** Функция отвечающая за переход к новому окну
     * @param button - кнопка по которой пользователь переходит к новому окну
     * @param wayNextWindow - путь к новому окну
     * @param newNameNextWindow - имя нового окно*/

    public void transitiionWithoutEvent(Button button, String wayNextWindow, String newNameNextWindow ) {
        Stage stage = (Stage) button.getScene().getWindow();

        stage.close();

        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource(wayNextWindow));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(newNameNextWindow);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void transitiionNotClose(Button button, String wayNextWindow, String newNameNextWindow ) {
        Stage stage = (Stage) button.getScene().getWindow();


        FXMLLoader fxmlLoader = new  FXMLLoader(getClass().getResource(wayNextWindow));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(newNameNextWindow);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}