package com.example.gameofthrones;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javafx.application.Application.launch;

public class GameMenu extends Application {
    private GameMenuItems gameMenuItems;
    private Scene scene;
    private  LevelCount levelCompleted;
    private Group root;
    private int sceneWidth =1330;
    private int sceneHeight=700;
    MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws Exception{
        try {
            music();
            root = new Group();
            levelCompleted=new LevelCount();
            //root.setPrefSize(800, 600);
            InputStream path = Files.newInputStream(Paths.get("src\\main\\resources\\com\\example\\gameofthrones\\GOT1.jpg"));
            Image img = new Image(path);
            path.close();
            ImageView backGroundImgView = new ImageView(img);
            backGroundImgView.setFitWidth(sceneWidth);
            backGroundImgView.setFitHeight(sceneHeight);

            gameMenuItems = new GameMenuItems();
            gameMenuItems.setVisible(true);

            root.getChildren().addAll(backGroundImgView, gameMenuItems);
            scene = new Scene(root,sceneWidth,sceneHeight);

            scene.setOnKeyPressed(event->{
                if(event.getCode()== KeyCode.ESCAPE){
                    if(!gameMenuItems.isVisible()){
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), gameMenuItems);
                        fadeTransition.setFromValue(0);
                        fadeTransition.setToValue(1);
                        gameMenuItems.setVisible(true);
                        fadeTransition.play();
                    }
                    else{
                        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), gameMenuItems);
                        fadeTransition.setFromValue(1);
                        fadeTransition.setToValue(0);
                        fadeTransition.setOnFinished(evt -> gameMenuItems.setVisible(false));
                        fadeTransition.play();
                    }
                }
            });

            Image MenuIcon = new Image("file:src\\main\\resources\\com\\example\\gameofthrones\\MenuIcon.jpg");
            stage.getIcons().add(MenuIcon);
            stage.setTitle("Game of Thrones");
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void music(){
        String s = "src\\main\\resources\\com\\example\\gameofthrones\\GOT1.mp3";
        Media media = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }
    private class GameMenuItems extends Parent {
        public GameMenuItems(){
            VBox homeMenu = new VBox(10);
            VBox optionsMenu = new VBox(10);
            VBox newGameMenu = new VBox(10);

            homeMenu.setTranslateX(100);
            homeMenu.setTranslateY(200);
            optionsMenu.setTranslateX(100);
            optionsMenu.setTranslateY(200);
            newGameMenu.setTranslateX(100);
            newGameMenu.setTranslateY(200);

            final int offset = 400;
            optionsMenu.setTranslateX(offset);
            MenuButton btnNewGame = new MenuButton("NEW GAME");
            btnNewGame.setOnMouseClicked(event->{
                getChildren().add(newGameMenu);
                TranslateTransition translate = new TranslateTransition(Duration.seconds(0.25),homeMenu);
                translate.setToX(homeMenu.getTranslateX() - offset);

                TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.50),newGameMenu);
                translate1.setToX(homeMenu.getTranslateX());

                translate.play();
                translate1.play();

                translate.setOnFinished(evt ->{
                    getChildren().remove(homeMenu);
                });
            });

            MenuButton btnResum = new MenuButton("RESUME");
            btnResum.setOnMouseClicked(event->{
                FadeTransition fadeTranslate = new FadeTransition(Duration.seconds(0.5),this) ;
                fadeTranslate.setFromValue(1);
                fadeTranslate.setToValue(0);
                fadeTranslate.setOnFinished(evt -> setVisible(false));
                fadeTranslate.play();
            });
            MenuButton btnOptions = new MenuButton("OPTIONS");
            btnOptions.setOnMouseClicked(event->{
                getChildren().add(optionsMenu);
                TranslateTransition translate = new TranslateTransition(Duration.seconds(0.25),homeMenu);
                translate.setToX(homeMenu.getTranslateX() - offset);

                TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.50),optionsMenu);
                translate1.setToX(homeMenu.getTranslateX());

                translate.play();
                translate1.play();

                translate.setOnFinished(evt ->{
                    getChildren().remove(homeMenu);
                });
            });
            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event->{
                System.exit(0);
            });
            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event->{
                getChildren().add(homeMenu);
                TranslateTransition translate = new TranslateTransition(Duration.seconds(0.25),optionsMenu);
                translate.setToX(optionsMenu.getTranslateX()+offset);

                TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.5),homeMenu);
                translate1.setToX(optionsMenu.getTranslateX());

                translate.play();
                translate1.play();

                translate.setOnFinished(evt ->{
                    getChildren().remove(optionsMenu);
                });
            });
            MenuButton btnBackk = new MenuButton("GO BACK");
            btnBackk.setOnMouseClicked(event->{
                getChildren().add(homeMenu);
                TranslateTransition translate = new TranslateTransition(Duration.seconds(0.25),newGameMenu);
                translate.setToX(newGameMenu.getTranslateX()+offset);

                TranslateTransition translate1 = new TranslateTransition(Duration.seconds(0.5),homeMenu);
                translate1.setToX(newGameMenu.getTranslateX());

                translate.play();
                translate1.play();

                translate.setOnFinished(evt ->{
                    getChildren().remove(newGameMenu);
                });
            });

            MenuButton btnSound = new MenuButton("SOUND");
            btnSound.setOnMouseClicked(event->{

            });
            MenuButton btnVideo = new MenuButton("VIDEO");
            btnVideo.setOnMouseClicked(event->{
                mediaPlayer.stop();
                new Video(scene,root);
            });
            MenuButton btnHouseGreyjoy = new MenuButton("HOUSE GREYJOY",0);
            MenuButton btnHouseBaratheon = new MenuButton("HOUSE BARATHEON",1);
            MenuButton btnHouseLannister = new MenuButton("HOUSE LANNISTER",2);
            MenuButton btnHouseStark = new MenuButton("HOUSE STARK",3);
            MenuButton btnWesterosFinalBattle = new MenuButton("WESTEROS FINAL BATTLE",4);

            btnHouseGreyjoy.setOnMouseClicked(event->{
                if(0 <= levelCompleted.getLevelCount())  new LevelOne(scene,root,levelCompleted);
            });
            btnHouseBaratheon.setOnMouseClicked(event->{
                if(1 <= levelCompleted.getLevelCount())  new LevelTwo(scene,root,levelCompleted);
            });
            btnHouseLannister.setOnMouseClicked(event->{
                if(2 <= levelCompleted.getLevelCount())   new LevelThree(scene,root,levelCompleted);
            });
            btnHouseStark.setOnMouseClicked(event->{
                if(3 <= levelCompleted.getLevelCount())  new LevelFour(scene,root,levelCompleted);
            });
            btnWesterosFinalBattle.setOnMouseClicked(event->{
                if(4 <= levelCompleted.getLevelCount())   new LevelFive(scene,root,levelCompleted);
            });




            homeMenu.getChildren().addAll(btnNewGame,btnResum,btnOptions,btnExit);
            optionsMenu.getChildren().addAll(btnBack,btnSound,btnVideo);
            newGameMenu.getChildren().addAll(btnHouseGreyjoy,btnHouseBaratheon,btnHouseLannister,btnHouseStark,btnWesterosFinalBattle,btnBackk);
            Rectangle bg = new Rectangle(sceneWidth,sceneHeight);
            bg.setFill(Color.GREY);
            bg.setOpacity(0);
            getChildren().addAll(bg,homeMenu);
        }
    }
    private class MenuButton extends StackPane{
        private Text text;
        private int levelNumber = 0;
        private Rectangle RectbackGround;
        public MenuButton(String name){

            setText(name);

            setRectbackGround();

            setAlignment();

            addMouseListener();

        }
        public MenuButton(String name, int levelNumber){

            setText(name);
            this.levelNumber = levelNumber;
            setRectbackGround();

            setAlignment();

            addMouseListener();

        }
        private void addMouseListener(){
            setOnMouseEntered(event->{
                if(levelNumber <= levelCompleted.getLevelCount()) {
                    RectbackGround.setTranslateX(10);
                    text.setTranslateX(10);
                    RectbackGround.setFill(Color.WHITE);
                    text.setFill(Color.BLACK);
                }
            });
            setOnMouseExited(event->{
                if(levelNumber <= levelCompleted.getLevelCount()) {
                    RectbackGround.setTranslateX(0);
                    text.setTranslateX(0);
                    RectbackGround.setFill(Color.BLACK);
                    text.setFill(Color.WHITE);
                }
            });
            DropShadow dropShadow = new DropShadow(50,Color.WHITE);
            dropShadow.setInput(new Glow());

            setOnMousePressed(event-> setEffect(dropShadow));
            setOnMouseReleased(event-> setEffect(null));
        }
        private void setText(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(20));
        }
        private void setRectbackGround(){
            RectbackGround = new Rectangle(250,30);
            RectbackGround.setOpacity(0.7);
            RectbackGround.setFill(Color.BLACK);
            RectbackGround.setEffect(new GaussianBlur(3.5));
        }
        private void setAlignment(){
            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(RectbackGround,text);
        }
    }
    private class levelButton extends MenuButton{

        public levelButton(String name) {
            super(name);
        }

    }
    public static void main(String[] args) {
        launch(args);
    }
}
