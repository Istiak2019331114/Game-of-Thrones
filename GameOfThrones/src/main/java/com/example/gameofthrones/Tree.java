package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tree extends GameElement {

    public Tree(int x, int y, int height, int width, AnchorPane pane,String treeName) {
        super(x, y, height, width, pane,treeName);
    }
    @Override
    public void draw()
    {
        String path =  "src\\main\\resources\\com\\example\\gameofthrones\\"+getImageName();
        try
        {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}
