package com.example.gameofthrones;
import com.example.leraringjavafx.Brick;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane ;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Maze implements Initializable {
    @FXML
    AnchorPane maze;
    private int screenWidth = 1200;
    private int screenHeight = 700;

    private int row = 7;
    private int col = 12;

    private int rectangleWidth = screenWidth/col;
    private int rectangleHeight = screenHeight/row;

    private int playerRow=3;
    private  int playerCol=0;
    private TranslateTransition translate;
    private ImageView player;
    private int[][] isPath;
    private int[][] visited = new int[row][col];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();

        player = new ImageView();
        player.setFitWidth(rectangleWidth);
        player.setFitHeight(rectangleHeight);
        player.setY(playerRow*rectangleHeight);
        player.setX(playerCol*rectangleWidth);
        player.setImage(new Image(String.valueOf(getClass().getResource("deneris.jpeg"))));

        maze.getChildren().add(player);
        maze.setPrefHeight(screenHeight);
        maze.setPrefWidth(screenWidth);

        translate = new TranslateTransition();
        translate.setNode(player);
        translate.setDuration(Duration.millis(500));


        int gridX, gridY;

        for (int i = 0; i < screenHeight; i += rectangleHeight)
            for (int j = 0; j < screenWidth; j += rectangleWidth) {
                gridX = i / rectangleWidth;
                gridY = j / rectangleHeight;
                System.out.println(gridX +" "+gridY);
                if (isPath[gridX][gridY] == 0) {
                    ImageView imageView = new ImageView();
                    Image image = new Image(String.valueOf(getClass().getResource("brick.png")));
                    Brick brick = new Brick(j, i, rectangleHeight,rectangleWidth,image, imageView);
                    maze.getChildren().add(imageView);
                    brick.draw();
                }

            }
    }

    void init() {
        isPath = new int[row][col];

        FileReader reader;

        int random=1;

        String path = "src\\main\\resources\\com\\example\\gameofthrones\\path"+ Integer.toString(random)+".txt";

        try
        {
            reader = new FileReader(path);
            MakePath makePath = new MakePath(isPath,reader,row,col);

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        for (int gridX = 0; gridX < row; gridX++)
            for (int gridY = 0; gridY < col; gridY++) {
                if(isPath[gridX][gridY]==1) visited[gridX][gridY]=0;
                else visited[gridX][gridY] = 1;
            }
    }



    // Moving Main Character
    public void moveUp()
    {
        if(playerRow-1 >=0 && visited[playerRow-1][playerCol]==0)
        {
            playerRow-=1;
            visited[playerRow][playerCol]=1;
            translate.setByY(-rectangleHeight);
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
            translate.setByY(rectangleHeight);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveRight()
    {
        if(playerCol+1<col && visited[playerRow][playerCol+1]==0)
        {
            playerCol+=1;
            visited[playerRow][playerCol]=1;
            translate.setByX(rectangleWidth);
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
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
            translate.play();
        }
    }


}
