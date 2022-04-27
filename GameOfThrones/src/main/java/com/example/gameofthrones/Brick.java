package com.example.leraringjavafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Brick {
    private int x;
    private int y;
    private int height;
    private int width;
    private Image image;
    private ImageView imageview;

    public Brick(int x, int y, int height,int width, Image image, ImageView imageview) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.image = image;
        this.imageview = imageview;
    }


    public void draw()
    {
        imageview.setX(x);
        imageview.setY(y);
        imageview.setFitHeight(height);
        imageview.setFitWidth(width);
        imageview.setImage(image);
    }
}


