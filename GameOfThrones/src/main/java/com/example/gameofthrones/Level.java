package com.example.gameofthrones;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Level{

    public AnchorPane maze;
    private Group prevroot;
    private Scene scene;
    private int gameScreenWidth = 1080;
    private int gameScreenHeight = 700;
    public int totalSoldier=0;
    public int row = 7;
    public int col = 12;

    public int rectangleWidth = gameScreenWidth /col;
    public int rectangleHeight = gameScreenHeight /row;

    private int sidePanelWidth=250;


    private int playerCellPaneRow =3;

    private  int playerCellPaneCol =0;
    private int killCount=0;
    private Player player;

    private String treeName;
    private String backgroundName;
    private String levelName;
    private String houseLogoName;
    private AnchorPane playerCellPane;
    private int numOfTextFile=1;
    private int transitionTime= 100;
    private TranslateTransition translate;

    private Node sidePanel;
    private SidePanel sidePanelControler;
    private GameElement curSoldier;
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

        maze.setPrefHeight(gameScreenHeight);
        maze.setPrefWidth(gameScreenWidth +sidePanelWidth);

        setTheme();

        setMazeBackground();

        addPlayerToMaze();

        addAnimationToPlayer();

        addTreeToMaze();

        addSidePanelToMaze();

        addListenerToScene();


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
    public void addSidePanelToMaze(){
        FXMLLoader loader=null;
        try {
            loader = new FXMLLoader(HelloApplication.class.getResource("side-panel.fxml"));
            sidePanel = loader.load();
            sidePanelControler = loader.getController();
        } catch (IOException e) {
            System.out.println(e);
        }
        setSidePanel();
        sidePanelControler.setPlace(gameScreenWidth,0);
        sidePanelControler.setPlayerPower(player.getPower());
        sidePanelControler.setCurrentLevel(levelName);
        sidePanelControler.setCurrentHouseLogo(houseLogoName);
        maze.getChildren().add(sidePanel);
    }
    private void calculateSoldiers()
    {
        for(int i=0;i<row;i++)
            for (int j=0;j<col;j++) if(isPath[i][j]==1) totalSoldier++;
        totalSoldier--;
    }
    public abstract void setTheme();
    public abstract void setSidePanel();

    private void setMazeBackground(){
        String image = getClass().getResource(backgroundName).toExternalForm();
        maze.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: 100% 100%;");
    }

    private void addPlayerToMaze()
    {
        player = new Player(playerCellPaneCol * rectangleWidth, playerCellPaneRow * rectangleHeight, rectangleHeight, rectangleWidth, maze,100);
        playerCellPane = player.getCellPane();
    }

    private void addAnimationToPlayer()
    {
        translate = new TranslateTransition();
        translate.setNode(playerCellPane);
        translate.setDuration(Duration.millis(transitionTime));
    }


    private void addTreeToMaze()
    {
        int gridRow, gridCol;

        for (int i = 0; i < gameScreenHeight; i += rectangleHeight)
            for (int j = 0; j < gameScreenWidth; j += rectangleWidth) {
                gridRow = i / rectangleHeight;
                gridCol = j / rectangleWidth;

                if (isPath[gridRow][gridCol] == 0) {
                    Tree tree = new Tree(j, i, rectangleHeight,rectangleWidth,maze,treeName);
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
        if(playerCellPaneRow -1 >=0 && visited[playerCellPaneRow -1][playerCellPaneCol]==0 )
        {
            curSoldier=gameElements[playerCellPaneRow-1][playerCellPaneCol];
            if(curSoldier.check(player)){
                playerCellPaneRow -=1;
                visited[playerCellPaneRow][playerCellPaneCol] = 1;
                translate.setByY(-rectangleHeight);
                translate.setByX(0);
                gameElements[playerCellPaneRow][playerCellPaneCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
    }
    private void moveDown()
    {
        if(playerCellPaneRow +1<row && visited[playerCellPaneRow +1][playerCellPaneCol]==0)
        {
            curSoldier=gameElements[playerCellPaneRow+1][playerCellPaneCol];
            if(curSoldier.check(player)){
                playerCellPaneRow +=1;
                visited[playerCellPaneRow][playerCellPaneCol] = 1;
            translate.setByY(rectangleHeight);
            translate.setByX(0);
                gameElements[playerCellPaneRow][playerCellPaneCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
    }
    private void moveRight()
    {
        if(playerCellPaneCol +1<col && visited[playerCellPaneRow][playerCellPaneCol +1]==0)
        {
            curSoldier=gameElements[playerCellPaneRow][playerCellPaneCol+1];
            if(curSoldier.check(player)){
                playerCellPaneCol +=1;
                visited[playerCellPaneRow][playerCellPaneCol] = 1;
            translate.setByX(rectangleWidth);
            translate.setByY(0);
                gameElements[playerCellPaneRow][playerCellPaneCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
    }
    private void moveLeft()
    {
        if(playerCellPaneCol -1 >=0 && visited[playerCellPaneRow][playerCellPaneCol -1]==0)
        {
            curSoldier=gameElements[playerCellPaneRow][playerCellPaneCol-1];
            if(curSoldier.check(player)){
                playerCellPaneCol -=1;
                visited[playerCellPaneRow][playerCellPaneCol] = 1;
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
                gameElements[playerCellPaneRow][playerCellPaneCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
    }

    private void backToPreRoot()
    {
        scene.setRoot(prevroot);
    }

    public int getPlayerCellPaneRow() {
        return playerCellPaneRow;
    }
    public int getPlayerCellPaneCol() {
        return playerCellPaneCol;
    }
    public int getTotalSoldier()
    {
        return totalSoldier;
    }
    public void setTreeName(String treeName)
    {
        this.treeName= treeName;
    }
    public void setBackgroundName(String backgroundName)
    {
        this.backgroundName= backgroundName;
    }
    public void setLevelName(String levelName)
    {
        this.levelName=levelName;
    }
    public void setHouseLogoName(String houseLogoName)
    {
        this.houseLogoName= houseLogoName;
    }

}
