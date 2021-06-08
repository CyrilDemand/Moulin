package ihm;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.stage.Stage;
import ihm2.MainMenuSpacer;
import java.net.URISyntaxException;

public class Render extends Application {


    public static Scene sceneMainMenu;
    public static Scene sceneOptions;
    public static Scene scenePlay;
    public static Scene sceneRules;



    private Scene prevScene;

    String musicFile = "ressources/defaultMusic.mp3";


    private String toMusicFile(String musicName){


        musicName.toLowerCase();
        StringBuilder res= new StringBuilder();
        String sub="";
        for (int i = 0; i<musicName.length(); i++){
            sub=musicName.substring(i,i++);
            if(sub == " "){
                i++;
                sub.substring(i,i++).toUpperCase();
                res.append(sub);
            } else {
                res.append(sub);
            }
        }
        res.append(".mp3");
        return res.toString();
    }

    @Override
    public void start(Stage stage) throws URISyntaxException {

        VBox mainMenu = new VBox();
        sceneMainMenu = new Scene(mainMenu, 1000, 500);
        prevScene=sceneMainMenu;
        mainMenu.setAlignment(Pos.CENTER);

        VBox options = new VBox();
        sceneOptions = new Scene(options, 1000, 500);
        options.setAlignment(Pos.BASELINE_CENTER);

        VBox rules = new VBox();
        Label labRules = new Label("Règles");
        ReturnButton rulesReturn = new ReturnButton(prevScene,stage, false);
        sceneRules = new Scene(rules, 1000, 500);

        rules.setAlignment(Pos.CENTER);
        rules.getChildren().addAll(labRules,rulesReturn);

        GridPane play = new GridPane();
        scenePlay = new Scene(play, 1000, 500);

        Label labTitle = new Label("C tré le titre du projé");
        Region spacerLabTitle = new MainMenuSpacer(stage,0.1);
        MainMenuButton playButton = new MainMenuButton(" Jouer ",scenePlay,stage);
        playButton.setOnAction(e->{

        });
        //playButton.setId("mainMenuButtons");
        MainMenuSpacer spacerPlayButton = new MainMenuSpacer(stage);
        MainMenuButton settingsButton = new MainMenuButton(" Options ",sceneOptions,stage);
        //settingsButton.setId("mainMenuButtons");
        MainMenuSpacer spacerSettingsButton = new MainMenuSpacer(stage);
        MainMenuButton rulesButton = new MainMenuButton(" Règles ",sceneRules,stage);
        //rulesButton.setId("mainMenuButtons");
        MainMenuSpacer spacerRulesButton = new MainMenuSpacer(stage);
        MainMenuButton exitButton = new MainMenuButton(" Quitter ",null,stage);
       // exitButton.setId("mainMenuButtons");
        mainMenu.getChildren().addAll(labTitle,spacerLabTitle,playButton,spacerPlayButton,settingsButton,spacerSettingsButton,rulesButton,spacerRulesButton,exitButton);
        //mainMenu.getChildren().addAll(labTitle,playButton,settingsButton,rulesButton,exitButton);


        //Beginning of the option menu

        VBox labVBox = new VBox();
        labVBox.setAlignment(Pos.CENTER);

        MainMenuSpacer spacerBotLabel = new MainMenuSpacer(stage, 0.2);
        MainMenuSpacer spacerTopLabel = new MainMenuSpacer(stage, 0.1);
        Label labelOptions = new Label("Options");
        labelOptions.setId("mainSettingsLabel");
        labVBox.getChildren().addAll(spacerTopLabel,labelOptions);
        //Beginning of the music HBox

        HBox leftHBox = new HBox();
        VBox mVBox = new VBox();
        HBox mHBox = new HBox();
        Label musicLabel = new Label("Musique");
        Slider musicSlider = new Slider();
        musicSlider.setId("settingsSliders");
        CheckBox musicCheckBox = new CheckBox();
        musicCheckBox.setIndeterminate(false);
        musicSlider.setShowTickLabels(true);
        musicSlider.setShowTickMarks(true);


        Region hSpacer = new Region();

        HBox sHBox = new HBox();
        Label soundsLabel = new Label("Sons");
        Slider soundsSlider = new Slider();
        soundsSlider.setId("settingsSliders");
        soundsSlider.setShowTickLabels(true);
        soundsSlider.setShowTickMarks(true);
        CheckBox soundsCheckBox = new CheckBox();
        soundsCheckBox.setIndeterminate(false);
        sHBox.getChildren().addAll(soundsLabel,soundsSlider,soundsCheckBox);

        //Music


 /*       Media music = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();
        mediaPlayer.play();*/
        String source;
        Media media;
        MediaPlayer mediaPlayer;
        MediaView mediaView;
        try {
            media = new Media("/ressources/defaultMusic.mp3");
            System.out.println("media init");
            if (media.getError() == null) {
                media.setOnError(new Runnable() {
                    public void run() {
                        // Handle asynchronous error in Media object.
                    }
                });
                try {
                    mediaPlayer = new MediaPlayer(media);
                    if (mediaPlayer.getError() == null) {
                        System.out.println("mediaplayer on error");
                        mediaPlayer.setOnError(new Runnable() {
                            public void run() {
                                // Handle asynchronous error in MediaPlayer object.
                            }
                        });
                        mediaView = new MediaView(mediaPlayer);
                        mediaView.setOnError(new EventHandler<MediaErrorEvent>() {
                            public void handle(MediaErrorEvent t) {
                                // Handle asynchronous error in MediaView.
                            }
                        });
                    } else {
                        // Handle synchronous error creating MediaPlayer.
                    }
                } catch (Exception mediaPlayerException) {
                    System.out.println("1er catch");
                    // Handle exception in MediaPlayer constructor.
                }
            } else {
                // Handle synchronous error creating Media.
            }
        } catch (Exception mediaException) {
            System.out.println("2eme catch");
            System.out.println(mediaException.toString());
            // Handle exception in Media constructor.
        }
/*
        musicCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue ov, Boolean old_val, Boolean new_val) {
                if(new_val==true){
                    mediaPlayer.play();
                    System.out.println("true");
                }else {
                mediaPlayer.pause();
                }
            }
        });
*/

        //HBox musicHBox = new HBox(musicLabel,musicSlider,musicCheckBox,hSpacer,soundsLabel,soundsSlider,soundsCheckBox);

        //Beginning of the music choice HBox

        ObservableList<String> musicList =
                FXCollections.observableArrayList(
                        "default music",
                        "music 1",
                        "music 2",
                        "music 3",
                        "music 4"
                );

        final ComboBox musicComboBox = new ComboBox(musicList);
        musicComboBox.setValue(musicList.get(0));
        musicComboBox.setVisibleRowCount(4);

        musicComboBox.onActionProperty().addListener( e->{
            musicFile=toMusicFile((String) musicComboBox.getValue());
        });


        //Beginning of the full screen HBox

        Label fsLabel = new Label("Plein écran");
        SwitchButton switchFS = new SwitchButton(stage);
        HBox fullscreenHB = new HBox(fsLabel,switchFS);

        //Return button

        ReturnButton settingsReturn = new ReturnButton(prevScene,stage,false);

        //Position settings

        fullscreenHB.setAlignment(Pos.CENTER);
        settingsReturn.setAlignment(Pos.CENTER);
        //Puttin everything into the settings scene

        leftHBox.getChildren().add(mVBox);
        mVBox.getChildren().addAll(mHBox,musicComboBox);
        mHBox.getChildren().addAll(musicLabel,musicSlider,musicCheckBox);

        HBox musicHBox = new HBox(leftHBox,sHBox);
        musicHBox.setAlignment(Pos.CENTER);
        stage.widthProperty().addListener( e ->{
            musicHBox.setSpacing(stage.getWidth()*0.05);
        });
        stage.heightProperty().addListener( e ->{
            mVBox.setSpacing(stage.getHeight()*0.03);
        });
        options.getChildren().addAll(labVBox,spacerBotLabel,musicHBox,fullscreenHB,settingsReturn);
        options.setSpacing(10);

        //Beginning of the play scene
        play.setAlignment(Pos.CENTER);
        ReturnButton playSettingsReturn = new ReturnButton(prevScene,stage,false);


        sceneMainMenu.getStylesheets().add("ressources/mainMenu.css");
        sceneOptions.getStylesheets().add("ressources/optionMenu.css");
        stage.setTitle("Menu principal");
        stage.setScene(sceneMainMenu);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}