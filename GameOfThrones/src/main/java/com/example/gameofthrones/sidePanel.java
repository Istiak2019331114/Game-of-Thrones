package com.example.gameofthrones;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SidePanel implements Initializable {

    @FXML
    private AnchorPane sidePanel;
    @FXML
    private TextArea playerCurrentPower,redSoldierKillCounts,blackSoldierKillerCounts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sidePanel.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        playerCurrentPower.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        redSoldierKillCounts.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        blackSoldierKillerCounts.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
    }
    public void PlayerPower(String s){
        playerCurrentPower.setText(s);
    }
    public void RedSoliderKill(String s){
        redSoldierKillCounts.setText(s);
    }
    public void BlackSoldierKill(String s){
        blackSoldierKillerCounts.setText(s);
    }
    public void setPlace(int x, int y)
    {
        sidePanel.setTranslateX(x);
        sidePanel.setTranslateY(y);
    }

}
