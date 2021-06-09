package ihm2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import moulin.Jeu;

public class SceneNewGame {

    private static Scene scene;
    private static Jeu jeu;


    public static void create(){
        VBox root=new VBox();
        Label title = new Label("New/Load a game");
        ResizingSpacer labSpacer = new ResizingSpacer(Main.getStage(), 0.1);
        Button newGame = new Button("New game");
        newGame.setOnAction(e->{
            Main.changeScene(SceneTypeOfMap.getScene());
        });
        ResizingSpacer newGameSpacer = new ResizingSpacer(Main.getStage(), 0.03);
        Button loadGame = new Button("Load a game file");
        loadGame.setOnAction(e->{
            Main.changeScene(SceneAskFile.getScene());
        });
        ResizingSpacer loadGameSpacer = new ResizingSpacer(Main.getStage(), 0.1);
        Button returnButton =new Button("Go back");
        returnButton.setId("returnButton");
        returnButton.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        Main.getStage().widthProperty().addListener(e->{
            returnButton.setPrefWidth((Main.getStage().getWidth()*0.5));
            newGame.setPrefWidth(Main.getStage().getWidth()*0.3);
            loadGame.setPrefWidth(Main.getStage().getWidth()*0.3);

        });
        Main.getStage().heightProperty().addListener(e->{
            returnButton.setPrefHeight((Main.getStage().getHeight()*0.1));
            newGame.setPrefHeight((Main.getStage().getHeight()*0.07));
            loadGame.setPrefHeight((Main.getStage().getHeight()*0.07));

        });

        root.getChildren().addAll(title,labSpacer,newGame,newGameSpacer,loadGame,loadGameSpacer,returnButton);
        root.setAlignment(Pos.CENTER);
        root.getStylesheets().add("ressources/newGameMenu.css");
        scene=new Scene(root,1000,500);
    }

    public static Jeu getJeu() {
        return jeu;
    }

    public static void setJeu(Jeu jeu) {
        SceneNewGame.jeu = jeu;
    }

    public static Scene getScene(){
        return scene;
    }
}
