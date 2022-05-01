package com.example.gameofthrones;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;

public  class Level{

    public AnchorPane maze;
    private Group prevroot;
    private Scene scene;
    private int screenWidth = 1080;
    private int screenHeight = 700;
    public int totalSoldier=0;
    public int row = 7;
    public int col = 12;

    public int rectangleWidth = screenWidth/col;
    public int rectangleHeight = screenHeight/row;

    private int playerimageViewRow=3;
    private  int playerimageViewCol=0;
    private int numOfTextFile=1;
    private int transitionTime= 250;
    private TranslateTransition translate;
    private ImageView playerimageView;
    // Row Column based
    public int[][] isPath;
    // Row Column based

    private int[][] visited = new int[row][col];
    // Row Column based
    public GameElement[][] gameElements = new GameElement[row][col];

    public Level(Scene scene, Group prevroot) {

        maze = new AnchorPane();

        this.scene = scene;

        this.scene.setRoot(maze);

        this.prevroot = prevroot;

        init();

        maze.setPrefHeight(screenHeight);
        maze.setPrefWidth(screenWidth+200);

        setMazeBackground();

        addPlayerToMaze();

        addAnimationToPlayer();

        addTreeToMaze();

        addListenerToScene();
        BlackSoldier blackSoldier = new BlackSoldier(playerimageViewCol * rectangleWidth, (playerimageViewRow-1) * rectangleHeight, rectangleHeight, rectangleWidth, maze, 100);
        RedSoldier redSoldier = new RedSoldier(playerimageViewCol * rectangleWidth, (playerimageViewRow-2) * rectangleHeight, rectangleHeight, rectangleWidth, maze, 100);


    }



    void init() {
        isPath = new int[row][col];

        FileReader reader;

        int random=((int)(Math.random()*1000))%numOfTextFile;

        String path = "src\\main\\resources\\com\\example\\gameofthrones\\path"+ Integer.toString(random)+".txt";

        try
        {
            reader = new FileReader(path);
            MakePath makePath = new MakePath(isPath,reader,row,col);
            calculateSoldiers();

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }

        for (int gridRow = 0; gridRow < row; gridRow++)
            for (int gridCol = 0; gridCol < col; gridCol++) {
                if(isPath[gridRow][gridCol]==1) visited[gridRow][gridCol]=0;
                else visited[gridRow][gridCol] = 1;
            }
    }

    private void calculateSoldiers()
    {
        for(int i=0;i<row;i++)
            for (int j=0;j<col;j++) if(isPath[i][j]==1) totalSoldier++;
        totalSoldier--;
    }
    private void setMazeBackground(){
        String image = getClass().getResource("mazeBg.jpg").toExternalForm();
        maze.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: 100% 100%;");
    }

    private void addPlayerToMaze()
    {
        Player player = new Player(playerimageViewCol * rectangleWidth, playerimageViewRow * rectangleHeight, rectangleHeight, rectangleWidth, maze,100);
        playerimageView = player.getImageView();
        playerimageView.setSmooth(true);
    }
    private void addAnimationToPlayer()
    {
        translate = new TranslateTransition();
        translate.setNode(playerimageView);
        translate.setDuration(Duration.millis(transitionTime));
    }

    private void addTreeToMaze()
    {
        int gridRow, gridCol;

        for (int i = 0; i < screenHeight; i += rectangleHeight)
            for (int j = 0; j < screenWidth; j += rectangleWidth) {
                gridRow = i / rectangleHeight;
                gridCol = j / rectangleWidth;

                if (isPath[gridRow][gridCol] == 0) {
                    Tree tree = new Tree(j, i, rectangleHeight,rectangleWidth,maze);
                    gameElements[gridRow][gridCol]= tree;
                }

            }
    }
    private void addListenerToScene()
    {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode())
            {
                case W:
                    moveUp();
                    break;
                case S:
                    moveDown();
                    break;
                case A:
                    moveLeft();
                    break;
                case D:
                    moveRight();
                    break;
                case ESCAPE:
                    backToPreRoot();
                default: break;
            }
        });
    }
     private void sleep()
     {
         try{
             Thread.sleep(transitionTime);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
    private void moveUp() {
        if(playerimageViewRow-1 >=0 && visited[playerimageViewRow-1][playerimageViewCol]==0)
        {
            playerimageViewRow-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(-rectangleHeight);
            translate.setByX(0);
            translate.play();
            sleep();
        }
    }
    private void moveDown()
    {
        if(playerimageViewRow+1<row && visited[playerimageViewRow+1][playerimageViewCol]==0)
        {
            playerimageViewRow+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByY(rectangleHeight);
            translate.setByX(0);
            translate.play();
            sleep();
        }
    }
    private void moveRight()
    {
        if(playerimageViewCol+1<col && visited[playerimageViewRow][playerimageViewCol+1]==0)
        {
            playerimageViewCol+=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(rectangleWidth);
            translate.setByY(0);
            translate.play();
            sleep();
        }
    }
    private void moveLeft()
    {
        if(playerimageViewCol-1 >=0 && visited[playerimageViewRow][playerimageViewCol-1]==0)
        {
            playerimageViewCol-=1;
            visited[playerimageViewRow][playerimageViewCol]=1;
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
            translate.play();
            sleep();
        }
    }

    private void backToPreRoot()
    {
        scene.setRoot(prevroot);
    }


}
