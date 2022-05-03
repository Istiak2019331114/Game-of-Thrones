package com.example.gameofthrones;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SidePanel implements Initializable {

    @FXML
    private AnchorPane sidePanel;
    @FXML
    private Label playerCurrentPower,KillCounts,currentLevel;

    @FXML
    private ImageView currentHouseLogo;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sidePanel.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        playerCurrentPower.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        KillCounts.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        currentLevel.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
    }
    public void setPlayerPower(int power){
        playerCurrentPower.setText(Integer.toString(power));
    }
    public void setKillCount(int killCount){
        KillCounts.setText(Integer.toString(killCount));
    }
    public void setCurrentLevel(String levelName) {
        currentLevel.setText(levelName);
    }
    public void setCurrentHouseLogo(String imageName){
        String path =  "src\\main\\resources\\com\\example\\gameofthrones\\"+imageName;
        Image image=null;
        try
        {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        currentHouseLogo.setImage(image);
    }
    public void setPlace(int x, int y)
    {
        sidePanel.setTranslateX(x);
        sidePanel.setTranslateY(y);
    }

}
