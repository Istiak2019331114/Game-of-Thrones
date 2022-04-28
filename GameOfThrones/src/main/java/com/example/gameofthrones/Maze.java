package com.example.gameofthrones;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane ;
import javafx.util.Duration;

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

    private int playerimageViewRow=3;
    private  int playerimageViewCol=0;
    private TranslateTransition translate;
    private ImageView playerimageView;
    private int[][] isPath;
    private int[][] visited = new int[row][col];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        init();

        Player player = new Player(playerimageViewCol * rectangleWidth, playerimageViewRow * rectangleHeight, rectangleHeight, rectangleWidth, maze);
        playerimageView = player.getImageView();
        playerimageView.setSmooth(true);

        maze.setPrefHeight(screenHeight);
        maze.setPrefWidth(screenWidth);

        translate = new TranslateTransition();
        translate.setNode(playerimageView);
        translate.setDuration(Duration.millis(500));

        int gridX, gridY;

        for (int i = 0; i < screenHeight; i += rectangleHeight)
            for (int j = 0; j < screenWidth; j += rectangleWidth) {
                gridX = i / rectangleWidth;
                gridY = j / rectangleHeight;

                if (isPath[gridX][gridY] == 0) {
                    Brick brick = new Brick(j, i, rectangleHeight,rectangleWidth,maze);
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
        if(playerimageViewRow-1 >=0 && visited[playerimageViewRow-1][playerimageViewCol]==0)
        {
            playerimageViewRow-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(-rectangleHeight);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveDown()
    {
        if(playerimageViewRow+1<row && visited[playerimageViewRow+1][playerimageViewCol]==0)
        {
            playerimageViewRow+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(rectangleHeight);
            translate.setByX(0);
            translate.play();
        }
    }
    public void moveRight()
    {
        if(playerimageViewCol+1<col && visited[playerimageViewRow][playerimageViewCol+1]==0)
        {
            playerimageViewCol+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(rectangleWidth);
            translate.setByY(0);
            translate.play();
        }
    }
    public void moveLeft()
    {
        if(playerimageViewCol-1 >=0 && visited[playerimageViewRow][playerimageViewCol-1]==0)
        {
            playerimageViewCol-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
            translate.play();
        }
    }


}
