package ihm2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SceneRules {

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        Label mainLabelRules = new Label("Rules");
        ResizingSpacer mainLabSpacer = new ResizingSpacer(Main.getStage(),0.15);
        mainLabelRules.setId("mainLabels");

        root.getChildren().addAll(mainLabelRules,mainLabSpacer);

        Label rules = new Label("The mill game is a game played by two to four players.\n" +
                "Each player has 3 pieces of their respective colors.\n" +
                "The object of the game is to line up the pieces.\n" +
                "During the first phase of the game, each player must place his pieces in a row.\n" +
                "During the second phase of the game, each player, in turn, can either\n" +
                "- place a trap that will block a line\n" +
                "- place a teleporter from one top to another\n" +
                "- move one of his pieces.\n" +
                "You can save the game too. \n");

        ResizingSpacer rulesSpacer = new ResizingSpacer(Main.getStage(), 0.1);

        Button returnButton =new Button("Go back");
        returnButton.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Main.getStage().widthProperty().addListener(e->{
            returnButton.setPrefWidth((Main.getStage().getWidth()*0.50));
        });
        Main.getStage().heightProperty().addListener(e->{
            returnButton.setPrefHeight((Main.getStage().getHeight()*0.1));
        });

        root.getChildren().addAll(rules,rulesSpacer,returnButton);
        root.setAlignment(Pos.CENTER);
        scene=new Scene(root,1000,500);
        scene.getStylesheets().add("ressources/rulesMenu.css");
    }

    public static Scene getScene(){
        return scene;
    }

}
