package com.example.gameofthrones;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane ;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Maze implements Initializable {
    @FXML
    AnchorPane maze;
    private int screenSize = 600;
    private int row = 6;
    private int squareLength = screenSize / row;

    private int playerRow=5;
    private  int playerCol=3;
    private TranslateTransition translate;
    private ImageView player;
    private int[][] isPath = {
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

        player = new ImageView();
        player.setFitWidth(100);
        player.setFitHeight(100);
        player.setY(playerRow*squareLength);
        player.setX(playerCol*squareLength);
        player.setImage(new Image(String.valueOf(getClass().getResource("deneris.jpeg"))));

        maze.getChildren().add(player);
        maze.setPrefHeight(screenSize);
        maze.setPrefWidth(screenSize);

        translate = new TranslateTransition();
        translate.setNode(player);
        translate.setDuration(Duration.millis(500));


        int gridX, gridY;

        for (int i = 0; i < screenSize; i += squareLength)
            for (int j = 0; j < screenSize; j += squareLength) {
                gridX = i / squareLength;
                gridY = j / squareLength;
                if (isPath[gridX][gridY] == 0) {
                    ImageView imageView = new ImageView();
                    Image image = new Image(String.valueOf(getClass().getResource("brick.png")));
                    com.example.leraringjavafx.Brick brick = new com.example.leraringjavafx.Brick(j, i, squareLength, image, imageView);
                    maze.getChildren().add(imageView);
                    brick.draw();
                }

            }
    }

    void init() {
        for (int gridX = 0; gridX < row; gridX++)
            for (int gridY = 0; gridY < row; gridY++) {
                if(isPath[gridX][gridY]==1) visited[gridX][gridY]=0;
                else visited[gridX][gridY] = 1;
            }
    }
    public void moveUp()
    {
        if(playerRow-1 >=0 && visited[playerRow-1][playerCol]==0)
        {
            playerRow-=1;
            visited[playerRow][playerCol]=1;
            translate.setByY(-squareLength);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveDown()
    {
        if(playerRow+1<row && visited[playerRow+1][playerCol]==0)
        {
            playerRow+=1;
            visited[playerRow][playerCol]=1;
            translate.setByY(squareLength);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveRight()
    {
        if(playerCol+1<row && visited[playerRow][playerCol+1]==0)
        {
            playerCol+=1;
            visited[playerRow][playerCol]=1;
            translate.setByX(squareLength);
            translate.setByY(0);
            translate.play();
        }
    }
    public void moveLeft()
    {
        if(playerCol-1 >=0 && visited[playerRow][playerCol-1]==0)
        {
            playerCol-=1;
            visited[playerRow][playerCol]=1;
            translate.setByX(-squareLength);
            translate.setByY(0);
            translate.play();
        }
    }


}