package com.example.gameofthrones;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane ;
import java.net.URL;
import java.util.ResourceBundle;

public class Maze implements Initializable {
    @FXML
    AnchorPane maze;
    private int screenSize = 600;
    private int row = 6;
    private int squareLength = screenSize / row;
    private int[][] isBlock = {
            {0, 1, 1, 1, 1, 0},
            {1, 1, 0, 0, 1, 0},
            {1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1},
            {1, 1, 1, 1, 0, 0}
    };
    private int[][] visited = new int[row][row];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();

        maze.setPrefHeight(screenSize);
        maze.setPrefWidth(screenSize);

        int gridX, gridY;

        for (int i = 0; i < screenSize; i += squareLength)
            for (int j = 0; j < screenSize; j += squareLength) {
                gridX = i / squareLength;
                gridY = j / squareLength;
                if (isBlock[gridX][gridY] == 0) {
                    ImageView imageView = new ImageView();
                    Image image = new Image(String.valueOf(getClass().getResource("brick.png")));
                    com.example.leraringjavafx.Brick brick = new com.example.leraringjavafx.Brick(i, j, squareLength, image, imageView);
                    maze.getChildren().add(imageView);
                    brick.draw();
                }

            }
    }

    void init() {
        for (int gridX = 0; gridX < row; gridX++)
            for (int gridY = 0; gridY < row; gridY++) {
                visited[gridX][gridY] = isBlock[gridX][gridY];
            }
    }
}