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
   private AnchorPane maze;
   private int power;
   private int imageViewHeight;
   private int imageViewWidth;
   private int imageViweX;
   private int imageViweY;
   private int sleeptime=1000;
   private String imageName;
   public Label powerLabel;
   public ImageView imageView;
   private AnchorPane cellPane=new AnchorPane();
   public Image image;
   // For Tent
   public GameElement(int x, int y, int height, int width, AnchorPane maze) {
      this.x = x;
      this.y = y;
      this.height = height-10;
      this.width = width;
      this.maze = maze;
      imageViweX=0;
      imageViweY=0;
      imageViewHeight=height;
      imageViewWidth=width;
      setImageView();
      addCellPaneToMaze();
   }
   // For Tree
   public GameElement(int x, int y, int height, int width, AnchorPane maze,String imageName) {
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
      this.maze = maze;
      this.imageName =imageName;
      imageViweX=0;
      imageViweY=0;
      imageViewHeight=height;
      imageViewWidth=width;
      setImageView();
      addCellPaneToMaze();
   }

   // For Soldiers
   public GameElement(int x, int y, int height, int width, AnchorPane maze, int power) {
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
      this.maze = maze;
      this.power= power;
      imageViweX=compress/2;
      imageViweY=labelHeight;
      imageViewHeight=height-labelHeight;
      imageViewWidth=width-compress;
      setImageView();
      setPowerLabel(power);
      addCellPaneToMaze();
   }

   private void addCellPaneToMaze() {
      cellPane.setTranslateX(x);
      cellPane.setTranslateY(y);
      cellPane.setPrefHeight(height);
      cellPane.setPrefWidth(width);
      maze.getChildren().add(cellPane);
   }


   private void setImageView()
   {
      imageView  = new ImageView();

      draw();

      imageView.setImage(image);
      imageView.setX(imageViweX);
      imageView.setY(imageViweY);
      imageView.setFitHeight(imageViewHeight);
      imageView.setFitWidth(imageViewWidth);
      cellPane.getChildren().add(imageView);
   }
   public void setPowerLabel(int power)
   {
      powerLabel = new Label();
      powerLabel.setText(Integer.toString(power));

      setStyleToPowerLabelText();

      powerLabel.setPrefHeight(labelHeight);
      powerLabel.setPrefWidth(width);
      powerLabel.setLayoutX(0);
      powerLabel.setLayoutY(0);
      cellPane.getChildren().add(powerLabel);
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
   // Returns If this Solider can be defeated by player
   public boolean check(GameElement player)
   {
      return player.getPower()>=power;
   }
   public void removeFromMaze()
   {
      maze.getChildren().remove(cellPane);
   }

   public void hideFromMaze()
   {
      cellPane.setOpacity(0.0);
   }
   public void revealInMaze()
   {
      cellPane.setOpacity(1.0);
   }

   // For all except Tree
   public void updatePowerLabel(int newPower)
   {
      power=newPower;
      powerLabel.setText(Integer.toString(power));

   }
   // Updates Player Power for this Soldier
   public void updatePlayerPower(Player player)
   {

   }
   public AnchorPane getCellPane() {
      return cellPane;
   }
   public int getPower()
   {
      return power;
   }
   public AnchorPane getMaze()
   {
      return  maze;
   }
   public String getImageName()
   {
      return imageName;
   }

}

