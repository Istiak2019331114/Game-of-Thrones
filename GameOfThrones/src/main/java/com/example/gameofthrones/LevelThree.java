package com.example.gameofthrones;

import com.almasb.fxgl.physics.box2d.dynamics.joints.LimitState;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class LevelThree extends Level{
    private int numOfBlackSoldier;
    private int numOfRedSoldier;
    private int numOfTent;
    private final int maxRedSoldier=25;
    private final int maxNumOfTent=15;
    private int blackSoldierMaxPower=200;
    private int redSoldierMaxPower=150;

    public LevelThree(Scene scene, Group prevroot) {
        super(scene,prevroot);
        setSoldiers();
    }

    private void setSoldiers()
    {
        numOfRedSoldier= getRandom()%maxRedSoldier;
        numOfTent= getRandom()%maxNumOfTent;
        numOfBlackSoldier-=(numOfRedSoldier+numOfTent);
        List<Pair<Integer,Integer>> pairs =  new ArrayList<Pair<Integer,Integer>>();
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
            {
                if(!(i==getPlayerCellPaneRow() && j==getPlayerCellPaneCol()) && isPath[i][j]==1)
                {
                    pairs.add(new Pair<Integer,Integer>(i,j));
                }
            }
        int numOfPairs=pairs.size();
        int index;
        int curRow, curCol;
        Pair<Integer, Integer> curCell = new Pair<Integer, Integer>(0,0);
        while (numOfPairs>0)
        {
            index=getRandom()%numOfPairs;
            curCell= pairs.get(index);

            pairs.remove(index);
            numOfPairs--;

            curRow =curCell.getKey();
            curCol=curCell.getValue();
            GameElement soldier;
            if(numOfTent>0)
            {

                if(getRandom()%2==0)
                {
                    soldier =new BlackSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,getRandom()%blackSoldierMaxPower);
                }
                else {
                    soldier =new RedSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,getRandom()%redSoldierMaxPower);

                }
                Tent tent =new Tent(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,soldier);
                gameElements[curRow][curCol]=tent;
                numOfTent--;
            }
            else if(numOfRedSoldier>0)
            {
                soldier =new RedSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,getRandom()%redSoldierMaxPower);
                gameElements[curRow][curCol]=soldier;
                numOfRedSoldier--;
            }
            else {
                soldier =new BlackSoldier(curCol*rectangleWidth,curRow*rectangleHeight,rectangleHeight,rectangleWidth,maze,getRandom()%blackSoldierMaxPower);
                gameElements[curRow][curCol]=soldier;
                numOfBlackSoldier--;
            }
        }
    }
    private int getRandom()
    {
        return (int) (Math.random()*1000000.0);
    }

}
