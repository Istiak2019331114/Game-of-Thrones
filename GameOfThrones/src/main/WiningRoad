package com.example.gameofthrones;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WiningRoad {
    private int playerRow;
    private int playercol;
    private int numOfRow;
    private int numOfCol;
    private  Random rand = new Random(System.currentTimeMillis());
    List<Pair<Integer, Integer>> winingPath;

    private int destinationRow=3;
    private int destinationCol=11;
    private int [][] visited;

    public WiningRoad(int numOfRow , int numOfCol,int playerRow,int playercol, int[][] trees, List<Pair<Integer, Integer>> path) {
        this.numOfRow = numOfRow;
        this.numOfCol =numOfCol;
        this.playerRow= playerRow;
        this.playercol= playercol;
        this.winingPath =path;
        visited = new int[numOfRow][numOfCol];
        copyToVisited(trees);
        dfs(playerRow,playercol);
    }

    private boolean dfs(int curCellRow,int curCellCol)
    {
        visited[curCellRow][curCellCol]=1;

        if(curCellRow==destinationRow && curCellCol==destinationCol) return  true;

        List<Pair<Integer,Integer>> availableCells = new ArrayList<Pair<Integer,Integer>>();

        getAvailableCells(curCellRow,curCellCol, availableCells);

        while (availableCells.size()>0)
        {
            int index = getRandom()%availableCells.size();

             if(dfs(availableCells.get(index).getKey(),availableCells.get(index).getValue()))
             {
                 System.out.println(availableCells.get(index));
                 winingPath.add(availableCells.get(index));
                 return true;
             }
             availableCells.remove(index);
        }
        return false;
    }
    private void copyToVisited(int[][] trees)
    {
           for(int i=0;i<numOfRow;i++) {
               for (int j = 0; j < numOfCol; j++) {
                   visited[i][j] = trees[i][j];
               }
           }

    }
    private void getAvailableCells(int curCellRow,int curCellCol,List<Pair<Integer,Integer>> availableCells)
    {
        // UP
        if(isAvailable(curCellRow-1,curCellCol)) availableCells.add(new Pair<>(curCellRow-1,curCellCol));
        //Down
        if(isAvailable(curCellRow+1,curCellCol)) availableCells.add(new Pair<>(curCellRow+1,curCellCol));
        //Right
        if(isAvailable(curCellRow,curCellCol+1)) availableCells.add(new Pair<>(curCellRow,curCellCol+1));

    }

    private boolean isAvailable(int row, int col)
    {
        if( (row>=0 && row<numOfRow) && (col>=0 && col<numOfCol) && visited[row][col]==0) return true;
        else return  false;
    }

    private int getRandom()
    {
        int randNum = rand.nextInt();
        if(randNum<0) return -randNum;
        else return randNum;
    }

}
