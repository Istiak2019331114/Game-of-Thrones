package com.example.gameofthrones;

import java.io.File;

import java.io.FileNotFoundException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.MalformedURLException;

public class Video {

    public AnchorPane vlc;
    private Scene scene;
    private int screenWidth = 1200;
    private int screenHeight = 700;
    private MediaView mediaView;
    private Group prevroot;
    private MenuButton playButton, pauseButton, resetButton,backButton;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    public Video(Scene scene, Group prevroot) {
        try {

            vlc = new AnchorPane();
            this.prevroot = prevroot;
            this.scene = scene;
            this.scene.setRoot(vlc);

            vlc.setPrefHeight(screenHeight);
            vlc.setPrefWidth(screenWidth);


            addMedia();
            addPlayButton();
            addPauseButton();
            addResetButton();
            addbackButton();
            setVideoBackground();
            vlc.getChildren().addAll(playButton,pauseButton,resetButton,mediaView,backButton);



        }catch (Exception e){
            System.out.println("The exception is " + e);
        }

    }
    public void addMedia() throws FileNotFoundException, MalformedURLException {


        file = new File("src\\main\\resources\\com\\example\\gameofthrones\\history.mp4");
        media = new Media(file.toURI().toURL().toString());
        mediaPlayer = new  MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);

        mediaView.setFitWidth(1200);
        mediaView.setFitHeight(600);
        mediaView.setEffect(new Glow());



    }
    private void setVideoBackground(){
        String image = getClass().getResource("GOT1.jpg").toExternalForm();
        vlc.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: 100% 100%;");
    }
    public void addPlayButton(){

        playButton = new MenuButton("PLAY");
        playButton.setLayoutX(250);
        playButton.setLayoutY(660);
        playButton.setMinSize(100,30);
        playButton.setOnMouseClicked(event -> {
            playMedia();
        });
    }
    public void addPauseButton(){
        pauseButton = new MenuButton("PAUSE");
        pauseButton.setLayoutX(450);
        pauseButton.setLayoutY(660);
        pauseButton.setMinSize(100,30);

        pauseButton.setOnMouseClicked(event -> {
            pauseMedia();
        });
    }
    public void addResetButton(){
        resetButton = new MenuButton("RESET");
        resetButton.setLayoutX(650);
        resetButton.setLayoutY(660);
        resetButton.setMinSize(100,30);
        resetButton.setOnMouseClicked(event -> {
            resetMedia();
        });
    }
    public void addbackButton(){
        backButton = new MenuButton("BACK");
        backButton.setLayoutX(850);
        backButton.setLayoutY(660);
        backButton.setMinSize(100,30);

        backButton.setOnMouseClicked(event->{
            mediaPlayer.pause();
            scene.setRoot(prevroot);
        });
    }
    public void playMedia() {

        mediaPlayer.play();
    }

    public void pauseMedia() {

        mediaPlayer.pause();
    }

    public void resetMedia() {

        if(mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }
    private static class MenuButton extends StackPane{
        private Text text;
        public MenuButton(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(20));

            Rectangle bg = new Rectangle(100,30);
            bg.setOpacity(0.7);
            bg.setFill(Color.WHITE);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg,text);

            setOnMouseEntered(event->{
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            setOnMouseExited(event->{
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            DropShadow drop = new DropShadow(50,Color.BLACK);
            drop.setInput(new Glow());

            setOnMousePressed(event-> setEffect(drop));
            setOnMouseReleased(event-> setEffect(null));
        }
    }

}
