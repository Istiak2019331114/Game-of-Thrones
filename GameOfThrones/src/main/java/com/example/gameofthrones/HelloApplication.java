package com.example.gameofthrones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Level controler = fxmlLoader.getController();
        scene.setOnKeyPressed(keyEvent -> {
            System.out.println(keyEvent.getCode());
            switch (keyEvent.getCode())
            {
                case W:
                    controler.moveUp();
                    break;
                case S:
                    controler.moveDown();
                    break;
                case A:
                    controler.moveLeft();
                    break;
                case D:
                    controler.moveRight();
                    break;
                default: break;
            }
        });
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
