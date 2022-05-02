package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LevelOne extends Level{
    private int numOfBlackSoldier;
    private int numOfRedSoldier;
    private int numOfTent;
    private final int maxNumOfRedSoldier =15;
    private final int minNumOfRedSoldier =10;
    private final int maxNumOfTent=5;
    private final int minNumOfTent=3;

    private int blackSoldierMaxPower=105;
    private int redSoldierMaxPower=80;
    private int increment=20;
    private int basePower=200;
    private int[] basePowerAtCol;

    private Random rand = new Random(System.currentTimeMillis());
    private List<Pair<Integer,Integer>> availableCell;

    private List<Pair<Integer,Integer>> winingPath=new ArrayList<Pair<Integer,Integer>>();

    public LevelOne(Scene scene, Group prevroot) {
        super(scene,prevroot);
        setSoldiers();
        setSoldierInWiningPath();
    }

    @Override
    public void setTheme() {
       setTreeName("levelOneTree.png");
       setBackgroundName("levelOneBG.jpg");
    }
    @Override
    public void setSidePanel()
    {
        setLevelName("House Greyjoy");
        setHouseLogoName("House Greyjoy.png");
    }
    private void setSoldiers()
    {

        setNumberofSoldier();

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

            soldierPower= basePowerAtCol[curCol]+(getRandom())%increment;

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
    private void setNumberofSoldier()
    {
        numOfRedSoldier= minNumOfRedSoldier + getRandom()%(maxNumOfRedSoldier - minNumOfRedSoldier);
        numOfTent=minNumOfTent+ getRandom()%(maxNumOfTent-minNumOfTent);
        numOfBlackSoldier=getTotalSoldier()-numOfTent-numOfRedSoldier;
    }
    private void getAvailableCell()
    {
        availableCell =  new ArrayList<Pair<Integer,Integer>>();

        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
                if(!(i==getPlayerCellPaneRow() && j==getPlayerCellPaneCol()) && isPath[i][j]==1)
                {
                    availableCell.add(new Pair<Integer,Integer>(i,j));
                }
            }
    }
    private void setBasePower()
    {
        basePowerAtCol = new int [col];
        for(int i=0;i<col;i++) basePowerAtCol[i]=basePower+(increment*i);
    }
    private int getRandom()
    {
        int randNum = rand.nextInt();
        if(randNum<0) return -randNum;
        else return randNum;
    }

    public void setSoldierInWiningPath()
    {
        new WiningRoad(row,col,getPlayerCellPaneRow(),getPlayerCellPaneCol(),getVisited(),winingPath);
        int curRow, curCol;
        int demoPlayerPower=250;

        int curSoldierPower;

        GameElement oldSoldier,curSoldier;

        while (winingPath.size()>0)
        {
           curRow =winingPath.get(winingPath.size()-1).getKey();
           curCol = winingPath.get(winingPath.size()-1).getValue();

           maze.getChildren().remove(gameElements[curRow][curCol].getCellPane());

           curSoldierPower= basePowerAtCol[curCol]+(getRandom())%increment;

           oldSoldier=gameElements[curRow][curCol];


           if(demoPlayerPower-curSoldierPower-basePowerAtCol[curCol]-increment-increment>0)
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
           winingPath.remove(winingPath.size()-1);
        }


    }

}
