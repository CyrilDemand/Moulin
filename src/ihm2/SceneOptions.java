package ihm2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneOptions {

    private static Scene scene;

    public static void create(){

        VBox root=new VBox();

        Label mainOptionsLabel = new Label("Options");
        mainOptionsLabel.setId("mainOptionsLabel");

        HBox sliders=new HBox();
        sliders.setAlignment(Pos.CENTER);
        Main.getStage().widthProperty().addListener( e ->{
            sliders.setSpacing(Main.getStage().getWidth()*0.05);
        });

        ResizingSpacer spacerAboveLabel = new ResizingSpacer(Main.getStage(), 0.1);
        ResizingSpacer spacerUnderLabel = new ResizingSpacer(Main.getStage(), 0.2);

        HBox slider1=new HBox();
        Slider sliderMusic=new Slider();
        CheckBox checkBoxMusic=new CheckBox();
        slider1.getChildren().addAll(new Label("Music"),sliderMusic,checkBoxMusic);

        HBox slider2=new HBox();
        Slider sliderSounds=new Slider();
        CheckBox checkBoxSounds=new CheckBox();
        slider2.getChildren().addAll(new Label("Sounds"),sliderSounds,checkBoxSounds);


        ResizingSpacer musicSpacer = new ResizingSpacer(Main.getStage(),0.05);

        HBox musicSelect=new HBox();
        ComboBox<String> musicComboBox=new ComboBox<>();
        musicSelect.getChildren().addAll(new Label("Music : "),musicComboBox);
        musicSelect.setAlignment(Pos.CENTER);

        sliders.getChildren().addAll(slider1,slider2);

        ResizingSpacer fullScreenSpacer = new ResizingSpacer(Main.getStage(), 0.05);

        CheckBox fullscreen=new CheckBox("Fullscreen");
        fullscreen.setIndeterminate(false);
        fullscreen.setOnAction(e->{
            if(fullscreen.selectedProperty().getValue()){
                Main.getStage().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                Main.getStage().setFullScreen(true);
                Main.setFsHint(true);
            } else {
                Main.getStage().setFullScreen(false);
                Main.setFsHint(false);
            }
        });

        Button returnButton=new Button("Go back");
        returnButton.setOnAction(e->{
                Main.changeScene(SceneMainMenu.getScene());
        });

        root.getChildren().addAll(spacerAboveLabel,mainOptionsLabel,spacerUnderLabel,sliders,musicSpacer,musicSelect,fullScreenSpacer,fullscreen,returnButton);

        root.setAlignment(Pos.BASELINE_CENTER);
        scene=new Scene(root,1000,500);
        scene.getStylesheets().add("ressources/optionMenu.css");
    }

    public static Scene getScene(){
        return scene;
    }

}
