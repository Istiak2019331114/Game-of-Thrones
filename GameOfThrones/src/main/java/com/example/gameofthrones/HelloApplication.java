package com.example.gameofthrones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("maze.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Maze controler = fxmlLoader.getController();
        scene.setOnKeyPressed(keyEvent -> {
            System.out.println(keyEvent.getCode());
            switch (keyEvent.getCode())
            {
                case U:
                    controler.moveUp();
                    break;
                case D:
                    controler.moveDown();
                    break;
                case L:
                    controler.moveLeft();
                    break;
                case R:
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
