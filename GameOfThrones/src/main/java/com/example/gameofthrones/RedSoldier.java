package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RedSoldier extends GameElement {

    public RedSoldier(int x, int y, int height, int width, AnchorPane pane,int power) {
        super(x, y, height, width, pane,power );
    }

    @Override
    public void draw()
    {
        try
        {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\gameofthrones\\redSoldier.png"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
