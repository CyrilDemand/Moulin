package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SceneRules {

    private static Scene scene;

    public static void create(){
        VBox root=new VBox();
        root.getChildren().add(new Label("Rules"));
        root.getChildren().add(new Label("The mill game is a game played by two to four players.\n" +
                "Each player has 3 pieces of their respective colors.\n" +
                "The object of the game is to line up the pieces.\n" +
                "During the first phase of the game, each player must place his pieces in a row.\n" +
                "During the second phase of the game, each player, in turn, can either\n" +
                "- place a trap that will block a line\n" +
                "- place a teleporter from one top to another\n" +
                "- move one of his pieces.\n" +
                "You can save the game too. \n"));

        Button returnButton =new Button("Go back");
        returnButton.setOnAction(e->{
            Main.changeScene(SceneMainMenu.getScene());
        });
        root.getChildren().add(returnButton);
        scene=new Scene(root,1000,500);
    }

    public static Scene getScene(){
        return scene;
    }

}
