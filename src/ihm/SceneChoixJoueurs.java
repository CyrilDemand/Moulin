package ihm;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.ColorEnum;
import moulin.Config;
import moulin.Player;

import java.util.ArrayList;

/**
 * creates a scene to show the Player choice screen
 */
public class SceneChoixJoueurs {

    private static Scene scene;
    private static Label nextLabel = new Label();
    /**
     * players hbox
     */
    public static ListView<PlayerHBox> players = new ListView<>();

    private static Button addButton = new Button("+");

    private static Button next;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();

        for (int i = 0;i<2;i++){
            players.getItems().add(new PlayerHBox());
        }
        SceneChoixJoueurs.delButton();
        addButton.setOnAction(e->{
            players.getItems().add(new PlayerHBox());
            if (players.getItems().size()==Config.playerMax){
                addButton.setDisable(true);
            }
            SceneChoixJoueurs.delButton();
            SceneChoixJoueurs.switchTo();
        });
        HBox buttonBar=new HBox();
        Button goBackToMainMenu=new Button("Go Back to main menu");
        goBackToMainMenu.setOnAction(e->{
            SceneMainMenu.switchTo();
        });
        Button goBack=new Button("Go Back");
        goBack.setOnAction(e->{
            SceneNewGame.switchTo();
        });
        next=new Button("Next");
        next.setOnAction(e->{
            ArrayList<Player> players = new ArrayList<>();
            for (PlayerHBox pb:SceneChoixJoueurs.players.getItems()) {
                if (pb!=null){
                    System.out.println(pb);
                    if (pb.isHuman()){
                        players.add(pb.getPlayer());
                    }else{
                        players.add(pb.getAI());
                    }
                }
            }
            SceneNewGame.setPlayers(players);
            SceneChoiceStartGame.switchTo();
        });
        buttonBar.getChildren().addAll(goBackToMainMenu,goBack,next);

        root.getChildren().addAll(addButton,players,nextLabel,buttonBar);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    /**
     * gets the scene
     * @return the scene
     */
    public static Scene getScene(){
        return scene;
    }

    /**
     * switched to the scene
     */
    public static void switchTo(){
        if (SceneChoixJoueurs.differentColor()!=SceneChoixJoueurs.players.getItems().size()){
            nextLabel.setText("All players must have a different color !");
            SceneChoixJoueurs.next.setDisable(true);
        }else{
            nextLabel.setText("");
            SceneChoixJoueurs.next.setDisable(false);
        }
        Main.changeScene(scene);
    }

    /**
     * checks if the button can be deleted
     */
    public static void delButton(){
        for (PlayerHBox p:SceneChoixJoueurs.players.getItems()){
            if (players.getItems().size()==2){
                p.getbDelete().setDisable(true);
            }else{
                p.getbDelete().setDisable(false);
            }
        }
        if (players.getItems().size()==Config.playerMax){
            addButton.setDisable(true);
        }else {
            addButton.setDisable(false);
        }
    }

    /**
     * checks if the color exists before adding it
     * @return the list size
     */
    private static int differentColor(){
        ArrayList<ColorEnum> list = new ArrayList<>();
        for (PlayerHBox pb:SceneChoixJoueurs.players.getItems()) {
            if (!list.contains(pb.getColor())){
                list.add(pb.getColor());
            }
        }
        return list.size();
    }
}
