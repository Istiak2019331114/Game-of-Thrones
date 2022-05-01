package com.example.gameofthrones;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public abstract class GameElement {
   private  int x;
   private int labelHeight=20;
   private int y;
   private int height;
   private int width;
   private int compress=20;
   private AnchorPane pane;
   private int power;
   public Label powerLabel;
   private ImageView imageView;
   public Image image;

   // For Tree
   public GameElement(int x, int y, int height, int width, AnchorPane pane) {
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
      this.pane= pane;
      setImageView();
   }
   // For Soldiers
   public GameElement(int x, int y, int height, int width, AnchorPane pane,int power) {
      this.x = x;
      this.y = y+labelHeight;
      this.height = height-labelHeight;
      this.width = width-compress;
      this.pane= pane;
      this.power= power;
      setImageView();
      setPowerLabel(power);
   }


   private void setImageView()
   {
      imageView  = new ImageView();

      draw();

      imageView.setImage(image);
      imageView.setX(x);
      imageView.setY(y);
      imageView.setFitHeight(height);
      imageView.setFitWidth(width);

      pane.getChildren().add(imageView);
   }
   public void setPowerLabel(int power)
   {
      powerLabel = new Label();
      powerLabel.setText(Integer.toString(power));

      setStyleToPowerLabelText();

      powerLabel.setPrefHeight(labelHeight);
      powerLabel.setPrefWidth(width+compress);
      powerLabel.setLayoutX(x);
      powerLabel.setLayoutY(y-labelHeight);
      pane.getChildren().add(powerLabel);
   }

   public  void setStyleToPowerLabelText()
   {
      powerLabel.setStyle("-fx-background-position: center center; " +
              "-fx-background-color:transparent;");

      powerLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
      powerLabel.setTextFill(Color.CYAN);
      powerLabel.setTextAlignment(TextAlignment.CENTER);
   }

   abstract public void draw();
   // For Checking that
   public boolean check(int power)
   {
      return power>=this.power;
   }
   public void removeFromMaze()
   {
      pane.getChildren().remove(imageView);
   }

   public void hideFromMaze()
   {
      getImageView().setOpacity(0.0);
   }
   public void revealInMaze()
   {
      getImageView().setOpacity(1.0);
   }
   public ImageView getImageView() {
      return imageView;
   }
}

