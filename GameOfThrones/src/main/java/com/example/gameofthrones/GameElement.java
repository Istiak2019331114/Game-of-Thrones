package com.example.gameofthrones;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class GameElement {
   private  int x;
   private int y;
   private int height;
   private int width;
   private AnchorPane pane;
   private int power;
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
      this.y = y;
      this.height = height;
      this.width = width;
      this.pane= pane;
      this.power= power;
      setImageView();
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

