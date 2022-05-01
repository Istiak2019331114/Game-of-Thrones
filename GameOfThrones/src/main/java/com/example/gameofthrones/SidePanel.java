package com.example.gameofthrones;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SidePanel implements Initializable {

    @FXML
    private AnchorPane sidePanel;
    @FXML
    private Label playerCurrentPower,redSoldierKilledCounts,blackSoldierKilledCounts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sidePanel.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        playerCurrentPower.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        redSoldierKilledCounts.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
        blackSoldierKilledCounts.setStyle("-fx-background-position: center center; " +
                "-fx-background-color:transparent;");
    }
    public void PlayerPower(String s){
        playerCurrentPower.setText(s);
    }
    public void RedSoliderKill(String s){
        redSoldierKilledCounts.setText(s);
    }
    public void BlackSoldierKill(String s){
        blackSoldierKilledCounts.setText(s);
    }
    public void setPlace(int x, int y)
    {
        sidePanel.setTranslateX(x);
        sidePanel.setTranslateY(y);
    }

}
