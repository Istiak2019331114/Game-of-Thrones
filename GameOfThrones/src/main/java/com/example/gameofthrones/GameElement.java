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

   private ImageView imageView;
   public Image image;

   public GameElement(int x, int y, int height, int width, AnchorPane pane) {
      this.x = x;
      this.y = y;
      this.height = height;
      this.width = width;
      this.pane= pane;
      setImageView();
   }

   abstract public void draw();
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

   public ImageView getImageView() {
      return imageView;
   }
}

