package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LevelThree extends Level{

    public LevelThree(Scene scene, Group prevroot) {
        super(scene,prevroot);
    }

    @Override
    public void setTheme() {
        setTreeName("levelThreeFire.png");
        setBackgroundName("levelThreeBG.jpg");
    }
    @Override
    public void setSidePanel()
    {
        setLevelName("House Lannister");
        setHouseLogoName("House Lannister.png");
    }
    @Override
    public void setupLevel() {
        maxNumOfRedSoldier = (int) ( getTotalSoldier() * 0.6);
        minNumOfRedSoldier =(int) ( getTotalSoldier() * 0.5);
        maxNumOfTent=(int) ( getTotalSoldier() * 0.25);
        minNumOfTent=(int) ( getTotalSoldier() * 0.2);
        powerIncrement =10;
        soldierBasePower =200;
    }

}
