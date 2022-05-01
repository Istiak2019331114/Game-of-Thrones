package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

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
    @Override
    public  void setStyleToPowerLabelText()
    {
        powerLabel.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");

        powerLabel.setFont(Font.font("Mv Boli", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        powerLabel.setTextFill(Color.RED);
        powerLabel.setTextAlignment(TextAlignment.CENTER);
    }

}
