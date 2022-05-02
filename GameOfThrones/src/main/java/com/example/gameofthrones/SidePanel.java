package com.example.gameofthrones;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
        currentHouseLogo.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");

    }
    public void PlayerPower(String s){
        playerCurrentPower.setText(s);
    }
    public void TotalKill(String s){
        KillCounts.setText(s);
    }
    public void curLevel(String s) {currentLevel.setText(s);}
    public void curHouseLogo(String img){

        currentHouseLogo.setStyle("-fx-background-image: url('" + img + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: 100% 100%;");
    }
    public void setPlace(int x, int y)
    {
        sidePanel.setTranslateX(x);
        sidePanel.setTranslateY(y);
    }

}
