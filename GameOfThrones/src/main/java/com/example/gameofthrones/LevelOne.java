package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LevelOne extends Level{

    public LevelOne(Scene scene, Group prevroot) {
        super(scene,prevroot);
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

    @Override
    public void setupLevel() {
        maxNumOfRedSoldier = (int) ( getTotalSoldier() * 0.45);
        minNumOfRedSoldier =(int) ( getTotalSoldier() * 0.4);
        maxNumOfTent=(int) ( getTotalSoldier() * 0.20);
        minNumOfTent=(int) ( getTotalSoldier() * 0.15);
        powerIncrement =20;
        soldierBasePower =200;
    }

}
