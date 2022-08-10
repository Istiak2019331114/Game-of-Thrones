package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;


public class LevelFour extends Level{

    public LevelFour(Scene scene, Group prevroot, LevelCount levelCompleted) {
        super(scene,prevroot,levelCompleted);
        levelNumber=3;
    }

    @Override
    public void setTheme() {
        setTreeName("levelFourCactus.png");
        setBackgroundName("levelFourBG.png");
    }
    @Override
    public void setSidePanel()
    {
        setLevelName("House Stark");
        setHouseLogoName("House Stark.png");
    }
    @Override
    public void setWinningPanelName()
    {
        winningPanelName = "HouseStarkWinningPanel.jpg";
        winMessage="House Stark Conquered";
    }
    @Override
    public void setupLevel() {
        maxNumOfRedSoldier = (int) ( getTotalSoldier() * 0.5);
        minNumOfRedSoldier =(int) ( getTotalSoldier() * 0.45);
        maxNumOfTent=(int) ( getTotalSoldier() * 0.3);
        minNumOfTent=(int) ( getTotalSoldier() * 0.25);
        powerIncrement =15;
        soldierBasePower =200;
    }
}

