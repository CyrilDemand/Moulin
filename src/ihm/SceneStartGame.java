package ihm;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import moulin.*;

public class SceneStartGame {
    private static Scene scene;
    private static Button nextTurn;
    private static CustomCanvas customCanvas;

    /**
     * creates a scene
     */
    public static void create(){
        VBox root=new VBox();
        customCanvas = new CustomCanvas(800,600,true,false);
        nextTurn=new Button("Next Turn");
        nextTurn.setDisable(true);

        customCanvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                customCanvas.select(SceneNewGame.getJeu());
                nextTurn.setDisable(customCanvas.getSelectedNode()==null);
            }
        });
        root.getChildren().addAll(customCanvas,nextTurn);

        nextTurn.setOnAction(e->{
            Jeu jeu=SceneNewGame.getJeu();
            tour();
            jeu.addTurn();
            customCanvas.unselect(jeu);
            nextTurn.setDisable(false);
        });

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
     * switches to the scene
     */
    public static void switchTo(){
        tour();
        Jeu jeu=SceneNewGame.getJeu();
        jeu.addTurn();
        Main.changeScene(scene);
    }

    /**
     * switches to the next turn
     */
    public static void tour(){
        Jeu jeu=SceneNewGame.getJeu();
        System.out.println(jeu.getPlayers().get(Jeu.getTurn()));
        if (jeu.getPlayers().get(Jeu.getTurn()).getClass().getSimpleName().equals("NormalAI")){
            Piece piece = null;
            for (Piece p:jeu.getPlayers().get(Jeu.getTurn()).getPieces()) {
                if (p.getNode()==null){
                    piece=p;
                    break;
                }
            }
            ((NormalAI)jeu.getPlayers().get(Jeu.getTurn())).start(jeu,piece);
            jeu.addTurn();
            if (!jeu.getPlayers().get(Jeu.getTurn()).getClass().getSimpleName().equals("Player"))SceneStartGame.tour();
        }if (jeu.getPlayers().get(Jeu.getTurn()).getClass().getSimpleName().equals("RandomAI")){
            Piece piece = null;
            for (Piece p:jeu.getPlayers().get(Jeu.getTurn()).getPieces()) {
                if (p.getNode()==null){
                    piece=p;
                    break;
                }
            }
            ((RandomAI)jeu.getPlayers().get(Jeu.getTurn())).start(jeu,piece);
            jeu.addTurn();
            if (!jeu.getPlayers().get(Jeu.getTurn()).getClass().getSimpleName().equals("Player"))SceneStartGame.tour();
        }if (jeu.getPlayers().get(Jeu.getTurn()).getClass().getSimpleName().equals("Player")){
            Piece pPlayer = null;
            for (Piece p:jeu.getPlayers().get(Jeu.getTurn()).getPieces()) {
                if (p.getNode()==null){
                    pPlayer=p;
                    break;
                }
            }
            jeu.getPlayers().get(Jeu.getTurn()).getPieces().get(jeu.getPlayers().get(Jeu.getTurn()).getPieces().indexOf(pPlayer)).put(customCanvas.getSelectedNode());
            return;
        }
    }
}
