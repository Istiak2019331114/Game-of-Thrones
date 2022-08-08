package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;


public class LevelTwo extends Level{
    public LevelTwo(Scene scene, Group prevroot, LevelCount levelCompleted) {
        super(scene,prevroot,levelCompleted);
        levelNumber=2;
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
        powerIncrement =20;
        soldierBasePower =200;
    }

}
