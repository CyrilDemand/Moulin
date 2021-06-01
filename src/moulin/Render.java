package moulin;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.Window;

import java.net.URISyntaxException;


public class Render extends Application {

    private Scene prevScene;

    @Override
    public void start(Stage stage) throws URISyntaxException {

        VBox mainMenu = new VBox();
        Scene sceneMainMenu = new Scene(mainMenu, 1000, 500);
        prevScene=sceneMainMenu;
        mainMenu.setAlignment(Pos.CENTER);

        VBox options = new VBox();
        Scene sceneOptions = new Scene(options, 1000, 500);

        VBox rules = new VBox();
        Label labRules = new Label("Règles");
        ReturnButton rulesReturn = new ReturnButton(prevScene,stage, false);
        Scene sceneRules = new Scene(rules, 1000, 500);

        rules.setAlignment(Pos.CENTER);
        rules.getChildren().addAll(labRules,rulesReturn);

        GridPane play = new GridPane();
        Scene scenePlay = new Scene(play, 1000, 500);



//        mainMenu.setAlignment(Pos.CENTER);
        Label labTitle = new Label("C tré le titre du projé");
        Region spacerLabTitle = new MainMenuSpacer(stage,0.1);
        MainMenuButton playButton = new MainMenuButton(" Jouer ",scenePlay,stage);
        playButton.setId("mainMenuButtons");
        MainMenuSpacer spacerPlayButton = new MainMenuSpacer(stage);
        MainMenuButton settingsButton = new MainMenuButton(" Options ",sceneOptions,stage);
        settingsButton.setId("mainMenuButtons");
        MainMenuSpacer spacerSettingsButton = new MainMenuSpacer(stage);
        MainMenuButton rulesButton = new MainMenuButton(" Règles ",sceneRules,stage);
        rulesButton.setId("mainMenuButtons");
        MainMenuSpacer spacerRulesButton = new MainMenuSpacer(stage);
        MainMenuButton exitButton = new MainMenuButton(" Quitter ",null,stage);
        exitButton.setId("mainMenuButtons");

        mainMenu.getChildren().addAll(labTitle,spacerLabTitle,playButton,spacerPlayButton,settingsButton,spacerSettingsButton,rulesButton,spacerRulesButton,exitButton);
        //        mainMenu.getChildren().addAll(labTitle,playButton,settingsButton,rulesButton,exitButton);

        options.setAlignment(Pos.CENTER);
        class SwitchButton extends StackPane {
            private final Rectangle back = new Rectangle(30, 10, Color.RED);
            private final Button button = new Button();
            private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
            private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d;";
            private boolean state;

            private void init() {
                getChildren().addAll(back, button);
                setMinSize(30, 15);
                back.maxWidth(30);
                back.minWidth(30);
                back.maxHeight(10);
                back.minHeight(10);
                back.setArcHeight(back.getHeight());
                back.setArcWidth(back.getHeight());
                back.setFill(Color.valueOf("#ced5da"));
                Double r = 2.0;
                button.setShape(new Circle(r));
                setAlignment(button, Pos.CENTER_LEFT);
                button.setMaxSize(15, 15);
                button.setMinSize(15, 15);
                button.setStyle(buttonStyleOff);
            }

            public SwitchButton() {

                init();
                EventHandler<Event> click = new EventHandler<Event>() {
                    @Override
                    public void handle(Event e) {
                        if (state) {
                            stage.setFullScreen(false);
                            ReturnButton.setFsHint(false);
                            MainMenuButton.setFsHint(false);
                            button.setStyle(buttonStyleOff);
                            back.setFill(Color.valueOf("#ced5da"));
                            setAlignment(button, Pos.CENTER_LEFT);
                            state = false;
                        } else {
                            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                            stage.setFullScreen(true);
                            ReturnButton.setFsHint(true);
                            MainMenuButton.setFsHint(true);
                            button.setStyle(buttonStyleOn);
                            back.setFill(Color.valueOf("#80C49E"));
                            setAlignment(button, Pos.CENTER_RIGHT);
                            state = true;
                        }
                    }
                };

                button.setFocusTraversable(false);
                setOnMouseClicked(click);
                button.setOnMouseClicked(click);
            }
        }
        Label labelOptions = new Label("Options");
        Label fsLabel = new Label("Fullscreen");
        SwitchButton switchFS = new SwitchButton();
        HBox fullscreenHB = new HBox(fsLabel,switchFS);
        Label musicLabel = new Label("Musique");
        Slider musicSlider = new Slider();
        HBox musiqueHB = new HBox(musicLabel,musicSlider);
        Label soundsLabel = new Label("Sons");
        Slider soundsSlider = new Slider();
        HBox sonsHB = new HBox(soundsLabel,soundsSlider);
        ReturnButton settingsReturn = new ReturnButton(prevScene,stage,false);
        fullscreenHB.setAlignment(Pos.CENTER);
        sonsHB.setAlignment(Pos.CENTER);
        musiqueHB.setAlignment(Pos.CENTER);
        settingsReturn.setAlignment(Pos.CENTER);
        options.getChildren().addAll(labelOptions,fullscreenHB,musiqueHB,sonsHB,settingsReturn);

        play.setAlignment(Pos.CENTER);
        ReturnButton playSettingsReturn = new ReturnButton(prevScene,stage,false);

        /*settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            prevScene = stage.getScene();
            stage.setScene(sceneOptions);
        });*/

/*        rulesButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            prevScene = stage.getScene();
            stage.setScene(sceneRules);
        });*/

        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
            stage.close();
        });

        sceneMainMenu.getStylesheets().add("moulin/mainMenu.css");
        stage.setTitle("Main Menu");
        stage.setScene(sceneMainMenu);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}