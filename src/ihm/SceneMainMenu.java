package ihm;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * creates a scene for the main menu
 */
public class SceneMainMenu{

    private static Scene scene;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();

        MainMenuSpacer labelSpacer = new MainMenuSpacer(Main.getStage(), 0.1);
        Button playButton=new Button("Play");
        playButton.setOnAction(e->{
            SceneNewGame.switchTo();
        });
        MainMenuSpacer playButtonSpacer = new MainMenuSpacer(Main.getStage());
        Button optionButton=new Button("Options");
        optionButton.setOnAction(e->{
            SceneOptions.switchTo();
        });
        MainMenuSpacer optionButtonSpacer = new MainMenuSpacer(Main.getStage());
        Button rulesButton=new Button("Rules");
        rulesButton.setOnAction(e->{
            SceneRules.switchTo();
        });
        MainMenuSpacer rulesButtonSpacer = new MainMenuSpacer(Main.getStage());
        Button quitButton=new Button("Quit");
        quitButton.setOnAction(e->{
            Main.getStage().close();
        });

        Main.getStage().widthProperty().addListener( e->{
            playButton.setPrefWidth((Main.getStage().getWidth()*0.65));
            optionButton.setPrefWidth((Main.getStage().getWidth()*0.65));
            rulesButton.setPrefWidth((Main.getStage().getWidth()*0.65));
            quitButton.setPrefWidth((Main.getStage().getWidth()*0.65));
        });
        Main.getStage().heightProperty().addListener( e->{
            playButton.setPrefHeight(Main.getStage().getHeight()*0.1);
            optionButton.setPrefHeight(Main.getStage().getHeight()*0.1);
            rulesButton.setPrefHeight(Main.getStage().getHeight()*0.1);
            quitButton.setPrefHeight(Main.getStage().getHeight()*0.1);
        });

        root.getChildren().addAll(new Label("The Mill Game"),labelSpacer,playButton,playButtonSpacer,optionButton,optionButtonSpacer,rulesButton,rulesButtonSpacer,quitButton);

        root.setAlignment(Pos.CENTER);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
        scene.getStylesheets().add("ressources/mainMenu.css");
    }

    /**
     * switches to the scene
     */
    public static void switchTo(){
        Main.changeScene(scene);
    }

    /**
     * gets the scene
     * @return the scene
     */
    public static Scene getScene(){
        return scene;
    }
}
