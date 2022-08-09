package com.example.gameofthrones;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WinningPanelController {
    @FXML
    private ImageView winningPanelImageView;
    @FXML
    private Button newGameMenuButton;

    @FXML
    private Text winLooseMessage;

    private Group previousRoot;

    public void setWinningPanelImageView(String imageName){
        String path =  "src\\main\\resources\\com\\example\\gameofthrones\\"+imageName;
        Image image=null;
        try
        {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        winningPanelImageView.setImage(image);
    }

    @FXML
    private void backToPreviousRoot(ActionEvent event) throws IOException
    {
        ((Node)event.getSource()).getScene().setRoot(previousRoot);
    }

    public void setPreviousRoot(Group previousRoot) {
        this.previousRoot = previousRoot;
    }

    public void setWinLooseMessage(String winLooseMessage) {
        this.winLooseMessage.setText(winLooseMessage);
    }
}
