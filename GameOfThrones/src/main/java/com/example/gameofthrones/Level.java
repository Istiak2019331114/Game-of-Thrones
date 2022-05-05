package com.example.gameofthrones;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    private int playerRow =3;

    private  int playerCol =0;

    private int playerInitialRow =3;

    private  int playerInitialCol =0;

    private int destinationRow=3;
    private int destinationCol=11;
    private int killCount=0;

    private Player player;
    private int numOfBlackSoldier;
    private int numOfRedSoldier;
    private int numOfTent;

    public int maxNumOfRedSoldier =15;
    public int minNumOfRedSoldier =10;
    public int maxNumOfTent=5;
    public int minNumOfTent=3;

    public int powerIncrement =20;
    public int soldierBasePower =200;

    private int[] basePowerAtCol;

    private Random rand = new Random(System.currentTimeMillis());
    private List<Pair<Integer,Integer>> availableCell;
    private String treeName;
    private String backgroundName;
    private String levelName;
    private String houseLogoName;
    private AnchorPane playerCellPane;
    private int numOfTextFile=10;
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

        setupLevel();

        setSoldiers();

        setSoldierInWiningPath();

    }



    void init() {
        isPath = new int[row][col];

        FileReader reader;

        int random=getRandom()%numOfTextFile;

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

        visited[playerInitialRow][playerInitialCol]=1;
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
    public abstract void setupLevel();

    private void setMazeBackground(){
        String image = getClass().getResource(backgroundName).toExternalForm();
        maze.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: 100% 100%;");
    }

    private void addPlayerToMaze()
    {
        player = new Player(playerInitialCol * rectangleWidth, playerInitialRow * rectangleHeight, rectangleHeight, rectangleWidth, maze,250);
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
                case UP:
                    moveUp();
                    break;
                case S:
                case DOWN:
                    moveDown();
                    break;
                case A:
                case LEFT:
                    moveLeft();
                    break;
                case D:
                case RIGHT:
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
        if(playerRow -1 >=0 && visited[playerRow -1][playerCol]==0 )
        {
            curSoldier=gameElements[playerRow -1][playerCol];
            if(curSoldier.check(player)){
                playerRow -=1;
                visited[playerRow][playerCol] = 1;
                translate.setByY(-rectangleHeight);
                translate.setByX(0);
                gameElements[playerRow][playerCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
        checkWiningCondition();
    }
    private void moveDown()
    {
        if(playerRow +1<row && visited[playerRow +1][playerCol]==0)
        {
            curSoldier=gameElements[playerRow +1][playerCol];
            if(curSoldier.check(player)){
                playerRow +=1;
                visited[playerRow][playerCol] = 1;
            translate.setByY(rectangleHeight);
            translate.setByX(0);
                gameElements[playerRow][playerCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
        checkWiningCondition();
    }
    private void moveRight()
    {
        if(playerCol +1<col && visited[playerRow][playerCol +1]==0)
        {
            curSoldier=gameElements[playerRow][playerCol +1];
            if(curSoldier.check(player)){
                playerCol +=1;
                visited[playerRow][playerCol] = 1;
            translate.setByX(rectangleWidth);
            translate.setByY(0);
                gameElements[playerRow][playerCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
        checkWiningCondition();
    }
    private void moveLeft()
    {
        if(playerCol -1 >=0 && visited[playerRow][playerCol -1]==0)
        {
            curSoldier=gameElements[playerRow][playerCol -1];
            if(curSoldier.check(player)){
                playerCol -=1;
                visited[playerRow][playerCol] = 1;
            translate.setByX(-rectangleWidth);
            translate.setByY(0);
                gameElements[playerRow][playerCol].removeFromMaze();
                translate.play();
                curSoldier.updatePlayerPower(player);
                sidePanelControler.setPlayerPower(player.getPower());
                sidePanelControler.setKillCount(++killCount);
//            sleep();
            }
        }
        checkWiningCondition();
    }
   //-----------------------------------------------------------------------------------------
   private void setSoldiers()
   {

       setNumberOfSoldier();

       getAvailableCell();

       setBasePower();

       int numOfPairs= availableCell.size();

       int index;
       int curRow, curCol;
       int soldierPower;

       Pair<Integer, Integer> curCell = new Pair<Integer, Integer>(0,0);

       GameElement soldier,tent;

       while (numOfPairs>0)
       {
           // selecting index randomly
           index=getRandom()%numOfPairs;
           curCell= availableCell.get(index);

           availableCell.remove(index);
           numOfPairs--;

           curRow =curCell.getKey();
           curCol=curCell.getValue();

           soldierPower= basePowerAtCol[curCol]+(getRandom())% powerIncrement;

           if(numOfTent>0)
           {

               if(getRandom()%2==0)
               {
                   soldier =new BlackSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldierPower);
               }
               else {
                   soldier =new RedSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldierPower);

               }
               tent =new Tent(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldier);
               gameElements[curRow][curCol]=tent;
               numOfTent--;
           }

           else if(numOfRedSoldier>0)
           {
               soldier =new RedSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldierPower);
               gameElements[curRow][curCol]=soldier;
               numOfRedSoldier--;
           }
           else {
               soldier =new BlackSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldierPower);
               gameElements[curRow][curCol]=soldier;
               numOfBlackSoldier--;
           }
       }
   }
    private void setNumberOfSoldier()
    {
        numOfRedSoldier= minNumOfRedSoldier + getRandom()%(maxNumOfRedSoldier - minNumOfRedSoldier);
        numOfTent=minNumOfTent+ getRandom()%(maxNumOfTent-minNumOfTent);
        numOfBlackSoldier=totalSoldier-numOfTent-numOfRedSoldier;
    }
    private void getAvailableCell()
    {
        availableCell =  new ArrayList<Pair<Integer,Integer>>();

        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
                if(visited[i][j]==0)
                {
                    availableCell.add(new Pair<Integer,Integer>(i,j));
                }
            }
    }
    private void setBasePower()
    {
        basePowerAtCol = new int [col];
        for(int i=0;i<col;i++) basePowerAtCol[i]= soldierBasePower +(powerIncrement *i);
    }

    public void setSoldierInWiningPath()
    {
        List<Pair<Integer,Integer>> winningPath=new ArrayList<Pair<Integer,Integer>>();
        int curRow, curCol;

        int demoPlayerPower=250;

        int curSoldierPower;

        GameElement oldSoldier,curSoldier;

        new WinningPath(row,col, playerInitialRow, playerInitialCol,visited,winningPath);


        while (winningPath.size()>0)
        {
            curRow = winningPath.get(winningPath.size()-1).getKey();
            curCol = winningPath.get(winningPath.size()-1).getValue();

            maze.getChildren().remove(gameElements[curRow][curCol].getCellPane());

            oldSoldier=gameElements[curRow][curCol];

            curSoldierPower= basePowerAtCol[curCol]+(getRandom())% powerIncrement;

            if(demoPlayerPower-curSoldierPower-basePowerAtCol[curCol] - powerIncrement-powerIncrement >0)
            {
                curSoldier= new RedSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,curSoldierPower);
                demoPlayerPower-=curSoldierPower;
            }
            else{
                curSoldier=new BlackSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,curSoldierPower);
                demoPlayerPower+=curSoldierPower;
            }


            if(oldSoldier instanceof Tent){
                gameElements[curRow][curCol] = new Tent(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,curSoldier);
            }
            else gameElements[curRow][curCol]=curSoldier;
            winningPath.remove(winningPath.size()-1);
        }

    }
//------------------------------------------------------------------------------------------------------------

    private int getRandom()
    {
        int randNum = rand.nextInt();
        if(randNum<0) return -randNum;
        else return randNum;
    }
    private void backToPreRoot()
    {
        scene.setRoot(prevroot);
    }

    public int getPlayerRow() {
        return playerRow;
    }
    public int getPlayerCol() {
        return playerCol;
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
    public int[][] getVisited()
    {
        return visited;
    }

    private void checkWiningCondition()
    {
        if(playerRow==destinationRow && playerCol==destinationCol)
        {

        }
    }
}
