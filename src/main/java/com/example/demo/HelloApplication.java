package com.example.demo;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BackUpBD backUpBD = new BackUpBD();

        LocalTime targetTime = LocalTime.of(23, 59);


        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();


        long delay = Duration.between(currentTime, targetTime).toMillis();


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(new javafx.util.Duration(delay)));


        EventHandler<ActionEvent> eventHandler = event -> {

            backUpBD.backupPostgre();
        };
        timeline.setOnFinished(eventHandler);


        timeline.play();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("BarberShop");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}