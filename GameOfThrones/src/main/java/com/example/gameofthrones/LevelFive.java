package com.example.gameofthrones;

import javafx.scene.Group;
import javafx.scene.Scene;


public class LevelFive extends Level{

    public LevelFive(Scene scene, Group prevroot, LevelCount levelCompleted) {
        super(scene,prevroot,levelCompleted);
        levelNumber=4;
    }

    @Override
    public void setTheme() {
        setTreeName("levelFiveCastle.png");
        setBackgroundName("levelFiveBG.jpg");
    }
    @Override
    public void setSidePanel()
    {
        setLevelName("Westeros Final Battle");
        setHouseLogoName("Westeros Final Battle.png");
    }
    @Override
    public void setWinningPanelName()
    {
        winningPanelName = "WesterosFinalBattleWinningPanel.jpg";
        winMessage="You have Gained Your Throne";
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

