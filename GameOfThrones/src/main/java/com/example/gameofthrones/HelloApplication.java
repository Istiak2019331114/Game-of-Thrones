package com.example.gameofthrones;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       // Scene scene = new Scene(new AnchorPane());
        //Group group = new Group();

       // Level level = new Level(scene,group); 
        Parent root = FXMLLoader.load(getClass().getResource("powerShower.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}


