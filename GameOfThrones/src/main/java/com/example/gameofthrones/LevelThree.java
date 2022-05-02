package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class LevelThree extends Level{
    private int numOfBlackSoldier;
    private int numOfRedSoldier;
    private int numOfTent;
    private final int maxNumOfRedSoldier =18;
    private final int minNumOfRedSoldier =15;
    private final int maxNumOfTent=10;
    private final int minNumOfTent=7;

    private int blackSoldierMaxPower=105;
    private int redSoldierMaxPower=80;
    private int increment=20;
    private int basePower=80;
    private int[] basePowerAtCol;
    private List<Pair<Integer,Integer>> availableCell;
    public LevelThree(Scene scene, Group prevroot) {
        super(scene,prevroot);
        setSoldiers();
    }

    @Override
    public void setTheme() {
        setTreeName("levelOneTree.png");
        setBackgroundName("levelOneBG.jpg");
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
        return (int) (Math.random()*1000000.0);
    }

}
