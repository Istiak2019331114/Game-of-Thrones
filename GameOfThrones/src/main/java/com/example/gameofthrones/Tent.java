package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tent extends GameElement {
    public GameElement solider;
    public Tent(int x, int y, int height, int width, AnchorPane pane, GameElement solider) {
        super(x, y, height, width, pane);
        this.solider = solider;
    }

    public void draw()
    {
        try
        {
            image = new Image(new FileInputStream("src\\main\\resources\\com\\example\\gameofthrones\\tent.png"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
