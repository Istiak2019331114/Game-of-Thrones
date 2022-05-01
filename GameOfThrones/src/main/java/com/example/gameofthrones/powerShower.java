package com.example.gameofthrones;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class powerShower  implements Initializable {

    @FXML
    private AnchorPane powerShow;
    @FXML
    private TextArea playerCurrentPower,redSoldierKillCounts,blackSoldierKillerCounts;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        powerShow.setStyle("-fx-background-position: center center; " +
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

}
