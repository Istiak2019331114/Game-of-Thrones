package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;


public class LevelThree extends Level{

    public LevelThree(Scene scene, Group prevroot, LevelCount levelCompleted) {
        super(scene,prevroot,levelCompleted);
        levelNumber=1;
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
    public void setWinningPanelName()
    {
        winningPanelName = "HouseLannisterWinningPanel.jpg";
        winMessage="House Lannister Conquered";
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
