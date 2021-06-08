package ihm2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import moulin.Config;
import moulin.Player;

import java.util.ArrayList;

public class SceneChoixJoueurs {

    private static Scene scene;

    public static ListView<PlayerHBox> players = new ListView<>();

    private static Button addButton = new Button("+");
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
        Button next=new Button("Next");
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

        root.getChildren().addAll(addButton,players,buttonBar);
        scene=new Scene(root,Main.getDefaultSceneWidth(),Main.getDefaultSceneHeight());
    }

    public static Scene getScene(){
        return scene;
    }

    public static void switchTo(){
        Main.changeScene(scene);
    }

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
        }
    }

}
