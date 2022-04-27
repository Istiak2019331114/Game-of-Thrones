package com.example.gameofthrones;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MakePath {
    private int[][] isPath;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private int row , col;

    public MakePath(int[][] isPath, FileReader fileReader, int row, int col) {
        this.isPath = isPath;
        this.fileReader = fileReader;
        this.row = row;
        this.col = col;
        makePath();
    }

    private void makePath(){
        try {
            bufferedReader = new BufferedReader(fileReader);
            String line;
            int currentRow=0;
            while ((line = bufferedReader.readLine())!=null)
            {
                makeCurrentRowPath(currentRow,line);
                currentRow++;
            }
        } catch (IOException e) {
            System.out.println("hi There 2");
        }
    }

    private void makeCurrentRowPath(int currentRow, String line)
    {
        for(int i=0;i<col;i++)
        {
            isPath[currentRow][i] = Character.getNumericValue(line.charAt(i));
        }
    }
}
