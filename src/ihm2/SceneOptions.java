package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SceneOptions {

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();

        HBox sliders=new HBox();

        HBox slider1=new HBox();
        Slider sliderMusic=new Slider();
        CheckBox checkBoxMusic=new CheckBox();
        slider1.getChildren().addAll(new Label("Music"),sliderMusic,checkBoxMusic);

        HBox slider2=new HBox();
        Slider sliderSounds=new Slider();
        CheckBox checkBoxSounds=new CheckBox();
        slider2.getChildren().addAll(new Label("Sounds"),sliderSounds,checkBoxSounds);

        sliders.getChildren().addAll(slider1,slider2);

        HBox musicSelect=new HBox();
        ComboBox<String> musicComboBox=new ComboBox<>();
        musicSelect.getChildren().addAll(new Label("Music : "),musicComboBox);

        CheckBox fullscreen=new CheckBox("Fullscreen");

        Button returnButton=new Button("Go back");
        returnButton.setOnAction(e->{
                Main.stage.setScene(SceneMainMenu.getScene());
        });

        root.getChildren().addAll(new Label("Options"),sliders,musicSelect,fullscreen,returnButton);

        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }

}
