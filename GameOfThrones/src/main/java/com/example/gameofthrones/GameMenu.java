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
import javafx.scene.layout.Pane;
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
import javafx.fxml.FXMLLoader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javafx.application.Application.launch;

public class GameMenu extends Application {
    private GMenu gameMenu;
    private Scene scene;
    MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage) throws Exception{
        try {
            music();
            Group root = new Group();
            //root.setPrefSize(800, 600);
            InputStream path = Files.newInputStream(Paths.get("src\\main\\resources\\com\\example\\gameofthrones\\GOT1.jpg"));
            Image img = new Image(path);
            path.close();

            ImageView imgView = new ImageView(img);
            imgView.setFitWidth(1200);
            imgView.setFitHeight(700);
            gameMenu = new GMenu();
            gameMenu.setVisible(false);
            root.getChildren().addAll(imgView,gameMenu);

             scene = new Scene(root,1200,700);
            scene.setOnKeyPressed(event->{
                if(event.getCode()== KeyCode.ESCAPE){
                    if(!gameMenu.isVisible()){
                        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),gameMenu);
                        ft.setFromValue(0);
                        ft.setToValue(1);
                        gameMenu.setVisible(true);
                        ft.play();
                    }
                    else{
                        FadeTransition ft = new FadeTransition(Duration.seconds(0.5),gameMenu);
                        ft.setFromValue(1);
                        ft.setToValue(0);
                        ft.setOnFinished(evt -> gameMenu.setVisible(false));
                        ft.play();
                    }
                }
            });
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
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
    private class GMenu extends Parent {
        public GMenu(){
            VBox menu0 = new VBox(10);
            VBox menu1 = new VBox(10);
            VBox menu2 = new VBox(10);

            menu0.setTranslateX(100);
            menu0.setTranslateY(200);
            menu1.setTranslateX(100);
            menu1.setTranslateY(200);
            menu2.setTranslateX(100);
            menu2.setTranslateY(200);

            final int offset = 400;
            menu1.setTranslateX(offset);
            MenuButton btnNewGame = new MenuButton("NEW GAME");
            btnNewGame.setOnMouseClicked(event->{
                getChildren().add(menu2);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.50),menu2);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(menu0);
                });
            });

            MenuButton btnResum = new MenuButton("RESUME");
            btnResum.setOnMouseClicked(event->{
                FadeTransition ft = new FadeTransition(Duration.seconds(0.5),this) ;
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.setOnFinished(evt -> setVisible(false));
                ft.play();
            });
            MenuButton btnOptions = new MenuButton("OPTIONS");
            btnOptions.setOnMouseClicked(event->{
                getChildren().add(menu1);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu0);
                tt.setToX(menu0.getTranslateX() - offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.50),menu1);
                tt1.setToX(menu0.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(menu0);
                });
            });
            MenuButton btnExit = new MenuButton("EXIT");
            btnExit.setOnMouseClicked(event->{
                System.exit(0);
            });
            MenuButton btnBack = new MenuButton("BACK");
            btnBack.setOnMouseClicked(event->{
                getChildren().add(menu0);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu1);
                tt.setToX(menu1.getTranslateX()+offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu0);
                tt1.setToX(menu1.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(menu1);
                });
            });
            MenuButton btnBackk = new MenuButton("GO BACK");
            btnBackk.setOnMouseClicked(event->{
                getChildren().add(menu0);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25),menu2);
                tt.setToX(menu2.getTranslateX()+offset);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5),menu0);
                tt1.setToX(menu2.getTranslateX());

                tt.play();
                tt1.play();

                tt.setOnFinished(evt ->{
                    getChildren().remove(menu2);
                });
            });

            MenuButton btnSound = new MenuButton("SOUND");
            MenuButton btnVideo = new MenuButton("VIDEO");
            MenuButton btnHouse1 = new MenuButton("HOUSE LANNISTER");
            MenuButton btnHouse2 = new MenuButton("HOUSE BARATHEON");
            MenuButton btnHouse3 = new MenuButton("HOUSE GREYJOY");

            btnHouse1.setOnMouseClicked(event->{
                new LevelOne(scene);
            });
            btnHouse2.setOnMouseClicked(event->{
                new LevelTwo(scene);
            });
            btnHouse3.setOnMouseClicked(event->{
                new LevelThree(scene);
            });

            menu0.getChildren().addAll(btnNewGame,btnResum,btnOptions,btnExit);
            menu1.getChildren().addAll(btnBack,btnSound,btnVideo);
            menu2.getChildren().addAll(btnHouse1,btnHouse2,btnHouse3,btnBackk);
            Rectangle bg = new Rectangle(1200,700);
            bg.setFill(Color.GREY);
            bg.setOpacity(0.6);
            getChildren().addAll(bg,menu0);
        }
    }
    private static class MenuButton extends StackPane{
        private Text text;
        public MenuButton(String name){
            text = new Text(name);
            text.setFont(text.getFont().font(20));

            Rectangle bg = new Rectangle(250,30);
            bg.setOpacity(0.7);
            bg.setFill(Color.BLACK);
            bg.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg,text);

            setOnMouseEntered(event->{
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            setOnMouseExited(event->{
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            DropShadow drop = new DropShadow(50,Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event-> setEffect(drop));
            setOnMouseReleased(event-> setEffect(null));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
