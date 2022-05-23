package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LevelTwo extends Level{
    public LevelTwo(Scene scene, Group prevroot) {
        super(scene,prevroot);
    }

    @Override
    public void setTheme() {
        setTreeName("levelTwoTree.png");
        setBackgroundName("levelTwoBG.jpg");
    }
    @Override
    public void setSidePanel()
    {
        setLevelName("House Baratheon");
        setHouseLogoName("House Baratheon.png");
    }
    @Override
    public void setupLevel() {
        maxNumOfRedSoldier = (int) ( getTotalSoldier() * 0.5);
        minNumOfRedSoldier =(int) ( getTotalSoldier() * 0.45);
        maxNumOfTent=(int) ( getTotalSoldier() * 0.25);
        minNumOfTent=(int) ( getTotalSoldier() * 0.2);
        powerIncrement =15;
        soldierBasePower =200;
    }

}
