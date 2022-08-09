package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tent extends GameElement {
    private int sleeptime=3000;
    private GameElement solider;
    private boolean isRevealed= false;
    public Tent(int x, int y, int height, int width, AnchorPane pane, GameElement solider) {
        super(x, y, height, width, pane);
        this.solider = solider;
        solider.hideFromMaze();
    }

    @Override
    public void draw()
    {
        try
        {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\gameofthrones\\tent.png"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    private void revealSoldier()
    {
        super.removeFromMaze();
        solider.revealInMaze();
    }
    @Override
    public boolean check(GameElement player)
    {
        if(!isRevealed)
        {
            revealSoldier();
            isRevealed=true;
        }
        return solider.check(player);
    }
    // Updates Player Power for this Soldier
    @Override
    public void updatePlayerPower(Player player)
    {
        solider.updatePlayerPower(player);
    }
    @Override
    public void removeFromMaze()
    {
        solider.removeFromMaze();
    }
    private void sleep()
    {
        try{
            Thread.sleep(sleeptime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
